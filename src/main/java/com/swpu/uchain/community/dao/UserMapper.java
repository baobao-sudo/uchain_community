package com.swpu.uchain.community.dao;

import com.swpu.uchain.community.entity.User;
import java.util.List;

public interface UserMapper {
    /**
     * 通过id查找
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入一条新数据
     * @param record
     * @return
     */
    int insert(User record);

    /**
     * 查找
     * @param id
     * @return
     */
    User selectByPrimaryKey(Integer id);

    /**
     * 查找所有
     * @return
     */
    List<User> selectAll();

    /**
     * 更新通过主键
     * @param record
     * @return
     */
    int updateByPrimaryKey(User record);

    /**
     * 通过学号查找
     * @param stuId
     * @return
     */
    User getUserByStuId(String stuId);

    /**
     * 通过小组查找
     * @param id
     * @return
     */
    List<User> getUserByGroupId(Integer id);
}