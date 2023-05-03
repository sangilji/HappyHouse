package com.ssafy.myhouse.controller;

import com.ssafy.myhouse.security.SecurityMember;
import com.ssafy.myhouse.service.houseService.HouseService;
import com.ssafy.myhouse.service.houseService.ReviewService;
import com.ssafy.myhouse.vo.houseVo.Address;
import com.ssafy.myhouse.vo.houseVo.House;
import com.ssafy.myhouse.vo.houseVo.HouseDto;
import com.ssafy.myhouse.vo.houseVo.Review;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class HouseController {

    private final HouseService houseService;
    private final ReviewService reviewService;

    @Description("모든 부동산 정보 조회")
    @GetMapping({"/home"})
    public ResponseEntity<List<House>> selectAll() throws Exception{
        List<House> list= houseService.selectAll();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @Description("주소로 정보 조회")
    @GetMapping("/home/search")
    public ResponseEntity<?> searchByAddress(@ModelAttribute Address address) throws Exception{
        List<HouseDto> houses = houseService.searchByAddress(address);
        return new ResponseEntity<>(houses, HttpStatus.OK);
    }

    @Description("동으로 정보 조회")
    @GetMapping("/home/search/{dong}")
    public ResponseEntity<?> searchByAddress(@PathVariable String dong) throws Exception{
        List<HouseDto> houses = houseService.searchByDong(dong);
        return new ResponseEntity<>(houses, HttpStatus.OK);
    }

    @Description("부동산 하나의 정보 조회+댓글 전체 조회")
    @GetMapping("/home/{no}/{aptCode}")
    public ResponseEntity<?> selectOne(@PathVariable String no,@PathVariable String aptCode) throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("houseInfo", houseService.selectOne(no));
        map.put("review", reviewService.selectAllReview(aptCode));
        return ResponseEntity.ok().body(map);
    }



}
