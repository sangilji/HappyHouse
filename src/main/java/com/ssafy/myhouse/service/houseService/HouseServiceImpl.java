package com.ssafy.myhouse.service.houseService;

import com.ssafy.myhouse.mapper.HouseMapper;
import com.ssafy.myhouse.vo.houseVo.House;
import com.ssafy.myhouse.vo.houseVo.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class HouseServiceImpl implements HouseService{
    @Autowired
    HouseMapper mapper;


    @Override
    public List<House> selectAll() throws SQLException {
        return mapper.selectAll();
    }

    @Override
    public House selectOne(String no) throws SQLException {
        return mapper.selectOne(no);
    }

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
    public List<House> findByAddress(String address) throws SQLException {
        return mapper.findByAddress(address);
    }

    @Override
    public int interestAdd(String aptCode) throws SQLException {
        return mapper.interestAdd(aptCode);
    }

    @Override
    public int interestDelete(String aptCode) throws SQLException {
        return mapper.interestDelete(aptCode);
    }

    @Override
    public List<Review> selectAllReview(String aptCode) throws SQLException {
        return mapper.selectAllReview(aptCode);
    }
}
