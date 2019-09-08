package com.swpu.uchain.community.dao;

import com.swpu.uchain.community.entity.Group;
import java.util.List;

/**
 * @author baobao
 * @date 2019-8-20
 */
public interface GroupMapper {
    /**
     * 删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入
     * @param record
     * @return
     */
    int insert(Group record);

    /**
     * 查找
     * @param id
     * @return
     */
    Group selectByPrimaryKey(Integer id);



    /**
     * 查询全部
     * @return
     */
    List<Group> selectAll();

    /**
     * 更新
     * @param record
     * @return
     */
    int updateByPrimaryKey(Group record);

    /**
     * 通过名字查找
     * @param groupName
     * @return
     */
    Group getByGroupName(String groupName);
}