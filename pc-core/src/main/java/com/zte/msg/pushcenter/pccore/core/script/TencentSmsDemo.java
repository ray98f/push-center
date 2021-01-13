package com.zte.msg.pushcenter.pccore.core.script;

import com.alibaba.fastjson.JSONObject;
import com.zte.msg.pushcenter.pcscript.ParamConstants;
import com.zte.msg.pushcenter.pcscript.PcScript;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/22 10:36
 */
public class TencentSmsDemo implements PcScript {

    private final static Charset UTF8 = StandardCharsets.UTF_8;

    private final static String CT_JSON = "application/json";

    public static byte[] hmac256(byte[] key, String msg) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, mac.getAlgorithm());
        mac.init(secretKeySpec);
        return mac.doFinal(msg.getBytes(UTF8));
    }

    public static String sha256Hex(String s) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] d = md.digest(s.getBytes(UTF8));
        return DatatypeConverter.printHexBinary(d).toLowerCase();
    }

    public String buildCurl(Map<String, Object> params) {
        String secretId = params.get(ParamConstants.SECRET_ID).toString();
        String secretKey = params.get(ParamConstants.SECRET_KEY).toString();
        String curl = "";
        try {
            String service = "sms";
            String host = "sms.tencentcloudapi.com";
//        String region = "ap-guangzhou";
            String action = "SendSms";
            String version = "2019-07-11";
            String algorithm = "TC3-HMAC-SHA256";
            String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            // 注意时区，否则容易出错
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            String date = sdf.format(new Date(Long.valueOf(timestamp + "000")));
            // ************* 步骤 1：拼接规范请求串 *************
            String httpRequestMethod = "POST";
            String canonicalUri = "/";
            String canonicalQueryString = "";
            String canonicalHeaders = "content-type:application/json\n" + "host:" + host + "\n";
            String signedHeaders = "content-type;host";
            String payload = buildPayload(params);
            String hashedRequestPayload = sha256Hex(payload);
            String canonicalRequest = httpRequestMethod + "\n" + canonicalUri + "\n" + canonicalQueryString + "\n"
                    + canonicalHeaders + "\n" + signedHeaders + "\n" + hashedRequestPayload;
            // ************* 步骤 2：拼接待签名字符串 *************
            String credentialScope = date + "/" + service + "/" + "tc3_request";
            String hashedCanonicalRequest = sha256Hex(canonicalRequest);
            String stringToSign = algorithm + "\n" + timestamp + "\n" + credentialScope + "\n" + hashedCanonicalRequest;
            // ************* 步骤 3：计算签名 *************
            byte[] secretDate = hmac256(("TC3" + secretKey).getBytes(UTF8), date);
            byte[] secretService = hmac256(secretDate, service);
            byte[] secretSigning = hmac256(secretService, "tc3_request");
            String signature = DatatypeConverter.printHexBinary(hmac256(secretSigning, stringToSign)).toLowerCase();
            // ************* 步骤 4：拼接 Authorization *************
            String authorization = algorithm + " " + "Credential=" + secretId + "/" + credentialScope + ", "
                    + "SignedHeaders=" + signedHeaders + ", " + "Signature=" + signature;
            TreeMap<String, String> headers = new TreeMap<String, String>();
            headers.put("Authorization", authorization);
            headers.put("Content-Type", CT_JSON);
            headers.put("Host", host);
            headers.put("X-TC-Action", action);
            headers.put("X-TC-Timestamp", timestamp);
            headers.put("X-TC-Version", version);
            StringBuilder sb = new StringBuilder();
            sb.append("curl -X POST https://").append(host)
                    .append(" -H \"Authorization: ").append(authorization).append("\"")
                    .append(" -H \"Content-Type: application/json\"")
                    .append(" -H \"Host: ").append(host).append("\"")
                    .append(" -H \"X-TC-Action: ").append(action).append("\"")
                    .append(" -H \"X-TC-Timestamp: ").append(timestamp).append("\"")
                    .append(" -H \"X-TC-Version: ").append(version).append("\"")
                    .append(" -H \"X-TC-Language: ").append("zh-CN").append("\"")
                    .append(" -d '").append(payload).append("'");
            curl = sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return curl;
    }

    private String buildPayload(Map<String, Object> params) {

        Payload payload = new Payload();
        payload.setPhoneNumberSet(new String[]{"+86" + params.get(ParamConstants.PHONE_NUM).toString()});
        payload.setSign(params.get(ParamConstants.SIGN).toString());
        payload.setSmsSdkAppid(params.get(ParamConstants.APP_ID).toString());
        payload.setTemplateID(params.get(ParamConstants.TEMPLATE_ID).toString());
        Map<String, String> vars = (Map<String, String>) params.get(ParamConstants.VARS);
        payload.setTemplateParamSet(vars.values().toArray(new String[]{}));
        return JSONObject.toJSONString(payload);
    }

    static class Payload {
        /**
         * PhoneNumberSet : ["+8618349342711"]
         * TemplateParamSet : ["152525"]
         * TemplateID : 821098
         * SmsSdkAppid : 1400465545
         * Sign : 中兴轨道通讯
         */
        private String TemplateID;
        private String SmsSdkAppid;
        private String Sign;
        private String[] PhoneNumberSet;
        private String[] TemplateParamSet;

        public void setTemplateID(String templateID) {
            TemplateID = templateID;
        }

        public void setSmsSdkAppid(String smsSdkAppid) {
            SmsSdkAppid = smsSdkAppid;
        }

        public void setSign(String sign) {
            Sign = sign;
        }

        public void setPhoneNumberSet(String[] phoneNumberSet) {
            PhoneNumberSet = phoneNumberSet;
        }

        public void setTemplateParamSet(String[] templateParamSet) {
            TemplateParamSet = templateParamSet;
        }
    }

    @Override
    public Res execute(Map<String, Object> params) {
        String curl = buildCurl(params);
        System.out.println(curl);
        ProcessBuilder process = new ProcessBuilder(curl.split(" "));
        Process p;
        JSONObject resObj = null;
        try {
            p = process.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append(System.getProperty("line.separator"));
            }
            resObj = JSONObject.parseObject(builder.toString());
            System.out.println(resObj);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (resObj == null) {
            return new Res(-1, "未知错误");
        }
        String error = resObj.getString("Error");
        if (null != error && !"".equals(error)) {
            String code = JSONObject.parseObject(error).getString("Code");
            ErrorCodes errorCodes = ErrorCodes.find(code);
            return new Res(errorCodes.pcCode, errorCodes.message);
        }
        return new Res(0, "success");
    }

    public enum ErrorCodes {

        ERROR_PHONE_NUM("InvalidParameterValue.IncorrectPhoneNumber", 32100001, "手机号格式错误"),

        ERROR_TEMPLATE_ID("FailedOperation.TemplateIncorrectOrUnapproved", 32100002, "模版未审批或请求的内容与审核通过的模版内容不匹配"),

        NET_ERROR("InternalError.SendAndRecvFail", 32100003, "接口超时或后短信收发包超时，请检查您的网络是否有波动"),

        TEMPLATE_VAR_NOR_FORMAT("InvalidParameterValue.TemplateParameterFormatError", 32100006, "验证码模板参数格式错误，验证码类模版，模版变量只能传入0 - 6位（包括6位）纯数字"),

        EXCEED_LIMIT("LimitExceeded.PhoneNumberThirtySecondLimit", 32100004, "单个手机号30秒内下发短信条数超过设定的上限，可自行到控制台调整短信频率限制策略。"),

        ERROR_KEY("AuthFailure.SignatureFailure", 32100005, "签名错误。 签名计算错误，请对照调用方式中的签名方法文档检查签名计算过程。"),

        SUCCESS("0", 0, "成功"),

        UNKNOWN("-1", -1, "未知错误");

        private String code;

        private Integer pcCode;

        private String message;

        ErrorCodes(String code, Integer pcCode, String message) {
            this.code = code;
            this.pcCode = pcCode;
            this.message = message;
        }

        private static ErrorCodes find(String code) {
            for (ErrorCodes errorCode : ErrorCodes.values()) {
                if (errorCode.code.equals(code)) {
                    return errorCode;
                }
            }
            return UNKNOWN;
        }

        public String getCode() {
            return code;
        }

        public Integer getPcCode() {
            return pcCode;
        }

        public String getMessage() {
            return message;
        }
    }

}