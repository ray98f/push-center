import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.zte.msg.pushcenter.pcscript.ParamConstants;
import com.zte.msg.pushcenter.pcscript.PcScript;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class MasScript implements PcScript {

    public static final String EC_NAME = "温州市铁路与轨道交通投资集团有限公司";
    public static final String AP_ID = "OCC_PSM";
    public static final String SECRET_KEY = "&=JmFhH7bo";
    public static final String ADD_SERIAL = "1065082112079963";
    public static final String API_URL = "http://112.33.46.17:37892/sms/tmpsubmit";
    public static final String SIGN = "KdrhPb7tI";
    public static final String CODE = "Code";
    public static final String OK = "OK";
    public static final Pattern VARS_PATTERN = Pattern.compile("\\$\\{[^}]+}");
    public static final Pattern PATTERN_REX = Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}");

    public String percentEcoding(String value) throws UnsupportedEncodingException{
        return value!=null? URLEncoder.encode(value, "utf-8").replace("+", "%20").replace("*", "%2A")
                .replace("%7E", "~"):null;
    }

    public String net(Map<String, Object> params) {

        String res = "";
        try {
            //vars convert
            JSONObject jsonObject = new JSONObject(params);
            String vas = jsonObject.getJSONObject("vars").toJSONString();
            LinkedHashMap mapVars = JSON.parseObject(vas,LinkedHashMap.class, Feature.OrderedField);

            String smsContent = params.get("content").toString();

            Logger.getAnonymousLogger().info("======test2-1mapVars:" + JSON.toJSONString(mapVars) + "======");
            LinkedHashMap newVars = convertTplVar(smsContent,mapVars);

            Logger.getAnonymousLogger().info("======test2-2newVars:" + JSON.toJSONString(newVars) + "======");

            for (Object key : newVars.keySet()) {
                String value = newVars.get(key).toString();
                //判断yyyy-MM-dd hh:ss:ii
                Matcher matcher = PATTERN_REX.matcher(value);

                if (matcher.find()) {
                    System.out.println("字符串中存在日期时间字符！");
                    SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    SimpleDateFormat sdfOutput = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
                    Date date = sdfInput.parse(matcher.group());
                    String formattedDate = sdfOutput.format(date);
                    value = value.replace(matcher.group(),formattedDate);
                    newVars.put(key,value);
                    System.out.println(value);
                }
            }

            Logger.getAnonymousLogger().info("======test3smsContent:" + JSON.toJSONString(newVars) + "======");

            List<String> vasArray = (List<String>) newVars.values().stream().map(Object::toString).collect(Collectors.toList());

            //mobile
            String mobiles = params.get(ParamConstants.PHONE_NUM).toString().replace("[","")
                    .replace("]","")
                    .replace("\"","");

            String tplId = params.get(ParamConstants.TEMPLATE_ID).toString();


            //计算MAC签名 md5加密
            String payload = "";
            StringBuffer cqs = new StringBuffer();
            cqs.append(EC_NAME)
                    .append(AP_ID)
                    .append(SECRET_KEY)
                    .append(params.get(ParamConstants.TEMPLATE_ID).toString())
                    .append(mobiles)
                    .append(JSON.toJSONString(vasArray))
                    .append(SIGN)
                    .append(ADD_SERIAL);
            String mac = encrypt32(cqs.toString());

            //短信请求
            LinkedHashMap<String,Object> pl = new LinkedHashMap<>();
            pl.put("ecName",EC_NAME);
            pl.put("apId",AP_ID);
            pl.put("templateId",params.get(ParamConstants.TEMPLATE_ID).toString());
            pl.put("mobiles",mobiles);
            pl.put("params",vasArray);
            pl.put("sign",SIGN);
            pl.put("addSerial",ADD_SERIAL);
            pl.put("mac",mac);
            payload = JSON.toJSONString(pl);
            Logger.getAnonymousLogger().info("======test5Payload:" + payload + "======");
            res = doPost(API_URL,Base64.getEncoder().encodeToString(payload.getBytes(StandardCharsets.UTF_8)),null);
            Logger.getAnonymousLogger().info("======test6Res:" + res + "======");

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return res;
    }

    private String buildPayload(Map<String, Object> params) {

        Map<String, String> vars = (Map<String, String>) params.get(ParamConstants.VARS);

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

        Logger.getAnonymousLogger().info("======test1:" + JSON.toJSONString(params) + "======");

        String res = net(params);
        Logger.getAnonymousLogger().info("======testRes:" + res + "======");

        if (res == null || "".equals(res)) {
            return new Res(-1, "未知错误");
        }

        Map<String, Object> resMap = JSONObject.parseObject(res);

        String rspcod = resMap.get("rspcod").toString();
        if (!"success".equals(rspcod)) {

            ErrorCodes errorCodes = ErrorCodes.find(rspcod);
            return new Res(errorCodes.pcCode, errorCodes.message);
        }
        return new Res(0, "success");
    }

    private static String encrypt32(String encryptStr) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] md5Bytes = md5.digest(encryptStr.getBytes());
            StringBuffer hexValue = new StringBuffer();
            for (int i = 0; i < md5Bytes.length; i++) {
                int val = ((int) md5Bytes[i]) & 0xff;
                if (val < 16){
                    hexValue.append("0");
                }
                hexValue.append(Integer.toHexString(val));
            }
            encryptStr = hexValue.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return encryptStr;
    }

    private static String encrypt16(String encryptStr) {
        return encrypt32(encryptStr).substring(8, 24);
    }

    public enum ErrorCodes {

        ERROR_PHONE_NUM("IllegalMac", 32100001, "mac校验不通过"),

        ERROR_TEMPLATE_ID("IllegalSignId", 32100001, "无效的签名编码"),

        NET_ERROR("InvalidMessage", 32100001, "非法消息，请求数据解析失败"),

        TEMPLATE_VAR_NOR_FORMAT("InvalidUsrOrPwd", 32100001, "非法用户名/密码"),

        EXCEED_30S_LIMIT("NoSignId", 32100001, "未匹配到对应的签名信息。"),

        ERROR_KEY("TooManyMobiles", 32100001, "手机号数量超限（>5000），应≤5000。"),

        SUCCESS("success", 0, "成功"),

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

    public static String doPost(String pathUrl, String data, String authorization) {
        OutputStreamWriter out = null;
        BufferedReader br = null;
        String result = "";
        data=data==null?"{}":data;
        try {
            URL url = new URL(pathUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setConnectTimeout(30000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            if (authorization != null && !"".equals(authorization)) {
                conn.setRequestProperty("Authorization", authorization);
            }
            conn.connect();
            out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            out.write(data);
            out.flush();
            InputStream is = conn.getInputStream();
            br = new BufferedReader(new InputStreamReader(is));
            String str = "";
            while ((str = br.readLine()) != null) {
                result += str;
            }
            is.close();
            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //处理流文件
        return result;
    }

    private static LinkedHashMap convertTplVar(String tpl,LinkedHashMap mapVars){
        LinkedHashMap<String,Object> vars = new LinkedHashMap<>();
        int index = 0;
        String key = "";
        while (true) {
            Matcher m = VARS_PATTERN.matcher(tpl);
            if (m.find()) {
                index++;
                System.out.println(m.group());
                tpl = m.replaceFirst("第" + index + "个位置");
                key = m.group().replace("${","").replace("}","");
                for (Object mapKey : mapVars.keySet()) {
                    if(mapKey.toString().length() > 10 && mapKey.toString().indexOf(key) >=0 && key.length() == 10){
                        vars.put(key,mapVars.get(mapKey));
                        break;
                    }else{
                        if(mapVars.get(key) != null){
                            vars.put(key,mapVars.get(key));
                            break;
                        }
                    }
                }
            } else {
                break;
            }
        }
        return vars;
    }


    //test
    public static void main(String[] args){
        Map<String, Object> paramss = new HashMap<>();

        //TEST
        paramss.put(ParamConstants.TEMPLATE_ID,"4b7cfe091c7d43feb975974dbb889c5a");//温州轨道APP故障监控
        paramss.put(ParamConstants.PHONE_NUM,"[\"15858289644\"]");
        paramss.put(ParamConstants.VARS,"{\"instName\":\"192.168.0.1\",\"year\":\"2024-01-02 03:04:05\",\"month\":\"1\",\"day\":\"1\",\"when\":\"1\",\"points\":\"1\",\"seconds\":\"11\",\"alarmName\":\"xyzxyz\",\"alarmDetai\":\"XYZXYZ\"}");

        MasScript mas = new MasScript();
        mas.execute(paramss);



    }
}