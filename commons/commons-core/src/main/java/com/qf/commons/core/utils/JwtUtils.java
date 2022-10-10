package com.qf.commons.core.utils;

import io.jsonwebtoken.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * jwt 令牌生成/解析
 * author 14526
 * create_time 2022/10/10
 */
public class JwtUtils {
    //密钥由调用方来决定
    private static String secretKey = "af46e5cb-d518-48b8-b6af-33288957da56";
    //有效期也由调用方来决定
    private static long ttl = 7200000;

    /**
     * 生成jwt令牌的方法
     *
     * @return
     */
    public static String createJwtToken(String key, Object value) {
        //设置自定义的map集合，存放自定义的数据
        Map<String, Object> params = new HashMap<>();
        params.put(key, value);

        long now = System.currentTimeMillis();
        JwtBuilder jwtBuilder = Jwts.builder()
                //设置令牌的生成时间
                .setIssuedAt(new Date(now))
                //添加额外的数据
                .addClaims(params)
                //设置jwt生成的加密算法以及密钥
                .signWith(SignatureAlgorithm.HS256, secretKey);
        if (ttl > 0) {
            //设置令牌的过期时间
            jwtBuilder.setExpiration(new Date(now + ttl));
        }
        //生成令牌
        return jwtBuilder.compact();
    }

    /**
     * 解析令牌
     *
     * @param jwtToken
     * @return
     */
    public static <T> T parseJwtToken(String jwtToken, String key) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secretKey)
                    .parseClaimsJws(jwtToken).getBody();
            return (T) claims.get(key);
        } catch (Throwable e) {
            //若token过期或者篡改 返回空
            return null;
        }
    }

//    public static void main(String[] args) {
//        System.out.println(UUID.randomUUID().toString());
//
//        String token = createJwtToken("name", "helloword");
//        System.out.println(token);
//        String name = parseJwtToken("eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NjU0MTA2MjgsIm5hbWUiOiJoZWxsb3dvcmQiLCJleHAiOjE2NjU0MTc4Mjh9.WLMQhykVlEyiSysie_n3Tt-Jp0OQjrktPPLrHv3qNyY", "name");
//        System.out.println(name);
//    }
}
