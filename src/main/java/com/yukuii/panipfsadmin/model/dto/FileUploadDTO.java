package com.yukuii.panipfsadmin.model.dto;

import lombok.Data;

@Data
public class FileUploadDTO {
    private String Name;
    private String Hash;
    private String Size;
    private String Url;
} 