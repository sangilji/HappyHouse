package com.ssafy.myhouse.controller;

import com.ssafy.myhouse.service.houseService.HouseService;
import com.ssafy.myhouse.vo.houseVo.House;
import com.ssafy.myhouse.vo.houseVo.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class HouseController {

    @Autowired
    HouseService service;

    @Description("모든 부동산 정보 조회")
    @GetMapping("/home")
    public ResponseEntity<List<House>> selectAll() throws Exception{
        List<House> list=service.selectAll();
        ResponseEntity<List<House>> rb=
                new ResponseEntity<List<House>>(list, HttpStatus.OK);
        System.out.println(rb);
        return rb;
    }

    @Description("부동산 하나의 정보 조회+댓글 전체 조회")
    @GetMapping("/home/{aptCode}")
    public ResponseEntity<?> selectOne(@PathVariable String aptCode) throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("houseInfo",service.selectOne(aptCode));
        map.put("review",service.selectAllReview(aptCode));
        return ResponseEntity.ok().body(map);
    }


    @Description("부동산 찜 상자에 넣기")
    @PutMapping("/home/{aptCode}/interestAdd")
    public Map<String,String> interestAdd(@PathVariable String aptCode) throws SQLException {
        int x=service.interestAdd(aptCode);
        Map<String,String> map=new HashMap<>();
        map.put("result","interestAdd success!");
        return map;
    }

    @Description("부동산 찜 상자에서 빼기")
    @PutMapping("/home/{aptCode}/interestDelete")
    public Map<String,String> interestDelete(@PathVariable String aptCode) throws SQLException {
        int x=service.interestDelete(aptCode);
        Map<String,String> map=new HashMap<>();
        map.put("result","interestDelete success!");
        return map;
    }

    @Description("댓글 입력")
    @PostMapping(value = "/home/{aptCode}")
    public Map<String,String> add(@RequestBody Review review) throws Exception{ // 사용자가 입력한 값을 받아와서 DB에 INSERT
        service.insert(review);
        Map<String,String> map=new HashMap<>();
        map.put("result", "review insert success!");
        return map;
    }

    @Description("댓글 수정")
    @PutMapping("/home/{aptCode}")
    public Map<String,String> update(@RequestBody Review review) throws SQLException {
        int x=service.update(review);
        Map<String,String> map=new HashMap<>();
        map.put("result","review update success!");
        return map;
    }

    @Description("댓글 삭제")
    @DeleteMapping("/home/{aptCode}/{reviewId}")
    public Map<String,String> update(@PathVariable String reviewId) throws SQLException {
        int x = service.delete(reviewId);
        Map<String, String> map = new HashMap<>();
        map.put("result", "review delete success!");
        return map;
    }
}
