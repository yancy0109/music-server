package com.server.interceptor;

import com.server.common.ErrorMessage;
import com.server.untils.JwtUntil;
import com.server.untils.RedisUntil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 配置拦截器
 * 进行token校验
 * 拦截不含token的用户
 * 同时对含有token的用户的token进行刷新
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Resource(name = "redisUntil")
    RedisUntil redisUntil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //根据username
        String username = (String) request.getSession().getAttribute("username");
//        System.out.println(username);
        if (username == null){
//            System.out.println("未登录请求");
            PrintWriter writer = response.getWriter();
            writer.write(new ErrorMessage("no login").getMessage().toString());
            writer.flush();
            writer.close();
            return false;
        }
        //判断redis是否包含username
        String sessionId = (String) redisUntil.getObjecet(username);
        String token = (String) redisUntil.getObjecet(sessionId);
        //异地登录校验
        //username与sessionId绑定是在登录Cotroller进行
        if (!sessionId.equals(request.getSession().getId())){
//            System.out.println(username+"异地登录成功");
            PrintWriter writer = response.getWriter();
            writer.write(new ErrorMessage("this user was login in other place").getMessage().toString());
            writer.flush();
            writer.close();
            return false;
        }
        String tokenResult = JwtUntil.parseToken(token);
        //token过期校验
        if (tokenResult==null){
            PrintWriter writer = response.getWriter();
            writer.write(new ErrorMessage(username+"token out").getMessage().toString());
            writer.flush();
            writer.close();
            return false;
        }
//        System.out.println(username+"正常访问");
        String newToken = JwtUntil.generateToken(username);
        //刷新token时间
        redisUntil.setObject(sessionId,newToken);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
