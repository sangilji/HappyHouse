package com.ssafy.myhouse.service.houseService;

import com.ssafy.myhouse.vo.houseVo.Address;
import com.ssafy.myhouse.vo.houseVo.House;
import com.ssafy.myhouse.vo.houseVo.HouseDeal;
import com.ssafy.myhouse.vo.houseVo.HouseDto;

import java.util.List;

public interface HouseService {
    List<House> selectAll() throws Exception; // 모든 집 목록

    House selectOne(String no) throws Exception; // 해당 번호의 글 한 개

    List<HouseDto> searchByDongCode(String dongCode) throws Exception;

    List<HouseDto> searchByDong(String dong) throws Exception;

    List<HouseDeal> searchHouseDeal(String aptCode) throws Exception;
}
