package com.ssafy.myhouse.service;

import com.ssafy.myhouse.mapper.MemberMapper;
import com.ssafy.myhouse.vo.LoginMemberDto;
import com.ssafy.myhouse.vo.Member;
import com.ssafy.myhouse.vo.JoinMemberDto;
import com.ssafy.myhouse.vo.ModifyMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    public boolean save(JoinMemberDto memberDto) throws Exception {
        if (duplicatedMember(memberDto)) {
            return false;
        }
        Member member = Member.builder()
                .userId(memberDto.getUserId())
                .password(memberDto.getPassword())
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
    public Member findById(int id) {
        return memberMapper.findById(id);
    }

    public int update(ModifyMemberDto memberDto) {

        Member member = Member.builder()
                .userId(memberDto.getUserId())
                .password(memberDto.getPassword())
                .name(memberDto.getName())
                .email(memberDto.getEmail())
                .address(memberDto.getAddress())
                .number(memberDto.getNumber())
                .build();
        return memberMapper.updateMember(member);
    }

    public void saveRefreshToken(String userid, String refreshToken) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userid", userid);
        map.put("token", refreshToken);
        memberMapper.saveRefreshToken(map);
    }

    public Object getRefreshToken(String userid) throws Exception {
        return memberMapper.getRefreshToken(userid);
    }

    public void deleRefreshToken(String userid) throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("userid", userid);
        map.put("token", null);
        memberMapper.deleteRefreshToken(map);
    }

    public Member login(LoginMemberDto memberDto) {
        if (memberDto.getUserid() == null || memberDto.getUserpwd() == null)
            return null;
        return memberMapper.login(memberDto);
    }
}
