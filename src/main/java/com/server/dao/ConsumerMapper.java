package com.server.dao;

import com.server.pojo.Consumer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConsumerMapper {

    //查询所有用户
    List<Consumer> getAllUser();
    //查询指定Id用户
    List<Consumer> getUserById(@Param("id") int id);
    //添加用户
    int addUser(Consumer consumer);
    //根据username查询数量
    int getCountByUserName(@Param("username") String username);
    //根据phooneNumber查询数量
    int getCountByPhoneNumber(@Param("phoneNum") String phoneNum);
    //根据id删除用户
    int deleteUserById(@Param("id")int id);
    //更新用户信息
    int updateUser(Consumer consumer);
}
