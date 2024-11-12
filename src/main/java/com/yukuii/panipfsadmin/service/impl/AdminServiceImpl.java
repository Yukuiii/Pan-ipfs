package com.yukuii.panipfsadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yukuii.panipfsadmin.common.exception.BusinessException;
import com.yukuii.panipfsadmin.mapper.FileMapper;
import com.yukuii.panipfsadmin.mapper.UserMapper;
import com.yukuii.panipfsadmin.model.vo.StatisticsVO;
import com.yukuii.panipfsadmin.model.entity.File;
import com.yukuii.panipfsadmin.model.entity.User;
import com.yukuii.panipfsadmin.model.vo.FileVO;
import com.yukuii.panipfsadmin.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserMapper userMapper;
    private final FileMapper fileMapper;

    @Override
    public StatisticsVO getStatistics() {
        StatisticsVO statistics = new StatisticsVO();
        
        // 获取用户总数（不包括管理员）
        statistics.setUserCount(userMapper.selectCount(new LambdaQueryWrapper<User>()
                .ne(User::getRole, "ADMIN")));
        
        // 获取文件总数
        statistics.setFileCount(fileMapper.selectCount(null));
        
        // 获取总存储空间
        Long totalSize = fileMapper.selectList(null).stream()
                .mapToLong(file -> Long.parseLong(file.getFileSize()))
                .sum();
        statistics.setTotalSize(totalSize);
        
        return statistics;
    }

    @Override
    public List<User> getUserList() {
        return userMapper.selectList(null);
    }

    @Override
    @Transactional
    public void deleteUser(Long userId) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        if ("ADMIN".equals(user.getRole())) {
            throw new BusinessException("不能删除管理员账号");
        }
        
        // 删除用户的所有文件
        fileMapper.delete(new LambdaQueryWrapper<File>()
                .eq(File::getUserId, userId));
        
        // 删除用户
        userMapper.deleteById(userId);
    }

    @Override
    public List<FileVO> getAllFiles() {
        return fileMapper.getAllFilesWithUser();
    }

    @Override
    public void deleteFile(Long fileId) {
        File file = fileMapper.selectById(fileId);
        if (file == null) {
            throw new BusinessException("文件不存在");
        }
        fileMapper.deleteById(fileId);
    }
} 