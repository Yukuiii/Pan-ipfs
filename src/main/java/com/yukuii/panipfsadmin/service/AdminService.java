package com.yukuii.panipfsadmin.service;

import com.yukuii.panipfsadmin.model.vo.StatisticsVO;
import com.yukuii.panipfsadmin.model.entity.User;
import com.yukuii.panipfsadmin.model.vo.FileVO;

import java.util.List;

public interface AdminService {
    StatisticsVO getStatistics();
    List<User> getUserList();
    void deleteUser(Long userId);
    List<FileVO> getAllFiles();
    void deleteFile(Long fileId);
} 