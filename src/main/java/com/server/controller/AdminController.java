package com.server.controller;

import com.server.common.ErrorMessage;
import com.server.common.SuccessMessage;
import com.server.service.AdminService;
import com.server.untils.JwtUntil;
import com.server.untils.RedisUntil;
import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;


@RestController
public class AdminController {

    /**
     * 未启用redis请注释
     */
//    @Resource(name = "redisUntil")
//    RedisUntil redisUntil;

    @Autowired
    AdminService adminService;

    // 判断是否登录成功
    @ResponseBody
    @RequestMapping(value = "/admin/login/status", method = RequestMethod.POST)
    public Object loginStatus(@RequestParam("name") String username,@RequestParam("password") String password, HttpSession session) {
        /**
         * 这段是Redis单点登录的逻辑
         */
        boolean flag = adminService.checkAdmin(username, password);
        if (!flag){
            return new ErrorMessage("密码错误").getMessage();
        }
//        String token = JwtUntil.generateToken(username);
//        session.setAttribute("username",username);
//
//        redisUntil.removeUser(username);
//        redisUntil.addUser(username,session.getId(),token);
        System.out.println(username+"登录成功");
        return new SuccessMessage("登录成功").getMessage();
    }
}
