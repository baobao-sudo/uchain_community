package com.swpu.uchain.community.service;

import com.swpu.uchain.community.entity.User;
import com.swpu.uchain.community.form.LoginForm;
import com.swpu.uchain.community.vo.ResultVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author hobo
 * @description
 */
public interface UserService {

    /**
     * 根据学号获取用户
     * @param stuId
     * @return
     */
    User getUserByStuId(String stuId);

    /**
     * 通过id查找
     * @param id
     * @return
     */
    User getById(Integer id);

    /**
     * 查找全部
     * @return
     */
    List<User> findAll();

    /**
     * 新增
     * @param user
     */
    void add(User user);

    /**
     * 删除
     * @param id
     */
    void delete(Integer id);

    /**
     * 查找一个小组的人员
     * @param id
     * @return
     */
    List<User> findByGroupId(Integer id);

    /**
     * 更新
     * @param user
     */
    void update(User user);


    /**
     * 从token中解析用户
     * @return
     */
    User getCurrentUser();

    /**
     * 登录
     * @param loginForm
     * @param response
     * @return
     */
    ResultVO login(LoginForm loginForm, HttpServletResponse response);

}
