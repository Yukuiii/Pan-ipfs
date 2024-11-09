package com.yukuii.panipfsadmin.service;

import com.yukuii.panipfsadmin.model.vo.FileUploadVO;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    FileUploadVO uploadFile(MultipartFile file) throws Exception;
} 