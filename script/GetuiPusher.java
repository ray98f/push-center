import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zte.msg.pushcenter.pcscript.ParamConstants;
import com.zte.msg.pushcenter.pcscript.PcScript;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

public class AliyunSmsDemo implements PcScript {

    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    private final static Charset UTF8 = StandardCharsets.UTF_8;

    //    private final static String CT_JSON = "application/json";
    private final static String CT_JSON = "application/x-www-form-urlencoded";
    public static final String CODE = "Code";
    public static final String OK = "OK";
    private static final String MAC_NAME = "HmacSHA1";
    private static final String ENCODING = "UTF-8";

    public static String sign(String accessSecret, String stringToSign) throws Exception {

        javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA1");

        mac.init(new javax.crypto.spec.SecretKeySpec(accessSecret.getBytes("UTF-8"), "HmacSHA1"));

        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));

        return new sun.misc.BASE64Encoder().encode(signData);

    }

    public String percentEcoding(String value) throws UnsupportedEncodingException{
        return value!=null? URLEncoder.encode(value, "utf-8").replace("+", "%20").replace("*", "%2A")
                .replace("%7E", "~"):null;
    }

    public String net(Map<String, Object> params) {
        String secretId = params.get(ParamConstants.SECRET_ID).toString();
        String secretKey = params.get(ParamConstants.SECRET_KEY).toString();
        StringBuilder res = new StringBuilder();
        InputStream is = null;
        try {
            String host = "dysmsapi.aliyuncs.com";
            String action = "SendSms";
            String version = "2017-05-25";
            String algorithm = "HMAC-SHA1";
            String timestamp = String.valueOf(System.currentTimeMillis());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            String date = sdf.format(new Date(Long.parseLong(timestamp)));
            String httpRequestMethod = "POST";
            String requestFormat = "JSON";
            String signatureVersion = "1.0";

            //计算RPC签名
            String payload = buildPayload(params);
            Map<String,String> param = new HashMap<String,String>();
            param.put("AccessKeyId", secretId);
            param.put("Action", action);
            param.put("Format", requestFormat);
//            param.put("ParamString", payload);
            param.put("SignatureMethod", algorithm);
            param.put("SignatureNonce", timestamp);
            param.put("SignatureVersion", signatureVersion);
            param.put("Timestamp", date);
            param.put("Version", version);
            param.put("PhoneNumbers", (params.get(ParamConstants.PHONE_NUM).toString()));
            param.put("TemplateParam", (JSON.toJSONString((Map<String, String>) params.get(ParamConstants.VARS))));
            param.put("TemplateCode", (params.get(ParamConstants.TEMPLATE_ID).toString()));
            param.put("SignName", (params.get(ParamConstants.SIGN).toString()));

            StringBuffer cqs = new StringBuffer();
            String[] keyArray = (String[]) param.keySet().toArray(new String[]{});
            Arrays.sort(keyArray);
            for (int i = 0; i < keyArray.length; i++) {
                cqs.append(percentEcoding(keyArray[i]));
                cqs.append("=");
                cqs.append(percentEcoding(param.get(keyArray[i])));
                if (i != keyArray.length - 1) cqs.append("&");
            }

            StringBuffer stringToSign = new StringBuffer();
            stringToSign.append(httpRequestMethod).append("&")
                    .append(percentEcoding("/")).append("&")
//                    .append(percentEcoding(percentEcoding(payload))).append("&")
                    .append(percentEcoding(cqs.toString()))
                    .append(percentEcoding(""));
//                    .append(percentEcoding("&" + percentEcoding(payload)))
//                    .append(percentEcoding("="));

            String signature = sign(secretKey + "&", stringToSign.toString());
            signature = percentEcoding(signature);
            HttpURLConnection conn = null;
            BufferedReader reader;
            try {
                URL url = new URL("https://" + host
                        + "/?SignatureVersion=" + signatureVersion
                        + "&Action=" + action
                        + "&Format=" + requestFormat
                        + "&SignatureNonce=" + timestamp
                        + "&Version=" + version
                        + "&AccessKeyId=" + secretId
                        + "&Signature=" + signature
                        + "&SignatureMethod=" + algorithm
                        + "&Timestamp=" + date);
//                        + "&PhoneNumbers=" + params.get(ParamConstants.PHONE_NUM).toString()
//                        + "&TemplateParam" + JSON.toJSONString(((Map<String, String>) params.get(ParamConstants.VARS)).values())
//                        + "&TemplateCode" + params.get(ParamConstants.TEMPLATE_ID).toString()
//                        + "&SignName" + params.get(ParamConstants.SIGN).toString());
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod(httpRequestMethod);
                conn.setUseCaches(false);
                conn.setDoOutput(true);
                conn.setDoInput(true);
                conn.setConnectTimeout(DEF_CONN_TIMEOUT);
                conn.setReadTimeout(DEF_READ_TIMEOUT);
                conn.setInstanceFollowRedirects(false);
                conn.setRequestProperty("Content-Type", CT_JSON);
                conn.setRequestProperty("Host", host);
//                conn.setRequestProperty("Action", action);
//                conn.setRequestProperty("AccessKeyId", ParamConstants.SECRET_ID);
//                conn.setRequestProperty("Timestamp", date);
//                conn.setRequestProperty("Version", version);
//                conn.setRequestProperty("SignatureNonce", timestamp);
//                conn.setRequestProperty("SignatureMethod", algorithm);
//                conn.setRequestProperty("SignatureVersion", signatureVersion);
//                conn.setRequestProperty("Signature", signature);
//                conn.setRequestProperty("Format", requestFormat);

//                conn.setRequestProperty("ParamString", payload);
                conn.connect();

                OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
                writer.write(payload);
                writer.flush();
                writer.close();

                if (conn.getResponseCode() >= 400) {
                    is = conn.getErrorStream();
                } else {
                    is = conn.getInputStream();
                }

                reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                String strRead;
                while ((strRead = reader.readLine()) != null) {
                    res.append(strRead);
                }
                reader.close();

                Logger.getAnonymousLogger().info("======" + stringToSign + "======");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (conn != null) {
                    conn.disconnect();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res.toString();
    }

    private String buildPayload(Map<String, Object> params) {

        Map<String, String> vars = (Map<String, String>) params.get(ParamConstants.VARS);
//        String res = "{\"PhoneNumbers\":[\"" +
//                params.get(ParamConstants.PHONE_NUM).toString() +
//                "\"],\"TemplateParam\":" +
//                JSON.toJSONString(vars.values()) +
//                ",\"TemplateCode\":\"" +
//                params.get(ParamConstants.TEMPLATE_ID).toString() +
//                "\",\"SignName\":\"" +
//                params.get(ParamConstants.SIGN).toString() +
//                "\"}";
        String res = "PhoneNumbers=" + params.get(ParamConstants.PHONE_NUM).toString()
                + "&TemplateParam=" +  JSON.toJSONString(vars)
                + "&TemplateCode=" + params.get(ParamConstants.TEMPLATE_ID).toString()
                + "&SignName=" + params.get(ParamConstants.SIGN).toString();
        //        		+ "&test=" + JSON.toJSONString(params);
        Logger.getAnonymousLogger().info("======payload:" + res + "======");
        return res;
    }


    @Override
    public Res execute(Map<String, Object> params) {
//        Logger.getAnonymousLogger().info("======test:" + JSON.toJSONString(params) + "======");
        Map<String, String> vars = (Map<String, String>) params.get(ParamConstants.VARS);
        for (String key : vars.keySet()) {
            vars.put(key, vars.get(key).replace("%", "百分比"));
        }

        String res = net(params);
        System.out.println(res);
        if (res == null || "".equals(res)) {
            return new Res(-1, "未知错误");
        }

        Map<String, Object> innerMap = JSONObject.parseObject(res).getInnerMap();

        JSONObject error = (JSONObject) innerMap.get("Error");
        if (null != error) {
            String code = error.getString(CODE);
            ErrorCodes errorCodes = ErrorCodes.find(code);
            return new Res(errorCodes.pcCode, errorCodes.message);
        }

        JSONObject response = (JSONObject) innerMap.get("Response");

        JSONArray sendStatusSet = (JSONArray) response.get("SendStatusSet");
        JSONObject sendStatus = (JSONObject) sendStatusSet.get(0);
        if (!sendStatus.get(CODE).toString().equals(OK)) {
            String code = sendStatus.get(CODE).toString();
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

        EXCEED_30S_LIMIT("LimitExceeded.PhoneNumberThirtySecondLimit", 32100004, "单个手机号30秒内下发短信条数超过设定的上限，可自行到控制台调整短信频率限制策略。"),

        ERROR_KEY("AuthFailure.SignatureFailure", 32100005, "签名错误。 签名计算错误，请对照调用方式中的签名方法文档检查签名计算过程。"),

        EXCEED_DAILY_LIMIT("LimitExceeded.PhoneNumberDailyLimit", 32100004, "单个手机号日内下发短信条数超过设定的上限"),

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