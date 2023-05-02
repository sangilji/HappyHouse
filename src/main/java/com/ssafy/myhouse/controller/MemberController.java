package com.ssafy.myhouse.controller;

import com.ssafy.myhouse.security.SecurityMember;
import com.ssafy.myhouse.service.MemberService;
import com.ssafy.myhouse.vo.Member;
import com.ssafy.myhouse.vo.JoinMemberDto;
import com.ssafy.myhouse.vo.ModifyMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;

    //회원가입 완료
    @PostMapping("/user")
    public ResponseEntity<?> join(@RequestBody JoinMemberDto joinMemberDto) throws Exception {

        boolean saveMember = memberService.save(joinMemberDto);
        if (!saveMember){
            return new ResponseEntity<>("중복된 아이디, 이메일",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("완료", HttpStatus.OK);
    }
    //로그인(security) 완료
    // 로그아웃(security) 완료

    @GetMapping("/user/mypage")
    public ResponseEntity<?> modifyMember(@AuthenticationPrincipal SecurityMember memberDetails) throws Exception{
        Member member = memberService.findOne(memberDetails.getUsername());
        String role = member.getRole();
        System.out.println("role = " + role);
        return new ResponseEntity<>(memberDetails.getPassword(), HttpStatus.OK);
    }

    // 수정
    @PutMapping("/user/mypage")
    public ResponseEntity<?> updateMember(@RequestBody ModifyMemberDto memberDto) throws Exception{
        int updateComplete = memberService.update(memberDto);
        if (updateComplete == 1) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            memberDto.getUserId(), memberDto.getPassword()));
            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);
            return new ResponseEntity<>("성공", HttpStatus.OK);
        }
        return new ResponseEntity<>("실패", HttpStatus.BAD_REQUEST);
    }

    // 삭제 완료
    @DeleteMapping("/user/mypage")
    public void deleteMember(@AuthenticationPrincipal SecurityMember member, HttpServletResponse response) throws Exception {
        memberService.delete(member.getUsername());

        response.sendRedirect("/logout");
    }
}
