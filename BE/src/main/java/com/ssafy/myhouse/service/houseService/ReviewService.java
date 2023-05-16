package com.ssafy.myhouse.service.houseService;

import com.ssafy.myhouse.vo.houseVo.Address;
import com.ssafy.myhouse.vo.houseVo.House;
import com.ssafy.myhouse.vo.houseVo.HouseDto;
import com.ssafy.myhouse.vo.houseVo.Review;

import java.util.List;
import java.util.Map;

public interface ReviewService {

    int insert(Review review) throws Exception; // 새 댓글 등록

    int delete(String reviewId) throws Exception; // 해당 번호의 댓글 삭제

    int update(Review r)throws Exception; //해당 번호의 댓글 수정


    List<Review> selectAllReview(String aptCode) throws Exception; //모든 댓글 목록

}
