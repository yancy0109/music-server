package com.server.controller;


import com.server.common.ErrorMessage;
import com.server.common.SuccessMessage;
import com.server.pojo.Consumer;
import com.server.service.ConsumerService;
import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConsumerController {

    @Autowired
    ConsumerService consumerService;

    //获取所有用户信息
    @RequestMapping("/user")
    public Object getAllUser(){
        return new SuccessMessage<>(null, consumerService.getAllUser()).getMessage();
    }

    //返回指定ID的用户
    @RequestMapping("/user/detail")
    public Object getUserById(int id){
        return new SuccessMessage<>(null,consumerService.getUserById(id)).getMessage();
    }

    //添加用户 在client端 未测试
    @RequestMapping("/user/add")
    public Object addUser(Consumer consumer){
        boolean flag = consumerService.addUser(consumer);
        if (flag){
            return new SuccessMessage<Null>("添加成功").getMessage();
        }else {
            return new ErrorMessage("添加失败").getMessage();
        }
    }
    //更新用户信息 在client端 未测试
    @RequestMapping("/user/update")
    public Object updateUser(Consumer consumer){
        boolean flag = consumerService.updateUser(consumer);
        if (flag){
            return new SuccessMessage("更新成功").getMessage();
        }else {
            return new SuccessMessage("更新失败").getMessage();
        }
    }

    //删除用户信息
    @RequestMapping("/user/delete")
    public Object deleteById(@RequestParam("id") int id){
        boolean flag = consumerService.deleteUserById(id);
        if (flag){
            return new SuccessMessage("删除成功").getMessage();
        }else {
            return new ErrorMessage("删除失败").getMessage();
        }
    }
}