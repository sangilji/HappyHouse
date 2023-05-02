package com.ssafy.myhouse.service.houseService;

import com.ssafy.myhouse.mapper.HouseMapper;
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
public class HouseServiceImpl implements HouseService {

    private final HouseMapper mapper;

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
    public List<HouseDto> searchByAddress(Address address) throws SQLException {
        return mapper.searchByAddress(address);
    }

    @Override
    public List<HouseDto> searchByDong(String dong) throws SQLException {
        return mapper.searchByDong(dong);
    }

    @Override
    public int interestAdd(Map<String,String> map) throws Exception {
        if (existsInterestByUserIdAndAptCode(map)){
            return mapper.updateInterest(map);
        }
        return mapper.addInterest(map);
    }

    private boolean existsInterestByUserIdAndAptCode(Map<String, String> map) {
        return mapper.existsInterestByUserIdAndAptCode(map);
    }

    @Override
    public List<HouseDto> findInterestsByMemberId(int id) throws Exception {
        return mapper.findInterestsByMemberId(id);
    }
//    @Override
//    public int interestDelete(String aptCode) throws SQLException {
//        return mapper.interestDelete(aptCode);

//    }

    @Override
    public List<Review> selectAllReview(String aptCode) throws SQLException {
        return mapper.selectAllReview(aptCode);
    }
}
