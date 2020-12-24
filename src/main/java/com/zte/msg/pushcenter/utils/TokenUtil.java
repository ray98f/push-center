package com.zte.msg.pushcenter.utils;

import com.alibaba.fastjson.JSONObject;
import com.zte.msg.pushcenter.dto.DataResponse;
import com.zte.msg.pushcenter.entity.TokenInfo;
import com.zte.msg.pushcenter.enums.ErrorCode;
import com.zte.msg.pushcenter.exception.CommonException;
import com.zte.msg.pushcenter.service.TokenService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2020/12/23 15:42
 */
public class TokenUtil {
    /**
     * 获得指定数目的UUID
     *
     * @param number int 需要获得的UUID数量
     * @return String[] UUID数组
     */
    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] retArray = new String[number];
        for (int i = 0; i < number; i++) {
            retArray[i] = getUUID();
        }
        return retArray;
    }

    /**
     * 获得UUID
     * 32位
     *
     * @return String UUID
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }

    /**
     * 生成随机字符
     *
     * @param length 字符长度
     * @return String
     */
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 生成时间戳
     *
     * @return String
     */
    public static String getTimestamp() {
        Date date = new Date();
        long time = date.getTime();
        return (time + "");
    }

    /**
     * 完成Unicode到String格式转换
     *
     * @param unicode 待转换字符串
     */
    public static String unicodeToString(String unicode) {
        StringBuffer string = new StringBuffer();
        String[] hex = unicode.split("\\\\u");
        for (int i = 1; i < hex.length; i++) {
            int data = Integer.parseInt(hex[i], 16);// 转换出每一个代码点
            string.append((char) data);// 追加成string
        }
        return string.toString();
    }

    /**
     * 生成项目密匙
     */
    private static SecretKey generalKey(String stringKey) {
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        SecretKey key = Keys.hmacShaKeyFor(encodedKey);
        return key;
    }

    public static String CreateToken(TokenInfo item) throws Exception {
        return CreateToken(item, 60 * 60 * 2 * 1000); //默认token有效时间为2小时
    }

    /**
     * 根据请求登录的信息生成令牌
     *
     * @param item      登录请求相关信息，同时也是令牌解密所需验证信息
     * @param ttlMillis 令牌有效时间
     * @return 返还生成的令牌
     */
    public static String CreateToken(TokenInfo item, long ttlMillis) throws Exception {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder()
                .setId(item.getAppId())
                .setSubject(item.getAppName())
                .claim("appKey", item.getAppKey())
                .claim("appSecret", item.getAppSecret())
                .claim("role", item.getRole())
                .setIssuedAt(now)
                .signWith(generalKey(item.getAppSecret()));
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * 将前端发送过来的请求所附带的信息转换为可识别形式。并发送给验证令牌的函数
     *
     * @param js 前端发送请求时所附带的String格式的json文件
     */
    public static TokenInfo ParseToken(String js) throws JwtException {
        if (js == null || js.equals("")) return null;
        JSONObject jsStr = JSONObject.parseObject(js);
        String token = jsStr.getString("token");
        String appKey = jsStr.getString("appKey");
        String appSecret = jsStr.getString("appSecret");
        return ParseToken_(token, new TokenInfo(null, null, appKey, appSecret, null));
    }

    /**
     * 验证令牌，成功则返还令牌所携带的信息
     */
    private static TokenInfo ParseToken_(String token, TokenInfo info) throws JwtException {
        Jws<Claims> jws;
        try {
            jws = Jwts.parser()
                    .setSigningKey(generalKey(info.getAppSecret()))
                    .parseClaimsJws(token);
        } catch (JwtException ex) {
            System.err.println("Token parsing failed!");
            return null;
        }
        Claims res = jws.getBody();
        TokenInfo userinfo = new TokenInfo(
                res.getId(),
                res.getSubject(),
                (String) res.get("appKey"),
                (String) res.get("appSecret"),
                (String) res.get("role")
        );
        return userinfo;
    }

    public static TokenInfo VerifyCode(String auth_code) {
        TokenInfo tokenInfo = null;
        try {
            tokenInfo = ParseToken(auth_code);
        } catch (JwtException e) {
            e.printStackTrace();
        }

        if (auth_code == null || auth_code == "" || tokenInfo == null) {
            throw new CommonException(ErrorCode.AUTHORIZATION_CHECK_FAIL);
        }
        return tokenInfo;
    }

    public static void main(String[] args) throws Exception {
        String s = AesUtils.encrypt("{'appKey':'zte16087894621166JEad','appSecret':'eab79b7b882e434dbcaa48d2e35820a3OmZrHEaZegbtfdKa'," +
                "'token':'eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJzZHNkc2RzIiwic3ViIjoiUmF5bWFuIiwicm9sZSI6IkFBQTogdHJ1ZSwgQkJCOiBmYWxzZSIsImFwcEtleSI6Inp0ZTE2MDg3ODk0NjIxMTY2SkVhZCIsImFwcFNlY3JldCI6ImVhYjc5YjdiODgyZTQzNGRiY2FhNDhkMmUzNTgyMGEzT21ackhFYVplZ2J0ZmRLYSIsImlhdCI6MTYwODc5MTU0MSwiZXhwIjoxNjA4Nzk4NzQxfQ.6OKlIJaslcbQNCaaxPHlINNUolLWWpJG6LOxQ_NE-FU'}");
        System.out.println(s);
        TokenInfo tokenInfo = VerifyCode(AesUtils.decrypt(s));
        System.out.println(tokenInfo.getRole());
//        String id = "sdsdsds", name = "Rayman", role = "AAA: true, BBB: false";
//        TokenInfo it = new TokenInfo(id, name, "zte16087894621166JEad","eab79b7b882e434dbcaa48d2e35820a3OmZrHEaZegbtfdKa", role);
//        String token = CreateToken(it);
//        System.out.println("JsonWebToken = " + token);
//        System.out.println(TokenUtil.getUUID() + TokenUtil.getRandomString(16));
    }
}