package com.ssafy.myhouse.controller;

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
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @Description("댓글 입력")
    @PostMapping(value = "/home/review/{aptCode}")
    public Map<String,String> add(@RequestBody Review review) throws Exception{ // 사용자가 입력한 값을 받아와서 DB에 INSERT
        reviewService.insert(review);
        Map<String,String> map=new HashMap<>();
        map.put("result", "review insert success!");
        return map;
    }

    @Description("댓글 수정")
    @PutMapping("/home/{aptCode}/{reviewId}")
    public Map<String,String> update(@RequestBody Review review) throws Exception {
        int x= reviewService.update(review);
        Map<String,String> map=new HashMap<>();
        map.put("result","review update success!");
        return map;
    }

    @Description("댓글 삭제")
    @DeleteMapping("/home/{aptCode}/{reviewId}")
    public Map<String,String> update(@PathVariable String reviewId) throws Exception {
        int x = reviewService.delete(reviewId);
        Map<String, String> map = new HashMap<>();
        map.put("result", "review delete success!");
        return map;
    }
}
