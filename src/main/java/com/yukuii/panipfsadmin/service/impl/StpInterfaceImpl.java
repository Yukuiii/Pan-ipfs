package com.yukuii.panipfsadmin.service.impl;

import java.util.Arrays;
import java.util.List;

import cn.dev33.satoken.stp.StpInterface;
import jakarta.annotation.Resource;

import com.yukuii.panipfsadmin.model.entity.User;
import com.yukuii.panipfsadmin.mapper.UserMapper;

import org.springframework.stereotype.Component;

@Component
public class StpInterfaceImpl implements StpInterface {



    @Resource
    private UserMapper userMapper;

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        User user = userMapper.selectById(Long.parseLong(loginId.toString()));
        return Arrays.asList(user.getRole());
    }

}
