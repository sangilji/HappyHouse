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

    int insert(Review review) throws Exception; // 새 댓글 등록

    int delete(String reviewId) throws Exception; // 해당 번호의 댓글 삭제

    int update(Review r)throws Exception; //해당 번호의 댓글 수정

    List<HouseDto> searchByAddress(Address address) throws Exception;

    List<HouseDto> searchByDong(String dong) throws Exception;

    int interestAdd(Map<String,String> map) throws Exception; //관심매물 찜 추가
//    int interestDelete(String aptCode) throws SQLException; //관심매물 찜 삭제

    List<Review> selectAllReview(String aptCode) throws Exception; //모든 댓글 목록

    List<HouseDto> findInterestsByMemberId(int id) throws Exception;
}
