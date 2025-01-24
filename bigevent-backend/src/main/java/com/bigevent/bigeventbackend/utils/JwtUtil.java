package com.bigevent.bigeventbackend.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;
import java.util.Map;

public class JwtUtil {

    // 定义秘钥
    private static final String KEY = "itheima";

    /**
     * 生成 Token
     *
     * @param claims 要携带的自定义数据（以键值对形式）
     * @return 生成的 Token 字符串
     */
    public static String genToken(Map<String, Object> claims) {
        return JWT.create()
                .withClaim("claims", claims) // 自定义数据
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12)) // 12小时过期时间
                .sign(Algorithm.HMAC256(KEY)); // 签名算法和秘钥
    }

    /**
     * 解析 Token 并提取数据
     *
     * @param token 要解析的 Token
     * @return 包含解析后数据的 Map
     */
    public static Map<String, Object> parseToken(String token) {
        // 验证器
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(KEY)).build();
        // 验证并解析 Token
        DecodedJWT decodedJWT = verifier.verify(token);
        // 获取 "claims" 字段的值并返回
        return decodedJWT.getClaim("claims").asMap();
    }
}
