package com.ssafy.myhouse.controller;

import com.ssafy.myhouse.service.JwtService;
import com.ssafy.myhouse.service.houseService.InterestsService;
import com.ssafy.myhouse.vo.Member;
import com.ssafy.myhouse.vo.houseVo.Address;
import com.ssafy.myhouse.vo.houseVo.House;
import com.ssafy.myhouse.vo.houseVo.HouseDto;
import com.ssafy.myhouse.vo.houseVo.Review;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class InterestsController {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final InterestsService interestsService;
    private final JwtService jwtService;
    @Description("부동산 찜 상자에 넣기")
    @PostMapping("/home/{aptCode}/{id}/interestAdd")
    public ResponseEntity<?> interestAdd(@PathVariable int id, @PathVariable String aptCode, HttpServletRequest request) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        Map<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(id));
        map.put("aptCode", aptCode);
        if (jwtService.checkToken(request.getHeader("access-token"))) {
            log.info("사용 가능한 토큰!!!");
            try {
                int x= interestsService.addInterest(map);
                resultMap.put("message",SUCCESS);
            } catch (Exception e) {
                log.error("정보조회 실패 : {}", e);
                resultMap.put("message", e.getMessage());
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } else {
            log.error("사용 불가능 토큰!!!");
            resultMap.put("message", FAIL);
            status = HttpStatus.UNAUTHORIZED;
        }

        resultMap.put("data", map);
        return new ResponseEntity<>(resultMap, status);

    }

    @Description("찜 목록 보기")
    @GetMapping("/interests/{id}")
    public ResponseEntity<?> findInterests(@PathVariable int id,HttpServletRequest request ) throws Exception{
//        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.OK;
        List<HouseDto> houses = interestsService.findInterestsByMemberId(id);
//        resultMap.put("message",SUCCESS);
//        resultMap.put("data",houses);
//        System.out.println(houses.);
        return new ResponseEntity<List<HouseDto>>(interestsService.findInterestsByMemberId(id),status);
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
