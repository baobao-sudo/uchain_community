package com.swpu.uchain.community.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.org.glassfish.gmbal.Description;
import lombok.Data;

@Data
public class UserVO1 {

    private Integer id;
    @JsonProperty("name")
    private String trueName;
    @JsonProperty("description")
    private String userDesc;
    @JsonProperty("picURL")
    private String userPicId;

}
