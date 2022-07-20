package com.server.untils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUntil {
    private static String SECRET = "MUSICSERVER";

    private static int LAST_TIME = Calendar.HOUR_OF_DAY;

    /**
     * 返回token
     * @param username
     * @return
     */
    public static String generateToken(String username) {
        Date now = new Date(System.currentTimeMillis());
        Calendar endtime = Calendar.getInstance();
        endtime.setTime(now);
        endtime.add(LAST_TIME, 1);
        //计算过期时间
        Date end = endtime.getTime();
        Map<String, Object> claims = new HashMap();
        claims.put("username", username);
        JwtBuilder jwtBuilder = Jwts.builder()
                .setClaims(claims)
                //设置签名时间
                .setIssuedAt(new Date())
                //设置签名人
                .setAudience("admin")
                //设置过期时间
                .setExpiration(end)
                //签名
                .signWith(SignatureAlgorithm.HS256, SECRET);

        return jwtBuilder.compact();
    }

    /**
     *返回用户名
     * @param token
     * @return
     */
    public static String parseToken(String token){
        Claims claims;
        try{
            claims = Jwts.parser()
                    //根据密钥解析
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
           return null;
        }
        return (String) claims.get("username");
    }

    public static void main(String[] args) {
        String token = generateToken("21312sadsad");
        System.out.println(parseToken(token));
    }
}

