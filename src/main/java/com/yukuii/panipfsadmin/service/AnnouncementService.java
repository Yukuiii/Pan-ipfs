package com.yukuii.panipfsadmin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yukuii.panipfsadmin.model.entity.Announcement;
import java.util.List;

public interface AnnouncementService extends IService<Announcement> {
    List<Announcement> listAnnouncements();
    void addAnnouncement(Announcement announcement);
    void updateAnnouncement(Announcement announcement);
    void deleteAnnouncement(Long id);
} 