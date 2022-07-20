package com.server.service.Impl;

import com.server.dao.AdminMapper;
import com.server.pojo.Admin;
import com.server.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public boolean checkAdmin(String username,String password) {
        Admin adminTemp = adminMapper.selectAdmin(username);
        if (adminTemp == null){
            return false;
        }
        if (adminTemp.getPassword().equals(password)){
            return true;
        }
        return false;
    }
}
