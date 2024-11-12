package com.yukuii.panipfsadmin.service;

import com.yukuii.panipfsadmin.model.entity.File;
import com.yukuii.panipfsadmin.model.vo.FileUploadVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {
    FileUploadVO uploadFile(MultipartFile file) throws Exception;
    
    // 保存文件记录
    void saveFileRecord(FileUploadVO fileUploadVO);
    
    // 获取用户的文件列表
    List<File> getUserFiles(Long userId);
    
    // 删除文件记录
    void deleteFile(Long fileId, Long userId);
} 