package com.ssafy.myhouse.mapper;


import com.ssafy.myhouse.vo.houseVo.Address;
import com.ssafy.myhouse.vo.houseVo.House;
import com.ssafy.myhouse.vo.houseVo.HouseDto;
import com.ssafy.myhouse.vo.houseVo.Review;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface HouseMapper {

    List<House> selectAll() throws SQLException; // 모든 집 목록

    House selectOne(String no) throws SQLException; // 해당 번호의 글 한 개

    List<HouseDto> searchByAddress(Address address) throws SQLException;//주소 기준으로 검색

    List<HouseDto> searchByDong(String address) throws SQLException;//동 기준으로 검색



}
