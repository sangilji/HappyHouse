package com.ssafy.myhouse.mapper;


import com.ssafy.myhouse.vo.houseVo.*;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface HouseMapper {

    List<House> selectAll() throws SQLException; // 모든 집 목록

    House selectOne(String no) throws SQLException; // 해당 번호의 글 한 개

    List<HouseDto> searchByDongCode(String dongCode) throws SQLException;//주소 기준으로 검색

    List<HouseDto> searchByDong(String address) throws SQLException;//동 기준으로 검색


    List<HouseDeal> searchHouseDeal(String aptCode) throws SQLException;
    Boolean createHouse(HouseDto houseDto) throws Exception;
    Boolean updateHouseComment(HouseDto houseDto) throws Exception;

}
