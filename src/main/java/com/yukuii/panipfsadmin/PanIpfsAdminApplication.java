package com.yukuii.panipfsadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.yukuii.panipfsadmin.mapper")
public class PanIpfsAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(PanIpfsAdminApplication.class, args);
    }

}
