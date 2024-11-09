package com.yukuii.panipfsadmin.model.vo;

import lombok.Data;

@Data
public class FileUploadVO {
    private String fileHash;    // IPFS文件哈希
    private String fileName;    // 文件名
    private String fileUrl;     // 文件访问URL
    private String fileSize;      // 文件大小
} 