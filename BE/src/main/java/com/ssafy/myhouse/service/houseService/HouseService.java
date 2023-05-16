package com.ssafy.myhouse.service.houseService;

import com.ssafy.myhouse.vo.houseVo.Address;
import com.ssafy.myhouse.vo.houseVo.House;
import com.ssafy.myhouse.vo.houseVo.HouseDto;
import com.ssafy.myhouse.vo.houseVo.Review;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface HouseService {
    List<House> selectAll() throws Exception; // 모든 집 목록

    House selectOne(String no) throws Exception; // 해당 번호의 글 한 개

    List<HouseDto> searchByAddress(Address address) throws Exception;

    List<HouseDto> searchByDong(String dong) throws Exception;

}
