package com.zte.msg.pushcenter.pccore.utils;

import com.alibaba.fastjson.JSONObject;
import com.zte.msg.pushcenter.pccore.config.RequestHeaderContext;
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

    private static final String SIMPLE_TOKEN_SECRET = "ZTE96952f774ce244fcb42af56062e519b3lFOGZ3YaWuCZS";

    /**
     * 获得指定数目的UUID
     *
     * @param number int 需要获得的UUID数量
     * @return String[] UUID数组
     */
    public static String[] getUuId(int number) {
        if (number < 1) {
            return null;
        }
        String[] retArray = new String[number];
        for (int i = 0; i < number; i++) {
            retArray[i] = getUuId();
        }
        return retArray;
    }

    /**
     * 获得UUID
     * 32位
     *
     * @return String UUID
     */
    public static String getUuId() {
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", Constants.EMPTY);
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
        return (time + Constants.EMPTY);
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
            // 转换出每一个代码点
            int data = Integer.parseInt(hex[i], 16);
            // 追加成string
            string.append((char) data);
        }
        return string.toString();
    }

    /**
     * 生成项目密匙
     */
    private static SecretKey generalKey(String stringKey) {
        byte[] encodedKey = Base64.decodeBase64(stringKey);
        return Keys.hmacShaKeyFor(encodedKey);
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
        //默认token有效时间为2小时
        return createOpenApiToken(item, 60 * 60 * 2 * 1000);
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
        if (js == null || Constants.EMPTY.equals(js)) {
            return null;
        }
        JSONObject jsStr = JSONObject.parseObject(js);
        String token = jsStr.getString("token");
        String appKey = jsStr.getString("appKey");
        String appSecret = jsStr.getString("appSecret");
        return openApiParseToken(token, new OpenApiTokenInfo(null, null, appKey, appSecret, null));
    }

    /**
     * OpenApi
     * 验证令牌，成功则返还令牌所携带的信息
     */
    private static OpenApiTokenInfo openApiParseToken(String token, OpenApiTokenInfo info) throws JwtException {
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
     * @param authCode
     * @return
     */
    public static OpenApiTokenInfo getOpenApiTokenInfo(String authCode) {
        OpenApiTokenInfo openApiTokenInfo = null;
        try {
            openApiTokenInfo = parseOpenApiToken(AesUtils.decrypt(authCode));
        } catch (JwtException e) {
            e.printStackTrace();
        }
        // 401
        if (authCode == null || Constants.EMPTY.equals(authCode) || openApiTokenInfo == null) {
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
        if (js == null || Constants.EMPTY.equals(js)) {
            return null;
        }
        JSONObject jsStr = JSONObject.parseObject(js);
        String token = jsStr.getString(Constants.TOKEN_STRING);
        String appSecret = jsStr.getString(Constants.APP_SECRET_STRING);
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
            String role = secretService.selectAppRole((String) claims.get(Constants.APP_KEY_STRING));
            if (!role.equals((String) claims.get(Constants.ROLE_STRING))) {
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
        //默认token有效时间为2小时
        return createSimpleToken(item, 60 * 60 * 2 * 1000);
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
                .setId(String.valueOf(item.getId()))
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
    private static SimpleTokenInfo simpleParseToken(String token) throws JwtException {
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

    /**
     * Simple
     * 获取开放平台登录信息
     *
     * @return
     */
    public static SimpleTokenInfo getSimpleTokenInfo(String token) {
        SimpleTokenInfo simpleTokenInfo = null;
        try {
            simpleTokenInfo = simpleParseToken(token);
        } catch (JwtException e) {
            e.printStackTrace();
        }
        // 401
        if (token == null || Constants.EMPTY.equals(token) || simpleTokenInfo == null) {
            throw new CommonException(ErrorCode.AUTHORIZATION_CHECK_FAIL);
        }
        return simpleTokenInfo;
    }

    public static String getCurrentUserName() {
        return RequestHeaderContext.getInstance().getUser().getUserName();
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
        User user = new User();
        user.setId(1L);
        user.setUserName("frp");
        user.setUserRealName("冯锐鹏");
        System.out.println(createSimpleToken(user, 999999999));
    }


}