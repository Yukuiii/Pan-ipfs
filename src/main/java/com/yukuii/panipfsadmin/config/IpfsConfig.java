package com.yukuii.panipfsadmin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "ipfs")
public class IpfsConfig {
    private String gateway;
    private String apiUrl;
} 