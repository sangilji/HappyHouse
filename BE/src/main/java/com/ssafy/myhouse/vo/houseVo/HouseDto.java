package com.ssafy.myhouse.vo.houseVo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HouseDto {
    private int dealAmount;
    private double area;
    private int floor;
    private Long aptCode;
    private double lat;
    private double lng;
    private String apartmentName;
    private String address;
}
