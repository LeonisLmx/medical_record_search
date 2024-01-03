package com.medical.record.config;

import com.medical.record.model.UserRecord;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author urey.liu
 * @description
 * @date 2023/5/24 10:31 上午
 */
public class JwtTokenUtil {

    private static final String CLAIM_KEY_USERNAME="sub";
    private static final String CLAIM_KEY_CREATED="created";

    private static final String secret = "medical-record";

    private static final Long expiration = 60L;

    /**
     * 根据用户信息生成token
     */
    public static String generateToken(UserRecord userRecord){
        Map<String,Object> claims=new HashMap<>(4);
        claims.put(CLAIM_KEY_USERNAME,userRecord.getUserName());
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }

    /**
     * 根据荷载JWT生成token
     * @param claims
     * @return
     */
    public static String generateToken(Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    /**
     * 生成token失效时间
     * @return
     */
    private static Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration*1000);
    }

    /**
     * 从token中获取登录名
     * @param token
     * @return
     */
    public static String getUserNameByToken(String token){
        String userName;
        try{
            Claims claims= getClaimsFromToken(token);
            userName= claims.getSubject();
        }catch (Exception e){
            userName=null;
        }
        return userName;
    }

    /**
     * 从token中获取荷载
     * @param token
     * @return
     */
    private static Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims= Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            e.printStackTrace();
        }
        return claims;
    }

    /**
     * 验证token是否有效
     * @param token
     * @return
     */
    public static boolean validateToken(String token){
        return !isTokenExpired(token);
    }

    /**
     * 验证token是否失效
     * @param token
     * @return
     */
    private static boolean isTokenExpired(String token) {
        Date expireDate = getExpiredDateFromToken(token);
        return  expireDate.before(new Date());
    }

    /**
     * 从token中获取失效时间
     * @param token
     * @return
     */
    private static Date getExpiredDateFromToken(String token) {
        Claims claims= getClaimsFromToken(token);
        return claims == null?new Date(0):claims.getExpiration();
    }
}
