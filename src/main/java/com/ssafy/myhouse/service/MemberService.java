package com.ssafy.myhouse.service;

import com.ssafy.myhouse.mapper.MemberMapper;
import com.ssafy.myhouse.vo.Member;
import com.ssafy.myhouse.vo.JoinMemberDto;
import com.ssafy.myhouse.vo.ModifyMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;
    private final BCryptPasswordEncoder encoder;

    public boolean save(JoinMemberDto memberDto) throws Exception {
        if (duplicatedMember(memberDto)) {
            return false;
        }
        Member member = Member.builder()
                .userId(memberDto.getUserId())
                .password(encoder.encode(memberDto.getPassword()))
                .name(memberDto.getName())
                .email(memberDto.getEmail())
                .address(memberDto.getAddress())
                .number(memberDto.getNumber())
                .role(memberDto.getRole())
                .build();

        memberMapper.join(member);
        return true;
    }

    private boolean duplicatedMember(JoinMemberDto joinMemberDto) {
        if (isExistsByUserId(joinMemberDto)) {
            return true;
        }
        if (isExistsByEmail(joinMemberDto)) {
            return true;
        }
        return false;
    }

    private boolean isExistsByUserId(JoinMemberDto joinMemberDto) {
        return memberMapper.existsByUserId(joinMemberDto.getUserId());
    }

    private boolean isExistsByEmail(JoinMemberDto joinMemberDto) {
        return memberMapper.existsByEmail(joinMemberDto.getEmail());
    }

    public int delete(String userId) throws Exception{
        return memberMapper.deleteByUserId(userId);
    }

    public Member findOne(String userId) {
        return memberMapper.findByUserId(userId);
    }

    public int update(ModifyMemberDto memberDto) {

        Member member = Member.builder()
                .userId(memberDto.getUserId())
                .password(encoder.encode(memberDto.getPassword()))
                .name(memberDto.getName())
                .email(memberDto.getEmail())
                .address(memberDto.getAddress())
                .number(memberDto.getNumber())
                .build();
        return memberMapper.updateMember(member);
    }
}
