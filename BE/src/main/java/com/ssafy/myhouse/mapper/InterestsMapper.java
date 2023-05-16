package com.ssafy.myhouse.mapper;

import com.ssafy.myhouse.vo.houseVo.HouseDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface InterestsMapper {

    int addInterest(Map<String, String> map) throws SQLException;

    boolean existsInterestByUserIdAndAptCode(Map<String, String> map);

    int updateInterest(Map<String, String> map);

    List<HouseDto> findInterestsByMemberId(int id) throws SQLException;
}
