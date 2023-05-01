package com.ssafy.myhouse.mapper;

import com.ssafy.myhouse.vo.Member;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;

@Mapper
public interface MemberMapper {

    int join(Member member) throws SQLException; // 회원가입

    Member findByUserId(String userId);
}
