package com.ssafy.myhouse.service.houseService;

import com.ssafy.myhouse.vo.houseVo.Address;
import com.ssafy.myhouse.vo.houseVo.House;
import com.ssafy.myhouse.vo.houseVo.HouseDto;
import com.ssafy.myhouse.vo.houseVo.Review;

import java.util.List;
import java.util.Map;

public interface InterestsService {

    int addInterest(Map<String, String> map) throws Exception; //관심매물 찜 추가


    List<HouseDto> findInterestsByMemberId(int id) throws Exception;
}
