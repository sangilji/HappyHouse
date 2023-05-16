package com.ssafy.myhouse.security;

import com.ssafy.myhouse.mapper.MemberMapper;
import com.ssafy.myhouse.vo.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecurityMemberService implements UserDetailsService {

    private final MemberMapper memberMapper;
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Member member = memberMapper.findByUserId(userId);
        if (member != null) {
            System.out.println(member.getUserId());
            return new SecurityMember(member);
        }
        return null;
    }
}
