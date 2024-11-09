package com.yukuii.panipfsadmin.controller;

import com.yukuii.panipfsadmin.common.result.Result;
import com.yukuii.panipfsadmin.model.vo.FileUploadVO;
import com.yukuii.panipfsadmin.service.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @Resource
    private FileService fileService;

    @PostMapping
    public Result<FileUploadVO> uploadFile(@RequestParam("file") MultipartFile file) throws Exception {
        FileUploadVO result = fileService.uploadFile(file);
        return Result.success(result);
    }
}
