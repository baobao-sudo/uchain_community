package com.swpu.uchain.community.controller;

import com.swpu.uchain.community.entity.Group;
import com.swpu.uchain.community.exception.GlobalException;
import com.swpu.uchain.community.service.GroupService;
import com.swpu.uchain.community.util.ResultVOUtil;
import com.swpu.uchain.community.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/group")
public class GroupAdminController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/list")
    public ResultVO list(){
        List<Group> groupList = groupService.findAll();
        return ResultVOUtil.success(groupList);
    }
    @PostMapping("/add")
    public ResultVO add(@RequestParam("groupName") String groupName){
        Group group = new Group();
        group.setGroupName(groupName);
        try {
            groupService.add(group);
        }catch (GlobalException e){
            return ResultVOUtil.error(e.getResultEnum());
        }
        return ResultVOUtil.success();

    }
    @PostMapping("/delete")
    public ResultVO delete(@RequestParam("id")Integer id){
        try {
            groupService.deleById(id);
        }catch (GlobalException e){
            return ResultVOUtil.error(e.getResultEnum());
        }
        return ResultVOUtil.success();
    }
    @PostMapping("/change")
    public ResultVO change(@RequestParam("id")Integer id,@RequestParam("groupName") String groupName){
        Group group = groupService.findById(id);
        group.setGroupName(groupName);
        try {
            groupService.update(group);
        }catch (GlobalException e){
            return ResultVOUtil.error(e.getResultEnum());
        }
        return ResultVOUtil.success();

    }

}
