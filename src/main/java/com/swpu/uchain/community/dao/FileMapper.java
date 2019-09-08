package com.swpu.uchain.community.dao;

import com.swpu.uchain.community.entity.File;
import java.util.List;

/**
 * @author baobao
 * @date 2019-8-20
 */
public interface FileMapper {
    /**
     * 查找
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入新的数据
     * @param record
     * @return
     */
    int insert(File record);

    /**
     * 通过id查找
     * @param id
     * @return
     */
    File selectByPrimaryKey(Integer id);

    /**
     * 查询全部
     * @return
     */
    List<File> selectAll();

    /**
     * 通过主键更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(File record);
}