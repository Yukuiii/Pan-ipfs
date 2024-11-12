package com.yukuii.panipfsadmin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yukuii.panipfsadmin.mapper.AnnouncementMapper;
import com.yukuii.panipfsadmin.model.entity.Announcement;
import com.yukuii.panipfsadmin.service.AnnouncementService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServiceImpl extends ServiceImpl<AnnouncementMapper, Announcement> implements AnnouncementService {

    @Override
    public List<Announcement> listAnnouncements() {
        LambdaQueryWrapper<Announcement> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Announcement::getCreateTime);
        return list(wrapper);
    }

    @Override
    public void addAnnouncement(Announcement announcement) {
        save(announcement);
    }

    @Override
    public void updateAnnouncement(Announcement announcement) {
        updateById(announcement);
    }

    @Override
    public void deleteAnnouncement(Long id) {
        removeById(id);
    }
} 