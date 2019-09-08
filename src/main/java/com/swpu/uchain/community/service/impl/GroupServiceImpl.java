package com.swpu.uchain.community.service.impl;

import com.swpu.uchain.community.dao.GroupMapper;
import com.swpu.uchain.community.dao.UserMapper;
import com.swpu.uchain.community.entity.Group;
import com.swpu.uchain.community.entity.User;
import com.swpu.uchain.community.enums.ResultEnum;
import com.swpu.uchain.community.exception.GlobalException;
import com.swpu.uchain.community.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    public Group findById(Integer id) {
        return groupMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Group> findAll() {
        return groupMapper.selectAll();
    }

    @Override
    public List<Group> findByGroupIdIn(List<Integer> groupIdList) {
        List<Group> groupList = new ArrayList<>();
        for(Integer id:groupIdList){
            Group group = groupMapper.selectByPrimaryKey(id);
            groupList.add(group);
        }
        return groupList;
    }

    @Override
    public void deleById(Integer id) {
        if(groupMapper.selectByPrimaryKey(id) == null){
            log.error("该id的小组不存在,id={}",id);
            throw new GlobalException(ResultEnum.GROUP_NOT_EXIST);
        }
        List<User> userList = userMapper.getUserByGroupId(id);
        for(User user:userList){
            userMapper.deleteByPrimaryKey(user.getId());
        }
        groupMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Group group) {
        if(groupMapper.getByGroupName(group.getGroupName()) != null){
            log.error("该小组名已存在,groupName={}",group.getGroupName());
            throw new GlobalException(ResultEnum.GROUP_NAME_ALREADY_EXIST);
        }
        groupMapper.updateByPrimaryKey(group);
    }

    @Override
    public void add(Group group) {
        if(groupMapper.getByGroupName(group.getGroupName()) != null){
            log.error("该小组名已存在,groupName={}",group.getGroupName());
            throw new GlobalException(ResultEnum.GROUP_NAME_ALREADY_EXIST);
        }
        groupMapper.insert(group);
    }

    @Override
    public Group findByGroupName(String groupName) {

        return groupMapper.getByGroupName(groupName);
    }
}
