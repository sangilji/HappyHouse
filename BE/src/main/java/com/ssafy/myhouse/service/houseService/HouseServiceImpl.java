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
    public List<HouseDto> searchByAddress(Address address) throws SQLException {
        return mapper.searchByAddress(address);
    }

    @Override
    public List<HouseDto> searchByDong(String dong) throws SQLException {
        return mapper.searchByDong(dong);
    }
}
