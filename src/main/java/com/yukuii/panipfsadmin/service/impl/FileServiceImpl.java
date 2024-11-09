package com.yukuii.panipfsadmin.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.alibaba.fastjson2.JSON;
import com.yukuii.panipfsadmin.common.config.IpfsConfig;
import com.yukuii.panipfsadmin.common.exception.BusinessException;
import com.yukuii.panipfsadmin.entity.File;
import com.yukuii.panipfsadmin.mapper.FileMapper;
import com.yukuii.panipfsadmin.model.dto.FileUploadDTO;
import com.yukuii.panipfsadmin.model.vo.FileUploadVO;
import com.yukuii.panipfsadmin.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class FileServiceImpl implements FileService {

    @Resource
    private IpfsConfig ipfsConfig;
    
    @Resource
    private RestTemplate restTemplate;
    
    @Resource
    private FileMapper fileMapper;

    @Override
    public FileUploadVO uploadFile(MultipartFile file) throws Exception {
        if (file.isEmpty()) {
            throw new BusinessException("上传文件不能为空");
        }

        // 准备上传请求
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        try { 
            // 发送上传请求到IPFS API
            ResponseEntity<String> response = restTemplate.exchange(
                ipfsConfig.getApiUrl() + "/api/v0/add",
                HttpMethod.POST,
                requestEntity,
                String.class
            );

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                // 解析响应
                FileUploadDTO fileUploadDTO = JSON.parseObject(response.getBody(), FileUploadDTO.class);
                // 构建返回结果
                FileUploadVO fileUploadVO = new FileUploadVO();
                fileUploadVO.setFileHash(fileUploadDTO.getHash());
                fileUploadVO.setFileName(fileUploadDTO.getName());
                fileUploadVO.setFileSize(fileUploadDTO.getSize());
                fileUploadVO.setFileUrl(fileUploadDTO.getUrl());
                
                // 保存文件记录
                saveFileRecord(fileUploadVO);
                
                return fileUploadVO;
            } else {
                throw new BusinessException("文件上传失败");
            }
        } catch (Exception e) {
            throw new BusinessException("文件上传失败：" + e.getMessage());
        }
    }
    
    @Override
    public void saveFileRecord(FileUploadVO fileUploadVO) {
        File file = new File();
        file.setUserId(StpUtil.getLoginIdAsLong());
        file.setFileHash(fileUploadVO.getFileHash());
        file.setFileName(fileUploadVO.getFileName());
        file.setFileSize(fileUploadVO.getFileSize());
        file.setFileUrl(fileUploadVO.getFileUrl());
        
        fileMapper.insert(file);
    }
    
    @Override
    public List<File> getUserFiles(Long userId) {
        LambdaQueryWrapper<File> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(File::getUserId, userId)
               .orderByDesc(File::getCreateTime);
        
        return fileMapper.selectList(wrapper);
    }
    
    @Override
    public void deleteFile(Long fileId, Long userId) {
        LambdaQueryWrapper<File> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(File::getId, fileId)
               .eq(File::getUserId, userId);
               
        if (fileMapper.delete(wrapper) == 0) {
            throw new BusinessException("文件不存在或无权限删除");
        }
    }
} 