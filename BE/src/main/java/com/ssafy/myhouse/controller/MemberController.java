package com.ssafy.myhouse.controller;

import com.ssafy.myhouse.service.JwtService;
import com.ssafy.myhouse.service.MemberService;
import com.ssafy.myhouse.vo.LoginMemberDto;
import com.ssafy.myhouse.vo.Member;
import com.ssafy.myhouse.vo.JoinMemberDto;
import com.ssafy.myhouse.vo.ModifyMemberDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor

@RequestMapping("/user")
public class MemberController {
    public static final Logger logger = LoggerFactory.getLogger(MemberController.class);
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    private final MemberService memberService;
    private final JwtService jwtService;


    //회원가입 완료
    @PostMapping
    public ResponseEntity<?> join(@RequestBody JoinMemberDto joinMemberDto) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        boolean saveMember = memberService.save(joinMemberDto);
        if (!saveMember) {
            resultMap.put("message", "중복된 아이디 또는 이메일입니다.");
            return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
        }
        resultMap.put("message", SUCCESS);
        resultMap.put("data", joinMemberDto);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    //로그인(security) 완료
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody LoginMemberDto memberDto) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = null;
        try {
            Member loginUser = memberService.login(memberDto);
            if (loginUser != null) {
                String accessToken = jwtService.createAccessToken("userid", loginUser.getUserId());// key, data
                String refreshToken = jwtService.createRefreshToken("userid", loginUser.getUserId());// key, data
                memberService.saveRefreshToken(memberDto.getUserid(), refreshToken);
                logger.debug("로그인 accessToken 정보 : {}", accessToken);
                logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
                resultMap.put("access-token", accessToken);
                resultMap.put("refresh-token", refreshToken);
                resultMap.put("message", SUCCESS);
                status = HttpStatus.ACCEPTED;
            } else {
                resultMap.put("message", FAIL);
                status = HttpStatus.ACCEPTED;
            }
        } catch (Exception e) {
            logger.error("로그인 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }

    // 로그아웃
    @GetMapping("/logout/{userid}")
    public ResponseEntity<?> removeToken(@PathVariable("userid") String userid) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        try {
            memberService.deleRefreshToken(userid);
            resultMap.put("message", SUCCESS);
            status = HttpStatus.ACCEPTED;
        } catch (Exception e) {
            logger.error("로그아웃 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);

    }

    @GetMapping("/mypage/{userid}")
    public ResponseEntity<?> getMember(@PathVariable("userid") String userid, HttpServletRequest request) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        if (jwtService.checkToken(request.getHeader("access-token"))) {
            logger.info("사용 가능한 토큰!!!");
            try {
//				로그인 사용자 정보.
                Member member = memberService.findOne(userid);
                System.out.println(member.getRole());
                resultMap.put("userInfo", member);
                resultMap.put("message", SUCCESS);
                status = HttpStatus.ACCEPTED;
            } catch (Exception e) {
                logger.error("정보조회 실패 : {}", e);
                resultMap.put("message", e.getMessage());
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
        } else {
            logger.error("사용 불가능 토큰!!!");
            resultMap.put("message", FAIL);
            status = HttpStatus.UNAUTHORIZED;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    // 수정
    @PutMapping("/mypage")
    public ResponseEntity<?> updateMember(@RequestBody ModifyMemberDto memberDto, HttpServletRequest request) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        if (jwtService.checkToken(request.getHeader("access-token"))) {
            int updateComplete = memberService.update(memberDto);
            if (updateComplete == 1) {
                resultMap.put("message", SUCCESS);
                resultMap.put("data", memberDto);
                return new ResponseEntity<>(resultMap, HttpStatus.OK);
            }
        } else {
            logger.error("사용 불가능 토큰!!!");
            resultMap.put("message", FAIL);
            HttpStatus status = HttpStatus.UNAUTHORIZED;

            return new ResponseEntity<>(resultMap, status);
        }

        resultMap.put("message", FAIL);
        return new ResponseEntity<>(resultMap, HttpStatus.BAD_REQUEST);
    }

    // 삭제 완료
    @DeleteMapping("mypage/{userid}")
    public ResponseEntity<?> deleteMember(@PathVariable String userid, HttpServletRequest request) throws Exception {
        Map<String, Object> resultMap = new HashMap<>();
        if (jwtService.checkToken(request.getHeader("access-token"))) {
            memberService.delete(userid);
            resultMap.put("message", SUCCESS);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } else {
            logger.error("사용 불가능 토큰!!!");
            resultMap.put("message", FAIL);
            HttpStatus status = HttpStatus.UNAUTHORIZED;

            return new ResponseEntity<>(resultMap, status);
        }


    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody Member member, HttpServletRequest request)
            throws Exception {
        logger.info("memberDto : {}", member);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        String token = request.getHeader("refresh-token");
        logger.info("token : {}, memberDto : {}", token, member);
        if (!jwtService.checkToken(token)) {
            if (token.equals(memberService.getRefreshToken(member.getUserId()))) {
                String accessToken = jwtService.createAccessToken("userid", member.getUserId());
                logger.info("token : {}", accessToken);
                logger.info("정상적으로 액세스토큰 재발급!!!");
                resultMap.put("access-token", accessToken);
                resultMap.put("message", SUCCESS);
                status = HttpStatus.ACCEPTED;
            }
            else {
                logger.info("리프레쉬토큰도 사용불!!!!!!!");
                status = HttpStatus.UNAUTHORIZED;
            }
        }
        return new ResponseEntity<>(resultMap, status);
    }

    @PostMapping("/findPassword")
    public ResponseEntity<?> findPassword(@RequestBody Member member, HttpServletRequest request) {
        String findPasswordMember = memberService.findPassword(member);
        logger.info("controller result  " + member);
        if (findPasswordMember.equals("success")) {
            return new ResponseEntity<>("success", HttpStatus.OK);
        } else if (findPasswordMember.equals("notFound")) {
            return new ResponseEntity<>("fail", HttpStatus.NOT_FOUND);
        } else {

            return new ResponseEntity<>("serverError", HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }
}
