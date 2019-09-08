package com.swpu.uchain.community.form;

import lombok.Data;

/**
 * @author baobao
 * @date 2019-8-23
 */
@Data
public class UserForm {

    private Integer id;

    private String stuId;

    private String trueName;

    private String password;

    private Integer role;

    private String userDesc;

    private Integer userPicId;

    private Integer unqualifyTimes;

    private Integer groupId;

    private Integer qualifyTime;
}
