package com.yukuii.panipfsadmin.model.vo;

import lombok.Data;
import com.yukuii.panipfsadmin.entity.User;

@Data
public class LoginResponseVo {
    private User user;
    private String token;
}
