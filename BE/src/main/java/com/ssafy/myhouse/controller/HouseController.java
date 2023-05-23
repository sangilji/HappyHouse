package com.ssafy.myhouse.controller;

import com.ssafy.myhouse.service.houseService.HouseService;
import com.ssafy.myhouse.service.houseService.ReviewService;
import com.ssafy.myhouse.vo.houseVo.House;
import com.ssafy.myhouse.vo.houseVo.HouseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> searchByDongCode(@RequestParam String dongCode) throws Exception{
        List<HouseDto> houses = houseService.searchByDongCode(dongCode);
        System.out.println(houses.size());
        return new ResponseEntity<>(houses, HttpStatus.OK);
    }

    @Description("동으로 정보 조회")
    @GetMapping("/home/search/{dong}")
    public ResponseEntity<?> searchByDong(@PathVariable String dong) throws Exception{
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

    @Description("거래가 조회")
    @GetMapping("/home/housedeal/{aptCode}")
    public ResponseEntity<?> getHouseDeal(@PathVariable String aptCode) throws Exception{
        return new ResponseEntity<>(houseService.searchHouseDeal(aptCode),HttpStatus.OK);
    }

    @Description("거래가 조회")
    @GetMapping("/home/review/{aptCode}")
    public ResponseEntity<?> getReview(@PathVariable String aptCode) throws Exception{
        return new ResponseEntity<>(reviewService.selectAllReview(aptCode),HttpStatus.OK);
    }


    @Description("집매물 추가")
    @PostMapping("/createHouse")
    public ResponseEntity<String> createHouse(@RequestBody HouseDto houseDto) throws Exception{
        System.out.println(houseDto.getAptCode());
        if(houseService.createHouse(houseDto)){
            System.out.println(houseDto.getHousecomment());
            return new ResponseEntity<String>("success",HttpStatus.OK);
        }
        return new ResponseEntity<String>("fail",HttpStatus.NO_CONTENT);
    }
}
