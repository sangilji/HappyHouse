package com.ssafy.myhouse.service.houseService;

import com.ssafy.myhouse.mapper.HouseMapper;
import com.ssafy.myhouse.vo.houseVo.*;
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
    public List<HouseDto> searchByDongCode(String dongCode) throws SQLException {
        return mapper.searchByDongCode(dongCode);
    }

    @Override
    public List<HouseDto> searchByDong(String dong) throws SQLException {
        return mapper.searchByDong(dong);
    }

    @Override
    public List<HouseDeal> searchHouseDeal(String aptCode) throws Exception {
        return mapper.searchHouseDeal(aptCode);
    }

    @Override
    public Boolean createHouse(HouseDto houseDto) throws Exception {
        return mapper.createHouse(houseDto);
    }

    @Override
    public Boolean updateHouseComment(HouseDto houseDto) throws Exception {
        return mapper.updateHouseComment(houseDto);
    }


}
