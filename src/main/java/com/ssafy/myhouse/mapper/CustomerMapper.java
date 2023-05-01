package com.ssafy.myhouse.mapper;


import com.springbootmvccustomer.vo.Customer;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface CustomerMapper {

    List<Customer> selectAll() throws SQLException; // 모든 글 목록

    Customer selectOne(int num) throws SQLException; // 해당 번호의 글 한 개

    int insert(Customer customer) throws SQLException; // 새 글 등록

    int delete(int num) throws SQLException; // 해당 번호의 글 삭제

    int update(Customer b)throws SQLException;

    List<Customer> findByAddress(String address) throws SQLException;
}
