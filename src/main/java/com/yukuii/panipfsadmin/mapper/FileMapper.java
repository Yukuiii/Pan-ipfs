package com.yukuii.panipfsadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yukuii.panipfsadmin.model.entity.File;
import com.yukuii.panipfsadmin.model.vo.FileVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper extends BaseMapper<File> {
    List<FileVO> getAllFilesWithUser();
} 