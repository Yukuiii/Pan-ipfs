package com.yukuii.panipfsadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yukuii.panipfsadmin.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    // 通过用户名查询用户
    User selectByUsername(String username);
} 