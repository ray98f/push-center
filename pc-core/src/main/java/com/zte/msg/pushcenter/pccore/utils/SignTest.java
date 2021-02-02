package com.zte.msg.pushcenter.pccore.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zte.msg.pushcenter.pccore.dto.req.AppMessageReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.MailMessageReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.SmsMessageReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.WeChatMessageReqDTO;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author 40302
 */
public class SignTest {

    private static void sms() throws IOException {
        SmsMessageReqDTO smsMessageReqDTO = new SmsMessageReqDTO();
        smsMessageReqDTO.setMessageId(UUID.randomUUID().toString());
        Long appId = 3L;
        smsMessageReqDTO.setAppId(appId);
        smsMessageReqDTO.setIsCallBack(false);
        smsMessageReqDTO.setCallBackUrl(null);
        Long requestTime = System.currentTimeMillis();
        smsMessageReqDTO.setRequestTime(requestTime);
        smsMessageReqDTO.setPhoneNum(new String[]{"13588xxxx37", "13958xxxx87"});
        smsMessageReqDTO.setTemplateId(4L);
        Map<String, String> vars = new HashMap<>(16);
        vars.put("a", "123456");
        smsMessageReqDTO.setVars(vars);
        // 获取应用密钥值
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://wshmang.f3322.net:40323/api/v1/open/app/secret?appId=" + appId);
        httpGet.addHeader("Content-Type", "application/json;charset=utf8");
        HttpResponse response = httpClient.execute(httpGet);
        JSONObject jsonObject = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
        String secret = JSONObject.parseObject(jsonObject.getString("data")).getString("secret");
        String str = secret + JSON.toJSONString(smsMessageReqDTO) + appId + requestTime + secret;
        String sign = DigestUtils.md5DigestAsHex(str.getBytes(StandardCharsets.UTF_8));
        smsMessageReqDTO.setSign(sign);
        // 发送邮件通知
        HttpPost httpPost = new HttpPost("http://wshmang.f3322.net:40323/api/v1/open/push/sms");
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        String jsonString = JSON.toJSONString(smsMessageReqDTO);
        StringEntity entity = new StringEntity(jsonString, "UTF-8");
        httpPost.setEntity(entity);
        response = httpClient.execute(httpPost);
        System.out.println(response.getStatusLine().getStatusCode());
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    private static void mail() throws IOException {
        MailMessageReqDTO mailMessageReqDTO = new MailMessageReqDTO();
        mailMessageReqDTO.setMessageId(UUID.randomUUID().toString());
        Long appId = 3L;
        mailMessageReqDTO.setAppId(appId);
        mailMessageReqDTO.setIsCallBack(false);
        mailMessageReqDTO.setCallBackUrl(null);
        Long requestTime = System.currentTimeMillis();
        mailMessageReqDTO.setRequestTime(requestTime);
        mailMessageReqDTO.setTo(new String[]{"403027278@qq.com"});
        mailMessageReqDTO.setCc(new String[]{"cccccc@qq.com", "dddddd@163.com"});
        mailMessageReqDTO.setProviderId(4L);
        mailMessageReqDTO.setSubject("这是一封邮件");
        mailMessageReqDTO.setContent("<h1 id=\\\"q3kn4\\\">邮件</h1><p><i>邮件</i><br></p><p>下划线<i><br></i></p>");
        // 获取应用密钥值
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://wshmang.f3322.net:40323/api/v1/open/app/secret?appId=" + appId);
        httpGet.addHeader("Content-Type", "application/json;charset=utf8");
        HttpResponse response = httpClient.execute(httpGet);
        JSONObject jsonObject = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
        String secret = JSONObject.parseObject(jsonObject.getString("data")).getString("secret");
        String str = secret + JSON.toJSONString(mailMessageReqDTO) + appId + requestTime + secret;
        String sign = DigestUtils.md5DigestAsHex(str.getBytes(StandardCharsets.UTF_8));
        mailMessageReqDTO.setSign(sign);
        // 发送邮件通知
        HttpPost httpPost = new HttpPost("http://wshmang.f3322.net:40323/api/v1/open/push/mail");
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        String jsonString = JSON.toJSONString(mailMessageReqDTO);
        StringEntity entity = new StringEntity(jsonString, "UTF-8");
        httpPost.setEntity(entity);
        response = httpClient.execute(httpPost);
        System.out.println(response.getStatusLine().getStatusCode());
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    private static void wechat() throws IOException {
        WeChatMessageReqDTO weChatMessageReqDTO = new WeChatMessageReqDTO();
        weChatMessageReqDTO.setMessageId(UUID.randomUUID().toString());
        Long appId = 3L;
        weChatMessageReqDTO.setAppId(appId);
        weChatMessageReqDTO.setIsCallBack(false);
        weChatMessageReqDTO.setCallBackUrl(null);
        Long requestTime = System.currentTimeMillis();
        weChatMessageReqDTO.setRequestTime(requestTime);
        weChatMessageReqDTO.setOpenId("ozZ6k5mWals6PZy8VCYlrstPP2Ck");
        weChatMessageReqDTO.setProviderId(5L);
        weChatMessageReqDTO.setTemplateId(1L);
        weChatMessageReqDTO.setData("{\"first\": {\"value\": \"测试\"},\"keyword1\": {\"value\": \"预警\"},\"keyword2\": {…}}");
        weChatMessageReqDTO.setUrl("www.baidu.com");
        weChatMessageReqDTO.setAppletData("{…}");
        // 获取应用密钥值
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://wshmang.f3322.net:40323/api/v1/open/app/secret?appId=" + appId);
        httpGet.addHeader("Content-Type", "application/json;charset=utf8");
        HttpResponse response = httpClient.execute(httpGet);
        JSONObject jsonObject = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
        String secret = JSONObject.parseObject(jsonObject.getString("data")).getString("secret");
        String str = secret + JSON.toJSONString(weChatMessageReqDTO) + appId + requestTime + secret;
        String sign = DigestUtils.md5DigestAsHex(str.getBytes(StandardCharsets.UTF_8));
        weChatMessageReqDTO.setSign(sign);
        // 发送微信公众号通知
        HttpPost httpPost = new HttpPost("http://wshmang.f3322.net:40323/api/v1/open/push/wechat");
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        String jsonString = JSON.toJSONString(weChatMessageReqDTO);
        StringEntity entity = new StringEntity(jsonString, "UTF-8");
        httpPost.setEntity(entity);
        response = httpClient.execute(httpPost);
        System.out.println(response.getStatusLine().getStatusCode());
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    private static void app() throws IOException {
        AppMessageReqDTO appMessageReqDTO = new AppMessageReqDTO();
        appMessageReqDTO.setMessageId(UUID.randomUUID().toString());
        Long appId = 3L;
        appMessageReqDTO.setAppId(appId);
        appMessageReqDTO.setIsCallBack(false);
        appMessageReqDTO.setCallBackUrl(null);
        Long requestTime = System.currentTimeMillis();
        appMessageReqDTO.setRequestTime(requestTime);
        appMessageReqDTO.setProviderId(14L);
        appMessageReqDTO.setTargetPlatform(3);
        appMessageReqDTO.setRegistrationId(new String[]{"1104a897920a8f31c7d"});
        appMessageReqDTO.setMessageType(2);
        appMessageReqDTO.setTitle("这是一个App推送");
        appMessageReqDTO.setContent("<h1 id=\\\"phakb\\\">推送</h1><p style=\\\"padding-left:2em;\\\"><b>test</b></p>");
        // 获取应用密钥值
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://wshmang.f3322.net:40323/api/v1/open/app/secret?appId=" + appId);
        httpGet.addHeader("Content-Type", "application/json;charset=utf8");
        HttpResponse response = httpClient.execute(httpGet);
        JSONObject jsonObject = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
        String secret = JSONObject.parseObject(jsonObject.getString("data")).getString("secret");
        String str = secret + JSON.toJSONString(appMessageReqDTO) + appId + requestTime + secret;
        String sign = DigestUtils.md5DigestAsHex(str.getBytes(StandardCharsets.UTF_8));
        appMessageReqDTO.setSign(sign);
        // 发送微信公众号通知
        HttpPost httpPost = new HttpPost("http://wshmang.f3322.net:40323/api/v1/open/push/app");
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        String jsonString = JSON.toJSONString(appMessageReqDTO);
        StringEntity entity = new StringEntity(jsonString, "UTF-8");
        httpPost.setEntity(entity);
        response = httpClient.execute(httpPost);
        System.out.println(response.getStatusLine().getStatusCode());
        System.out.println(EntityUtils.toString(response.getEntity()));
    }

    public static void main(String[] args) throws IOException {
        sms();
        mail();
        wechat();
        app();
    }
}
