package com.swpu.uchain.community.service;

import com.swpu.uchain.community.entity.Group;

import java.util.List;

public interface GroupService {

    /**
     * 通过id查找
     * @param id
     * @return
     */
    Group findById(Integer id);

    /**
     * 查找所有
     * @return
     */
    List<Group> findAll();

    /**
     * 查找数组的目标
     * @param groupIdList
     * @return
     */
    List<Group> findByGroupIdIn(List<Integer> groupIdList);

    /**
     * 删除通过id
     * @param id
     */
    void deleById(Integer id);

    /**
     * 更新
     * @param group
     */
    void update(Group group);

    /**
     * 增加
     * @param group
     */
    void add(Group group);

    /**
     * 通过小组名进行查找
     * @param groupName
     * @return
     */
    Group findByGroupName(String groupName);

}
