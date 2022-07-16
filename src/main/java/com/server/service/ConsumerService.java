package com.server.service;

import com.server.pojo.Consumer;

import java.util.List;

public interface ConsumerService {

    //查询所有用户
    List<Consumer> getAllUser();

    //返回指定ID的用户
    List<Consumer> getUserById(int id);

    //添加用户
    boolean addUser(Consumer consumer);

    //根据用户id删除
    boolean deleteUserById(int id);

    //更新用户信息
    boolean updateUser(Consumer consumer);
}
