package com.yukuii.panipfsadmin.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.yukuii.panipfsadmin.common.result.Result;
import com.yukuii.panipfsadmin.model.entity.User;
import com.yukuii.panipfsadmin.model.dto.LoginDTO;
import com.yukuii.panipfsadmin.model.dto.RegisterDTO;
import com.yukuii.panipfsadmin.model.vo.LoginResponseVo;
import com.yukuii.panipfsadmin.service.UserService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private UserService userService;

    @PostMapping("/login")
    public Result<LoginResponseVo> login(@Validated @RequestBody LoginDTO loginDTO) {
       LoginResponseVo loginResponseVo = userService.login(loginDTO);
        return Result.success(loginResponseVo);
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
    public Result<User> getUserInfo() {
        Long userId = StpUtil.getLoginIdAsLong();
        User user = userService.getUserInfo(userId);
        return Result.success(user);
    }
} 