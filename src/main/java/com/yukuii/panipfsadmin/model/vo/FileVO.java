package com.yukuii.panipfsadmin.model.vo;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class FileVO {
    private Long id;
    private String fileName;
    private String fileHash;
    private Long fileSize;
    private String fileUrl;
    private Long userId;
    private String username;  // 用户名
    private String nickname;  // 用户昵称
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
} 