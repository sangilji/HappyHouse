package com.ssafy.myhouse.mapper;

import com.ssafy.myhouse.vo.houseVo.Review;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface ReviewMapper {

    int insert(Review review) throws SQLException; // 새 댓글 등록

    int delete(String reviewId) throws SQLException; // 해당 번호의 댓글 삭제

    int update(Review r) throws SQLException; //해당 번호의 댓글 수정

    List<Review> selectAllReview(String aptCode) throws SQLException; //모든 댓글 목록
}
