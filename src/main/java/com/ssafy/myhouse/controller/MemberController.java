package com.ssafy.myhouse.controller;

import com.ssafy.myhouse.service.MemberService;
import com.ssafy.myhouse.vo.MemberJoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //회원가입
    @PostMapping("/user")
    public ResponseEntity<?> join(@RequestBody MemberJoinDto memberJoinDto) throws Exception {

        boolean saveMember = memberService.save(memberJoinDto);
        if (!saveMember){
            return new ResponseEntity<>("중복된 아이디, 이메일",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("완료", HttpStatus.OK);
    }
    //로그인(security)
    // 로그아웃(security)

    // 수정

    // 삭제
    @DeleteMapping("/mypage")
    public void deleteMember(@AuthenticationPrincipal UserDetails member, HttpServletResponse response) throws Exception {
        memberService.delete(member.getUsername());
        response.sendRedirect("/logout");
    }
}
