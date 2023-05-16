package com.ssafy.myhouse.vo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ModifyMemberDto {

    private String userId;
    private String password;
    private String email;
    private String name;
    private String address;
    private String number;
}
