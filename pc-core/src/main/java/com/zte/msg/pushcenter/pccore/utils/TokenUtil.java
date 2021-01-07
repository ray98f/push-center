package com.zte.msg.pushcenter.pccore.utils;

import com.alibaba.fastjson.JSONObject;
import com.zte.msg.pushcenter.pccore.dto.OpenApiTokenInfo;
import com.zte.msg.pushcenter.pccore.dto.SimpleTokenInfo;
import com.zte.msg.pushcenter.pccore.entity.User;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.enums.TokenStatus;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.service.SecretService;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.codec.binary.Base64;

import javax.annotation.Resource;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
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

    @Resource
    private static SecretService secretService;

    @Resource
    private static HttpServletRequest request;

    private static final String SIMPLE_TOKEN_SECRET = "ZTE96952f774ce244fcb42af56062e519b3lFOGZ3YaWuCZS";

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

    /**
     * OpenApi
     * 生成Token
     *
     * @param item OpenApiToken信息
     * @return String
     * @throws Exception Token校验失败
     */
    public static String createOpenApiToken(OpenApiTokenInfo item) throws Exception {
        return createOpenApiToken(item, 60 * 60 * 2 * 1000); //默认token有效时间为2小时
    }

    /**
     * OpenApi
     * 根据请求登录的信息生成令牌
     *
     * @param item      登录请求相关信息，同时也是令牌解密所需验证信息
     * @param ttlMillis 令牌有效时间
     * @return 返还生成的令牌
     */
    public static String createOpenApiToken(OpenApiTokenInfo item, long ttlMillis) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder()
                .setId(String.valueOf(item.getAppId()))
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
     * OpenApi
     * 将前端发送过来的请求所附带的信息转换为可识别形式,并发送给验证令牌
     *
     * @param js 前端发送请求时所附带的String格式的json文件
     */
    public static OpenApiTokenInfo parseOpenApiToken(String js) throws JwtException {
        if (js == null || js.equals("")) return null;
        JSONObject jsStr = JSONObject.parseObject(js);
        String token = jsStr.getString("token");
        String appKey = jsStr.getString("appKey");
        String appSecret = jsStr.getString("appSecret");
        return openApiParseToken_(token, new OpenApiTokenInfo(null, null, appKey, appSecret, null));
    }

    /**
     * OpenApi
     * 验证令牌，成功则返还令牌所携带的信息
     */
    private static OpenApiTokenInfo openApiParseToken_(String token, OpenApiTokenInfo info) throws JwtException {
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
        return new OpenApiTokenInfo(
                Integer.valueOf(res.getId()),
                res.getSubject(),
                (String) res.get("appKey"),
                (String) res.get("appSecret"),
                (String) res.get("role")
        );
    }

    /**
     * OpenApi
     * 获取开放平台登录信息
     *
     * @param auth_code
     * @return
     */
    public static OpenApiTokenInfo getOpenApiTokenInfo(String auth_code) {
        OpenApiTokenInfo openApiTokenInfo = null;
        try {
            openApiTokenInfo = parseOpenApiToken(AesUtils.decrypt(auth_code));
        } catch (JwtException e) {
            e.printStackTrace();
        }
        // 401
        if (auth_code == null || auth_code.equals("") || openApiTokenInfo == null) {
            throw new CommonException(ErrorCode.AUTHORIZATION_CHECK_FAIL);
        }
        return openApiTokenInfo;
    }

    /**
     * OpenApi
     * 校验token
     *
     * @param authorization
     * @return TokenStatus
     */
    public static TokenStatus verifyOpenApiToken(String authorization) {
        TokenStatus result;
        Claims claims;
        String js = AesUtils.decrypt(authorization);
        if (js == null || js.equals("")) return null;
        JSONObject jsStr = JSONObject.parseObject(js);
        String token = jsStr.getString("token");
        String appSecret = jsStr.getString("appSecret");
        try {
            claims = Jwts.parser()
                    .setSigningKey(appSecret)
                    .parseClaimsJws(token)
                    .getBody();
            final Date exp = claims.getExpiration();
            if (exp.before(new Date(System.currentTimeMillis()))) {
                result = TokenStatus.EXPIRED;
            } else {
                result = TokenStatus.VALID;
            }
            String role = secretService.selectAppRole((String) claims.get("appKey"));
            if (!role.equals((String) claims.get("role"))) {
                result = TokenStatus.INVALID;
            }
        } catch (Exception e) {
            result = TokenStatus.INVALID;
        }
        return result;
    }

    /**
     * Simple
     * 生成Token
     *
     * @param item OpenApiToken信息
     * @return String
     * @throws Exception Token校验失败
     */
    public static String createSimpleToken(User item) throws Exception {
        return createSimpleToken(item, 60 * 60 * 2 * 1000); //默认token有效时间为2小时
    }

    /**
     * Simple
     * 根据请求登录的信息生成令牌
     *
     * @param item      登录请求相关信息，同时也是令牌解密所需验证信息
     * @param ttlMillis 令牌有效时间
     * @return 返还生成的令牌
     */
    public static String createSimpleToken(User item, long ttlMillis) {
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        JwtBuilder builder = Jwts.builder()
                .setId(item.getUserName())
                .setSubject(item.getUserName())
                .claim("userRealName", item.getUserRealName())
                .setIssuedAt(now)
                .signWith(generalKey(SIMPLE_TOKEN_SECRET));
        if (ttlMillis >= 0) {
            long expMillis = nowMillis + ttlMillis;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }
        return builder.compact();
    }

    /**
     * Simple
     * 验证令牌，成功则返还令牌所携带的信息
     */
    private static SimpleTokenInfo simpleParseToken_(String token) throws JwtException {
        Jws<Claims> jws;
        try {
            jws = Jwts.parser()
                    .setSigningKey(generalKey(SIMPLE_TOKEN_SECRET))
                    .parseClaimsJws(token);
        } catch (JwtException ex) {
            System.err.println("Token parsing failed!");
            return null;
        }
        Claims res = jws.getBody();
        return new SimpleTokenInfo(
                res.getId(),
                res.getSubject(),
                (String) res.get("userRealName")
        );
    }

    public static String getUserName() {
        return getSimpleTokenInfo().getUserName();
    }

    /**
     * Simple
     * 获取开放平台登录信息
     *
     * @return
     */
    public static SimpleTokenInfo getSimpleTokenInfo() {
        String token = request.getHeader("Authorization");
        SimpleTokenInfo simpleTokenInfo = null;
        try {
            simpleTokenInfo = simpleParseToken_(token);
        } catch (JwtException e) {
            e.printStackTrace();
        }
        // 401
        if (token == null || token.equals("") || simpleTokenInfo == null) {
            throw new CommonException(ErrorCode.AUTHORIZATION_CHECK_FAIL);
        }
        return simpleTokenInfo;
    }

    /**
     * Simple
     * 校验token
     *
     * @param authorization
     * @return TokenStatus
     */
    public static TokenStatus verifySimpleToken(String authorization) {
        TokenStatus result;
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SIMPLE_TOKEN_SECRET)
                    .parseClaimsJws(authorization)
                    .getBody();
            final Date exp = claims.getExpiration();
            if (exp.before(new Date(System.currentTimeMillis()))) {
                result = TokenStatus.EXPIRED;
            } else {
                result = TokenStatus.VALID;
            }
        } catch (Exception e) {
            result = TokenStatus.INVALID;
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
//        String str = "{'appKey':'zte16087894621166JEad','appSecret':'eab79b7b882e434dbcaa48d2e35820a3OmZrHEaZegbtfdKa'," +
//                "'token':'eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJzZHNkc2RzIiwic3ViIjoiUmF5bWFuIiwiYXBwS2V5IjoienRlMTYwODc4OTQ2MjExNjZKRWFkIiwiYXBwU2VjcmV0IjoiZWFiNzliN2I4ODJlNDM0ZGJjYWE0OGQyZTM1ODIwYTNPbVpySEVhWmVnYnRmZEthIiwicm9sZSI6IkFBQTogdHJ1ZSwgQkJCOiBmYWxzZSIsImlhdCI6MTYwODg1ODM2MywiZXhwIjoxNjA4ODY1NTYzfQ.06NepZ0f5zUd0EpO6oWfL7a3ern4eW5DWZlf7xi1dPA'}";
//        String s = "25MQjSMQzL83qfVq8my3hSjDfaA+6p4CHm4FceEFzM9mGN2r4C8/RcNg0zdiLbfBFmhaUBsbmgCtJYPqtSfaEB+aDpmDtM/N3dRNT+i0fsZisU+rEb7PcMl/89YgsusMEGogJQjfwHHtjj3x/Ym9g1zXtlKt7jmg58akTNQyXF3IjuNDEs1TMjEKAsMY1RuFbggmljj90L73yXwZ1vT5ygTgUclCJoFy1bfpQ/V7WrtPXlo9AErIUsB4ic38MkAR3fpiIkCs4lHIVCFcIfnvGwM/XYW/H2frSUuCmhNuU4tAfUhE2VSEf9FMIglZyaw490HHr75JHaAtoMWRP3FciY8J9i2uFIT70SKAd6IMAekXWweZXPATtILHiHd+oBN9Neoy69wbOufkmDPcv3sSjeEb0kWBwuPRze8Zx0mfl3X/CYusCMofymx9dhbdxDsVBb9ypZnPEYE9QIIz+aPZdjneWohLLfAFaWLAFpigacIZXTUJtD6xQpzh8D0vAQ5a";
//        System.out.println(s);
//        TokenStatus tokenStatus = verifyOpenApiToken(s);
//        System.out.println(tokenStatus);
//        OpenApiTokenInfo tokenInfo = getOpenApiTokenInfo(s);
//        System.out.println(tokenInfo.getAppName());
//        String id = "sdsdsds", name = "Rayman", role = "AAA: true, BBB: false";
//        TokenInfo it = new TokenInfo(id, name, "zte16087894621166JEad","eab79b7b882e434dbcaa48d2e35820a3OmZrHEaZegbtfdKa", role);
//        String token = CreateOpenApiToken(it);
//        System.out.println("JsonWebToken = " + token);
//        System.out.println(TokenUtil.getUUID() + TokenUtil.getRandomString(16));
//        String id = "sdsdsds", name = "Rayman", role = "AAA: true, BBB: false";
//        SimpleTokenInfo sti = new SimpleTokenInfo(id, name, role);
//        String token = createSimpleToken(sti);
//        System.out.println(token);
//        String str = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiJzZHNkc2RzIiwic3ViIjoiUmF5bWFuIiwidXNlclJvbGUiOiJBQUE6IHRydWUsIEJCQjogZmFsc2UiLCJpYXQiOjE2MDg4ODE1NDcsImV4cCI6MTYwODg4ODc0N30.tkNMmCvx9Y4KH2nDnQQyh0hFwDPCf_mY9C3P-UZi9Bs";
//        TokenStatus status = verifySimpleToken(str);
//        System.out.println(status);
//        SimpleTokenInfo simpleTokenInfo = getSimpleTokenInfo(str);
//        System.out.println(simpleTokenInfo.getUserRole());
//        System.out.println("ZTE" + getUUID() + getRandomString(13));
        System.out.println(getTimestamp());
        System.out.println(Long.parseLong(getTimestamp()) + 60 * 5 * 1000);
        System.out.println(Long.valueOf(TokenUtil.getTimestamp()).compareTo(Long.parseLong(getTimestamp()) + 60 * 5) > 0);
        System.out.println(System.currentTimeMillis());
    }


}