package com.swpu.uchain.community.service.impl;

import com.swpu.uchain.community.dao.FileMapper;
import com.swpu.uchain.community.entity.File;
import com.swpu.uchain.community.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;


    @Override
    public File findById(Integer fileId) {
        return fileMapper.selectByPrimaryKey(fileId);
    }

    @Override
    public List<File> findAll() {
        return fileMapper.selectAll();
    }

    @Override
    public void deleById(Integer fileId) {
        fileMapper.deleteByPrimaryKey(fileId);
    }


    @Override
    public void update(File file) {
        fileMapper.updateByPrimaryKey(file);
    }

    @Override
    public void add(File file) {
        fileMapper.insert(file);
    }
}
