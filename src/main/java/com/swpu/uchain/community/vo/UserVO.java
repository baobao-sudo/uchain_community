package com.swpu.uchain.community.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserVO {

    private Integer id;

    private String stuId;
    @JsonProperty("name")
    private String trueName;

    private String password;

    private Integer role;
    @JsonProperty("description")
    private String userDesc;
    @JsonProperty("picURL")
    private String userPicId;

    private Integer unqualifyTimes;

    private Integer groupId;

    private Integer qualifyTime;

}
