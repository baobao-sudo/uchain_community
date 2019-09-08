package com.swpu.uchain.community.controller;

import com.swpu.uchain.community.entity.Group;
import com.swpu.uchain.community.entity.User;
import com.swpu.uchain.community.exception.GlobalException;
import com.swpu.uchain.community.form.UserForm;
import com.swpu.uchain.community.service.FileService;
import com.swpu.uchain.community.service.GroupService;
import com.swpu.uchain.community.service.UserService;
import com.swpu.uchain.community.service.impl.FileServiceImpl;
import com.swpu.uchain.community.util.ResultVOUtil;
import com.swpu.uchain.community.vo.GroupVO;
import com.swpu.uchain.community.vo.ResultVO;
import com.swpu.uchain.community.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author baobao
 * @date 2019-8-21
 */
@RestController
@RequestMapping("/admin/member")
public class UserAdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private FileServiceImpl fileservice;

    @GetMapping("/list")
    public ResultVO list() {
        List<User> userList = userService.findAll();
        List<Group> groupList = groupService.findAll();

        List<GroupVO> groupVOList = new ArrayList<>();
        for (Group group:groupList){
            GroupVO groupVO = new GroupVO();
            groupVO.setId(group.getId());
            groupVO.setGroupName(group.getGroupName());
            List<UserVO> userVOList = new ArrayList<>();
            for(User user:userList){
                if(user.getGroupId().equals(group.getId())){
                    UserVO userVO = new UserVO();
                    String picURL = new String();
                    if(user.getUserPicId() != null) {
                        picURL = fileservice.findById(user.getUserPicId()).getFileUrl();
                    }else{
                        picURL = null;
                    }
                    BeanUtils.copyProperties(user,userVO);
                    userVO.setUserPicId(picURL);
                    userVOList.add(userVO);
                }
            }
            groupVO.setMember(userVOList);
            groupVOList.add(groupVO);
        }
        return ResultVOUtil.success(groupVOList);
    }


    @PostMapping("/add")
    public ResultVO add(UserForm userForm){
        User user = new User();
        try {
            BeanUtils.copyProperties(userForm, user);
            user.setPassword(new BCryptPasswordEncoder().encode(userForm.getPassword()));
            if(fileservice.findById(userForm.getUserPicId()) != null){
                user.setUserPicId(fileservice.findById(userForm.getUserPicId()).getId());
            }
            user.setId(null);
            userService.add(user);
        }catch (GlobalException e){
            return ResultVOUtil.error(e.getResultEnum());
        }

        return ResultVOUtil.success();
    }

    @PostMapping("/delete")
    public ResultVO delete(@RequestParam("id") Integer id ){
        try {
            userService.delete(id);
        }catch (GlobalException e){
            return ResultVOUtil.error(e.getResultEnum());
        }
        return ResultVOUtil.success();
    }

    @PostMapping("/change")
    public  ResultVO change(UserForm userForm){
        User user = new User();
        try {
            BeanUtils.copyProperties(userForm, user);
            if(userForm.getPassword() == null){
                user.setPassword(userService.getById(userForm.getId()).getPassword());
            }else{
                user.setPassword(new BCryptPasswordEncoder().encode(userForm.getPassword()));
            }
            userService.update(user);
        }catch (GlobalException e){
            return ResultVOUtil.error(e.getResultEnum());
        }
      return ResultVOUtil.success();
    }


}
