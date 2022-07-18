package com.server.controller;

import com.server.common.SuccessMessage;
import com.server.untils.JwtUntil;
import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class AdminController {

    // 判断是否登录成功
    @ResponseBody
    @RequestMapping(value = "/admin/login/status", method = RequestMethod.POST)
    public Object loginStatus(@RequestParam("name") String username,@RequestParam("password") String password, HttpSession session) {
        String token = JwtUntil.generateToken(username);
        HashMap map = new HashMap();
        map.put("token",token);
        return new SuccessMessage<Map>("登录成功",map).getMessage();
    }
}
