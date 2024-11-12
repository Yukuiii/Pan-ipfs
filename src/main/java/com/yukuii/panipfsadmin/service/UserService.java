package com.yukuii.panipfsadmin.service;

import com.yukuii.panipfsadmin.model.entity.User;
import com.yukuii.panipfsadmin.model.dto.LoginDTO;
import com.yukuii.panipfsadmin.model.dto.RegisterDTO;
import com.yukuii.panipfsadmin.model.vo.LoginResponseVo;
public interface UserService {
    LoginResponseVo login(LoginDTO loginDTO);
    void register(RegisterDTO registerDTO);
    User getUserInfo(Long userId);
    User getUserByUsername(String username);
    void addUser(User user);
} 