package com.ssafy.myhouse.service;

import com.ssafy.myhouse.mapper.MapMapper;
import com.ssafy.myhouse.vo.SidoGugunCodeDto;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MapService {

    private final SqlSession sqlSession;

    public List<SidoGugunCodeDto> getSido() throws Exception {
        return sqlSession.getMapper(MapMapper.class).getSido();
    }

    public List<SidoGugunCodeDto> getGugun(String sidoCode) throws Exception {
        return sqlSession.getMapper(MapMapper.class).getGugun(sidoCode);
    }

    public List<SidoGugunCodeDto> getDong(Map<String,String> map) throws Exception {
        return sqlSession.getMapper(MapMapper.class).getDong(map);
    }

}
