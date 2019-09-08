package com.swpu.uchain.community.vo;

import lombok.Data;

import java.util.List;
@Data
public class GroupVO1 {

    private Integer id;

    private String groupName;

    List<UserVO1> member;
}
