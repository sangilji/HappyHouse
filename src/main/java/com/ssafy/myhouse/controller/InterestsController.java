package com.ssafy.myhouse.controller;

import com.ssafy.myhouse.security.SecurityMember;
import com.ssafy.myhouse.service.houseService.InterestsService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class InterestsController {

    private final InterestsService interestsService;

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
        int x= interestsService.addInterest(map);
        return new ResponseEntity<>("찜 완료", HttpStatus.OK);
    }

    @Description("찜 목록 보기")
    @GetMapping("/home/interests")
    public ResponseEntity<?> findInterests(@AuthenticationPrincipal SecurityMember member) throws Exception{
        List<HouseDto> houses = interestsService.findInterestsByMemberId(member.getId());
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

}
