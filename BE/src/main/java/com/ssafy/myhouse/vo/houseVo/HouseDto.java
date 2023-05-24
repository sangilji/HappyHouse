package com.ssafy.myhouse.vo.houseVo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HouseDto {
    private int dealAmount;
    private double area;
    private int floor;
    private String buildYear;
    private String aptCode;
    private String dongCode;
    private double lat;
    private double lng;
    private String apartmentName;
    private String address;
    private String housecomment;
    private String jibun;
    private boolean flag;
}
