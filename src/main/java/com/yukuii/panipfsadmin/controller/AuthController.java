package com.yukuii.panipfsadmin.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.yukuii.panipfsadmin.common.result.Result;
import com.yukuii.panipfsadmin.entity.User;
import com.yukuii.panipfsadmin.model.dto.LoginDTO;
import com.yukuii.panipfsadmin.model.dto.RegisterDTO;
import com.yukuii.panipfsadmin.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result<User> login(@Validated @RequestBody LoginDTO loginDTO) {
        User user = userService.login(loginDTO);
        return Result.success(user);
    }

    @PostMapping("/register")
    public Result<?> register(@Validated @RequestBody RegisterDTO registerDTO) {
        userService.register(registerDTO);
        return Result.success();
    }

    @PostMapping("/logout")
    public Result<?> logout() {
        StpUtil.logout();
        return Result.success();
    }

    @GetMapping("/info")
    public Result<?> getUserInfo() {
        // TODO: 获取当前登录用户信息
        return Result.success();
    }
} 