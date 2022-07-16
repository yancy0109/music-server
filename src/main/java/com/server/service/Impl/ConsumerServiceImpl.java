package com.server.service.Impl;

import com.server.dao.ConsumerMapper;
import com.server.pojo.Consumer;
import com.server.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerMapper consumerMapper;

    @Override
    public List<Consumer> getAllUser() {
        return consumerMapper.getAllUser();
    }

    @Override
    public List<Consumer> getUserById(int id) {
        return consumerMapper.getUserById(id);
    }

    @Override
    public boolean addUser(Consumer consumer) {
        //查询UserName唯一性
        int countByUserName = consumerMapper.getCountByUserName(consumer.getUsername());
        if (countByUserName>0) return false;
        //查询PhoneNum唯一性
        int countByPhoneNumber = consumerMapper.getCountByPhoneNumber(consumer.getPhoneNum());
        if (countByPhoneNumber>0) return false;
        //查询唯一后进行插入
        return consumerMapper.addUser(consumer) > 0;
    }

    @Override
    public boolean deleteUserById(int id) {
        return consumerMapper.deleteUserById(id)>0;
    }

    @Override
    public boolean updateUser(Consumer consumer) {
        return consumerMapper.updateUser(consumer)>0;
    }
}
