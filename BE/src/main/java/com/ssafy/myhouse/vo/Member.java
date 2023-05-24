package com.ssafy.myhouse.vo;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member {

    private int id;
    private String userId;
    private String password;
    private String email;
    private String name;
    private String address;
    private String number;
    private String role;
}
