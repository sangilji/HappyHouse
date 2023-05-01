package com.ssafy.myhouse.service.houseService;

import com.ssafy.myhouse.vo.houseVo.House;
import com.ssafy.myhouse.vo.houseVo.Review;

import java.sql.SQLException;
import java.util.List;

public interface HouseService {
    List<House> selectAll() throws SQLException; // 모든 집 목록

    House selectOne(String aptCode) throws SQLException; // 해당 번호의 글 한 개

    int insert(Review review) throws SQLException; // 새 댓글 등록

    int delete(String reviewId) throws SQLException; // 해당 번호의 댓글 삭제

    int update(Review r)throws SQLException; //해당 번호의 댓글 수정

    List<House> findByAddress(String address) throws SQLException;

    int interestAdd(String aptCode) throws SQLException; //관심매물 찜 추가
    int interestDelete(String aptCode) throws SQLException; //관심매물 찜 삭제

    List<Review> selectAllReview(String aptCode) throws SQLException; //모든 댓글 목록

}
