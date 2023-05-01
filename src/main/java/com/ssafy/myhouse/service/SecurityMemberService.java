package com.ssafy.myhouse.service;

import com.ssafy.myhouse.vo.Member;
import com.ssafy.myhouse.vo.SecurityMember;
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

//    private final MemberRepository memberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("msg?");
        Member member = new Member();
        log.info("member = {}",member);
        if (member != null) {
            System.out.println(member.getUserId());
            return new SecurityMember(member);
        }
        return null;
    }
}
