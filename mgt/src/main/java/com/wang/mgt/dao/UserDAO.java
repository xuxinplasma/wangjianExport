package com.wang.mgt.dao;


import com.wang.mgt.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDAO {


    User findByUsername(@Param("username") String username);
}