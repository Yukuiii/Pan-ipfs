package com.yukuii.panipfsadmin.service;

import com.yukuii.panipfsadmin.entity.User;
import com.yukuii.panipfsadmin.model.dto.LoginDTO;
import com.yukuii.panipfsadmin.model.dto.RegisterDTO;

public interface UserService {
    User login(LoginDTO loginDTO);
    void register(RegisterDTO registerDTO);
} 