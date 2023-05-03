package com.ssafy.myhouse.service.houseService;

import com.ssafy.myhouse.mapper.HouseMapper;
import com.ssafy.myhouse.mapper.ReviewMapper;
import com.ssafy.myhouse.vo.houseVo.Address;
import com.ssafy.myhouse.vo.houseVo.House;
import com.ssafy.myhouse.vo.houseVo.HouseDto;
import com.ssafy.myhouse.vo.houseVo.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper mapper;

    @Override
    public int insert(Review review) throws SQLException {
        return mapper.insert(review);
    }

    @Override
    public int delete(String reviewId) throws SQLException {
        return mapper.delete(reviewId);
    }

    @Override
    public int update(Review r) throws SQLException {
        return mapper.update(r);
    }

    @Override
    public List<Review> selectAllReview(String aptCode) throws SQLException {
        return mapper.selectAllReview(aptCode);
    }
}
