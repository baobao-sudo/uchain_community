package com.swpu.uchain.community.vo;

import com.swpu.uchain.community.entity.User;
import lombok.Data;

import java.util.List;

/**
 * @author baobao
 * @date 2019-8-21
 */
@Data
public class GroupVO {

    private Integer id;

    private String groupName;

    List<UserVO> member;
}
