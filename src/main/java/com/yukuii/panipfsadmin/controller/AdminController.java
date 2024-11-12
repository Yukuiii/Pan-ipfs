package com.yukuii.panipfsadmin.controller;


import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yukuii.panipfsadmin.common.result.Result;
import com.yukuii.panipfsadmin.model.entity.User;
import com.yukuii.panipfsadmin.model.vo.StatisticsVO;
import com.yukuii.panipfsadmin.model.vo.FileVO;
import com.yukuii.panipfsadmin.service.AdminService;

import cn.dev33.satoken.annotation.SaCheckRole;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@SaCheckRole("ADMIN")
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/statistics")
    public Result<StatisticsVO> getStatistics() {
        return Result.success(adminService.getStatistics());
    }

    @GetMapping("/users")
    public Result<List<User>> getUserList() {
        return Result.success(adminService.getUserList());
    }

    @DeleteMapping("/users/{userId}")
    public Result<Void> deleteUser(@PathVariable Long userId) {
        adminService.deleteUser(userId);
        return Result.success();
    }

    @GetMapping("/files")
    public Result<List<FileVO>> getAllFiles() {
        return Result.success(adminService.getAllFiles());
    }

    @DeleteMapping("/files/{fileId}")
    public Result<Void> deleteFile(@PathVariable Long fileId) {
        adminService.deleteFile(fileId);
        return Result.success();
    }

    @PostMapping("/users")
    public Result<?> addUser(@RequestBody User user) {
        // 添加用户前先检查用户名是否已存在
        if (userService.getUserByUsername(user.getUsername()) != null) {
            return Result.error("用户名已存在");
        }
        // 对密码进行加密
        user.setPassword(BCrypt.hashpw(user.getPassword()));
        userService.addUser(user);
        return Result.success();
    }
} 