package com.ssafy.myhouse.vo.houseVo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class House {
    private String aptCode;
    private String dongCode;
    private String aptName;
    private String jibun;
    private String lng; //위도
    private String lat; //경도
    private String housecomment;
}
