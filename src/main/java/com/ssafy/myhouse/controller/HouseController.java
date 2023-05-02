package com.ssafy.myhouse.controller;

import com.ssafy.myhouse.security.SecurityMember;
import com.ssafy.myhouse.service.houseService.HouseService;
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
        map.put("review", houseService.selectAllReview(aptCode));
        return ResponseEntity.ok().body(map);
    }


    @Description("부동산 찜 상자에 넣기")
    @PostMapping("/home/{aptCode}/interestAdd")
    public ResponseEntity<?> interestAdd(@AuthenticationPrincipal SecurityMember member, @PathVariable String aptCode) throws Exception {
        if (member == null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create("/login"));
            return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
        }
        Map<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(member.getId()));
        map.put("aptCode", aptCode);
        int x= houseService.interestAdd(map);
        return new ResponseEntity<>("찜 완료", HttpStatus.OK);
    }

    @Description("찜 목록 보기")
    @GetMapping("/home/interests")
    public ResponseEntity<?> findInterests(@AuthenticationPrincipal SecurityMember member) throws Exception{
        List<HouseDto> houses = houseService.findInterestsByMemberId(member.getId());
        return new ResponseEntity<>(houses, HttpStatus.OK);
    }

//    @Description("부동산 찜 상자에서 빼기")
//    @PutMapping("/home/{aptCode}/interestDelete")
//    public Map<String,String> interestDelete(@PathVariable String aptCode) throws SQLException {
//        int x= houseService.interestDelete(aptCode);
//        Map<String,String> map=new HashMap<>();
//        map.put("result","interestDelete success!");
//        return map;
//    }

    @Description("댓글 입력")
    @PostMapping(value = "/home/{aptCode}")
    public Map<String,String> add(@RequestBody Review review) throws Exception{ // 사용자가 입력한 값을 받아와서 DB에 INSERT

        houseService.insert(review);
        Map<String,String> map=new HashMap<>();
        map.put("result", "review insert success!");
        return map;
    }

    @Description("댓글 수정")
    @PutMapping("/home/{aptCode}")
    public Map<String,String> update(@RequestBody Review review) throws Exception {
        int x= houseService.update(review);
        Map<String,String> map=new HashMap<>();
        map.put("result","review update success!");
        return map;
    }

    @Description("댓글 삭제")
    @DeleteMapping("/home/{aptCode}/{reviewId}")
    public Map<String,String> update(@PathVariable String reviewId) throws Exception {
        int x = houseService.delete(reviewId);
        Map<String, String> map = new HashMap<>();
        map.put("result", "review delete success!");
        return map;
    }
}
