package com.swpu.uchain.community.service;

import com.swpu.uchain.community.entity.File;

import java.util.List;

public interface FileService {

    /**
     * @param fileId
     * @return
     */
    File findById(Integer fileId);

    /**
     * @return
     */
    List<File> findAll();

    /**
     * @param fileId
     */
    void deleById(Integer fileId);

    /**
     * @param file
     */
    void update(File file);

    /**
     * @param file
     */
    void add(File file);

}
