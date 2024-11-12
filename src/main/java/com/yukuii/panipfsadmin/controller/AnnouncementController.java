package com.yukuii.panipfsadmin.controller;

import com.yukuii.panipfsadmin.common.result.Result;
import com.yukuii.panipfsadmin.model.entity.Announcement;
import com.yukuii.panipfsadmin.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/announcements")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @GetMapping
    public Result<?> listAnnouncements() {
        return Result.success(announcementService.listAnnouncements());
    }

    @PostMapping
    public Result<?> addAnnouncement(@RequestBody Announcement announcement) {
        announcementService.addAnnouncement(announcement);
        return Result.success();
    }

    @PutMapping("/{id}")
    public Result<?> updateAnnouncement(@PathVariable Long id, @RequestBody Announcement announcement) {
        announcement.setId(id);
        announcementService.updateAnnouncement(announcement);
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result<?> deleteAnnouncement(@PathVariable Long id) {
        announcementService.deleteAnnouncement(id);
        return Result.success();
    }
} 