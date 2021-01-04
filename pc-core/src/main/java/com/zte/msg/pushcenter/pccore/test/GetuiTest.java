package com.zte.msg.pushcenter.pccore.test;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/8 10:57
 */
public class GetuiTest {

    public void push() throws IOException {
        //短信群推url
        String url = "https://openapi-smsp.getui.com/v1/sps/push_sms_list";
        URL urlObj = new URL(url);
        URLConnection con = urlObj.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) con;

        //http头部
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");

        String appId = "";
        JSONObject requestDataObject = new JSONObject();
        requestDataObject.put("appId", appId);
        requestDataObject.put("authToken", "9e46cf0fac0bd94b01c6396db8700f6b5c988cd4e51eaff94f1cefec50ad17c9");
        requestDataObject.put("smsTemplateId", "000001");
        Map<String, String> param = new HashMap<String, String>();
        param.put("name", "***");
        param.put("code", "***");
        requestDataObject.put("smsParam", param);
        List<String> phoneNum = new ArrayList<String>();
        for (int i = 0; i < 1; i++) {
            phoneNum.add("9a54ab64562e64bd76b5d50d4e6487ac");
            phoneNum.add("b6fda656f377a5b8ebdb7db70aa29870");
        }
        requestDataObject.put("recNum", phoneNum);

        //建立连接，将数据写入内存
        OutputStreamWriter out = new
                OutputStreamWriter(httpURLConnection.getOutputStream());
        out.write(requestDataObject.toString());
        out.flush();
        out.close();
        BufferedReader in = null;
        String result = "";

        //将数据发送给服务端，并获取返回结果
        in = new BufferedReader(new
                InputStreamReader(httpURLConnection.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            result += line;
        }
        System.out.println(result);
    }

    public void authSign() throws IOException {
        //鉴权url
        String url = "https://openapi-smsp.getui.com/v1/sps/auth_sign";
        URL urlObj = new URL(url);
        URLConnection con = urlObj.openConnection();
        HttpURLConnection httpURLConnection = (HttpURLConnection) con;

        //http头部
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        long timestamp = System.currentTimeMillis();

        //将个推短信服务提供的app对应的appkey和masterSecret，可自行替换
        String appkey = "";
        String masterSecret = "";
        String appId = "";

        //sha256加密，使用org.apache.commons包中自带的加密方法，需将加密后数据一起上传
        String sign = DigestUtils.sha256Hex(String.format("%s%d%s", appkey, timestamp, masterSecret));
        JSONObject requestDataObject = new JSONObject();
        requestDataObject.put("sign", sign);
        requestDataObject.put("timestamp", timestamp);
        requestDataObject.put("appId", appId);

        //建立连接，将数据写入内存
        OutputStreamWriter out = new
                OutputStreamWriter(httpURLConnection.getOutputStream());
        out.write(requestDataObject.toString());
        out.flush();
        out.close();

        BufferedReader in = null;
        String result = "";

        //将数据发送给服务端，并获取返回结果
        in = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
        String line;
        while ((line = in.readLine()) != null) {
            result += line;
        }
        System.out.println(result);
    }

}
