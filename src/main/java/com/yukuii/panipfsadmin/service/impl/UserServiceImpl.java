package com.yukuii.panipfsadmin.service.impl;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import com.yukuii.panipfsadmin.common.exception.BusinessException;
import com.yukuii.panipfsadmin.entity.User;
import com.yukuii.panipfsadmin.model.dto.LoginDTO;
import com.yukuii.panipfsadmin.model.dto.RegisterDTO;
import com.yukuii.panipfsadmin.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    
    @Override
    public User login(LoginDTO loginDTO) {
        // TODO: 实现数据库查询逻辑
        User user = getUserByUsername(loginDTO.getUsername());
        
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        
        if (!BCrypt.checkpw(loginDTO.getPassword(), user.getPassword())) {
            throw new BusinessException("密码错误");
        }
        
        // 登录成功，记录登录状态
        StpUtil.login(user.getId());
        return user;
    }
    
    @Override
    public void register(RegisterDTO registerDTO) {
        // TODO: 实现数据库查询逻辑
        if (getUserByUsername(registerDTO.getUsername()) != null) {
            throw new BusinessException("用户名已存在");
        }
        
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        // 密码加密存储
        user.setPassword(BCrypt.hashpw(registerDTO.getPassword()));
        user.setNickname(registerDTO.getNickname());
        user.setEmail(registerDTO.getEmail());
        
        // TODO: 保存用户到数据库
    }
    
    private User getUserByUsername(String username) {
        // TODO: 实现数据库查询逻辑
        return null;
    }
} 