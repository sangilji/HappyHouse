package com.ssafy.myhouse.service.houseService;

import com.ssafy.myhouse.mapper.HouseMapper;
import com.ssafy.myhouse.mapper.InterestsMapper;
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
public class InterestsServiceImpl implements InterestsService {

    private final InterestsMapper mapper;

    @Override
    public int addInterest(Map<String,String> map) throws Exception {
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
        System.out.println(mapper.findInterestsByMemberId(id).size());
        return mapper.findInterestsByMemberId(id);
    }
}
