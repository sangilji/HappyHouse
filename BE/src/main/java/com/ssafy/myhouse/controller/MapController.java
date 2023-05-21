package com.ssafy.myhouse.controller;

import com.ssafy.myhouse.service.MapService;
import com.ssafy.myhouse.vo.SidoGugunCodeDto;
import com.ssafy.myhouse.vo.houseVo.House;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/map")
@Slf4j
public class MapController {

    private final MapService mapService;

    @GetMapping("/sido")
    public ResponseEntity<List<SidoGugunCodeDto>> sido() throws Exception {
        return new ResponseEntity<List<SidoGugunCodeDto>>(mapService.getSido(), HttpStatus.OK);
    }

    @GetMapping("/gugun")
    public ResponseEntity<List<SidoGugunCodeDto>> gugun(@RequestParam String sidoCode) throws Exception {
        return new ResponseEntity<List<SidoGugunCodeDto>>(mapService.getGugun(sidoCode), HttpStatus.OK);
    }

    @GetMapping("/dong")
    public ResponseEntity<List<SidoGugunCodeDto>> dong(@RequestParam String sidoCode,@RequestParam String gugunCode) throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("sidoCode",sidoCode);
        map.put("gugunCode",gugunCode);
        return new ResponseEntity<List<SidoGugunCodeDto>>(mapService.getDong(map), HttpStatus.OK);
    }
}
