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

    int insert(Review review) throws SQLException; // 새 댓글 등록

    int delete(String reviewId) throws SQLException; // 해당 번호의 댓글 삭제

    int update(Review r) throws SQLException; //해당 번호의 댓글 수정

    List<HouseDto> searchByAddress(Address address) throws SQLException;//주소 기준으로 검색

    List<HouseDto> searchByDong(String address) throws SQLException;//동 기준으로 검색

    int interestAdd(Map<String, String> map) throws SQLException;

    int interestDelete(String aptCode) throws SQLException;

    List<Review> selectAllReview(String aptCode) throws SQLException; //모든 댓글 목록

    boolean findInterestByUserIdAndAptCode(Map<String, String> map);

    int updateInterest(Map<String, String> map);


}
