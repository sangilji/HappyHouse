package com.ssafy.myhouse.mapper;

import com.ssafy.myhouse.vo.LoginMemberDto;
import com.ssafy.myhouse.vo.Member;
import com.ssafy.myhouse.vo.ModifyMemberDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.Map;

@Mapper
public interface MemberMapper {

    int join(Member member) throws SQLException; // 회원가입

    Member findByUserId(String userId);

    boolean existsByUserId(String userId);

    boolean existsByEmail(String email);

    int deleteByUserId(String userId) throws SQLException;

    int updateMember(Member member);

    Member findById(int id);

    void saveRefreshToken(Map<String, String> map);

    Object getRefreshToken(String userid);

    void deleteRefreshToken(Map<String, String> map);

    Member login(LoginMemberDto memberDto);

    ModifyMemberDto findPassword(String userId);

    int newPassword(Member member);
}
