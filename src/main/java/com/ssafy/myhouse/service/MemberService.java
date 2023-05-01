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

    public boolean save(MemberJoinDto memberJoinDto) throws Exception {
        if (duplicatedMember(memberJoinDto)) {
            return false;
        }
        Member member = Member.builder()
                .userId(memberJoinDto.getUserId())
                .password(encoder.encode(memberJoinDto.getPassword()))
                .name(memberJoinDto.getName())
                .email(memberJoinDto.getEmail())
                .address(memberJoinDto.getAddress())
                .number(memberJoinDto.getNumber())
                .role(memberJoinDto.getRole())
                .build();

        memberMapper.join(member);
        return true;
    }

    private boolean duplicatedMember(MemberJoinDto memberJoinDto) {
        if (isExistsByUserId(memberJoinDto)) {
            return true;
        }
        if (isExistsByEmail(memberJoinDto)) {
            return true;
        }
        return false;
    }

    private boolean isExistsByUserId(MemberJoinDto memberJoinDto) {
        return memberMapper.existsByUserId(memberJoinDto.getUserId());
    }

    private boolean isExistsByEmail(MemberJoinDto memberJoinDto) {
        return memberMapper.existsByEmail(memberJoinDto.getEmail());
    }

    public int delete(String userId) throws Exception{
        return memberMapper.deleteByUserId(userId);
    }
}
