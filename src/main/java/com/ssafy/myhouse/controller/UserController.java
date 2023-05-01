package com.ssafy.myhouse.controller;

import com.ssafy.myhouse.service.MemberService;
import com.ssafy.myhouse.vo.MemberJoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final MemberService memberService;

    //회원가입
    @PostMapping("")
    public ResponseEntity<?> join(@Validated @RequestBody MemberJoinDto memberJoinDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(bindingResult, HttpStatus.BAD_REQUEST);
        }
        memberService.save(memberJoinDto);

        return new ResponseEntity<>("완료", HttpStatus.OK);
    }

    //로그인

    // 로그아웃

    // 수정

    // 삭제

}
