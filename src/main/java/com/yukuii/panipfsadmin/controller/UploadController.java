package com.yukuii.panipfsadmin.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.yukuii.panipfsadmin.common.result.Result;
import com.yukuii.panipfsadmin.model.entity.File;
import com.yukuii.panipfsadmin.model.vo.FileUploadVO;
import com.yukuii.panipfsadmin.service.FileService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/upload")
@SaCheckLogin
public class UploadController {

    @Resource
    private FileService fileService;

    @PostMapping
    public Result<FileUploadVO> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        FileUploadVO result = fileService.uploadFile(file);
        return Result.success(result);
    }
    
    @PostMapping("/hash")
    public Result<?> saveFileByHash(@RequestBody FileUploadVO fileUploadVO) {
        fileService.saveFileRecord(fileUploadVO);
        return Result.success();
    }
    
    @GetMapping("/files")
    public Result<List<File>> getUserFiles() {
        Long userId = StpUtil.getLoginIdAsLong();
        List<File> files = fileService.getUserFiles(userId);
        return Result.success(files);
    }
    
    @DeleteMapping("/{fileId}")
    public Result<?> deleteFile(@PathVariable Long fileId) {
        Long userId = StpUtil.getLoginIdAsLong();
        fileService.deleteFile(fileId, userId);
        return Result.success();
    }
}
