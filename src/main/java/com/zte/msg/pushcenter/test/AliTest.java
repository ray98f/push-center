package com.zte.msg.pushcenter.test;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/7 13:29
 */
public class AliTest {

    public static void main(String[] args) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou",
                "LTAI4GKrt4Xy3aqL18dEc6LF", "2Gj393XLc2N4sQBan8vHzUMmiF9gdK");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", "18349342711");
        request.putQueryParameter("SignName", "SignName");
        request.putQueryParameter("TemplateCode", "TemplateCode");
        request.putQueryParameter("TemplateParam", "TemplateParam");
        request.putQueryParameter("SmsUpExtendCode", "SmsUpExtendCode");
        request.putQueryParameter("OutId", "OutId");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
