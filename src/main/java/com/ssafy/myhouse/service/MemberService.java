package com.ssafy.myhouse.service;

import com.ssafy.myhouse.mapper.MemberMapper;
import com.ssafy.myhouse.vo.Member;
import com.ssafy.myhouse.vo.MemberJoinDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder encoder;
    public boolean save(MemberJoinDto memberJoinDto) throws Exception{
        if (duplicatedMember(memberJoinDto)) {
            return false;
        }
        Member member = Member.builder()
                .userId(memberJoinDto.getUserId())
                .password(encoder.encode(memberJoinDto.getPassword()))
                .name(memberJoinDto.getName())
                .email(memberJoinDto.getEmail())
                .role(memberJoinDto.getRole())
                .build();

        memberMapper.join(member);

        return true;
    }

    private boolean duplicatedMember(MemberJoinDto memberJoinDto) {
        return false;
    }
}
