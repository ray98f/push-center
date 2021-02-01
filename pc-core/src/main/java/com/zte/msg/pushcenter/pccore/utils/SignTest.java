package com.zte.msg.pushcenter.pccore.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.zte.msg.pushcenter.pccore.dto.req.SmsMessageReqDTO;
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
    public static void main(String[] args) throws IOException {
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
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://localhost:40323/api/v1/app/open/secret?appId=" + appId);
        httpGet.addHeader("Content-Type", "application/json;charset=utf8");
        HttpResponse response = httpClient.execute(httpGet);
        JSONObject jsonObject = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));
        String secret = JSONObject.parseObject(jsonObject.getString("data")).getString("secret");
        String str = secret + JSON.toJSONString(smsMessageReqDTO) + appId + requestTime + secret;
        System.out.println(str);
        String sign = DigestUtils.md5DigestAsHex(str.getBytes(StandardCharsets.UTF_8));
        smsMessageReqDTO.setSign(sign);
        System.out.println(sign);
        HttpPost httpPost = new HttpPost("http://localhost:40323/api/open/v1/push/sms");
        httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        String jsonString = JSON.toJSONString(smsMessageReqDTO);
        StringEntity entity = new StringEntity(jsonString, "UTF-8");
        httpPost.setEntity(entity);
        response = httpClient.execute(httpPost);
        System.out.println(EntityUtils.toString(response.getEntity()));
    }
}
