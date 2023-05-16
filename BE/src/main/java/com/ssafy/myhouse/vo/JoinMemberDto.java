package com.ssafy.myhouse.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JoinMemberDto {

    private int id;

    //    @NotEmpty
//    @Length(min = 8, max = 12)
    private String userId;

    //    @NotEmpty
//    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$", message = "대소문자와 숫자 특수기호가 필수로 들어가야 합니다.")
    private String password;

    //    @Email
    private String email;

    //    @NotEmpty
//    @Pattern(regexp = "^[가-힣]{3,5}$")
    private String name;
    private String address;
    private String number;
    //    @NotEmpty
    private String role;
}
