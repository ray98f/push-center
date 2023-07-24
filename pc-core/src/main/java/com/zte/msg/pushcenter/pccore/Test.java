//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
//import com.alibaba.fastjson.JSONObject;
//import com.arronlong.httpclientutil.HttpClientUtil;
//import com.arronlong.httpclientutil.builder.HCB;
//import com.arronlong.httpclientutil.common.HttpConfig;
//import com.arronlong.httpclientutil.common.HttpHeader;
//import com.zte.msg.pushcenter.pcscript.PcScript;
//import org.apache.http.Header;
//import org.apache.http.client.utils.URLEncodedUtils;
//import org.apache.http.impl.client.CloseableHttpClient;
//
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//import java.util.*;
//
///**
// * description:
// *
// * @author chentong
// * @version 1.0
// * @date 2021/1/18 12:40
// */
//public class GetuiPusher implements PcScript {
//
//    //1
////    private static final String APP_ID = "5cq3MPaf2b6arpOX88UaH";
////
////    private static final String APP_KEY = "f2zwndr5wq9E2qZgyFPOW8";
////
////    private static final String MASTER_SECRET = "nYtkJHxejt5DunG0Vczfu1";
//
//    //2
//    private static final String APP_ID = "KBUddlhohd8iskWv4ESaM5";
//
//    private static final String APP_KEY = "AIfFsiAFzP8eTeaEDLuWz3";
//
//    private static final String MASTER_SECRET = "rKeD328Jf185wlkX0twEi1";
//
//    private static final String SYS = "GETUI";
//
//    private static final String BASE_URL = "https://restapi.getui.com/v2/" + APP_ID;
//
//    private static final String TOKEN_URL = BASE_URL + "/auth";
//
//    private static final String PUSH_SINGLE_BATCH_URL = BASE_URL + "/push/single/batch/cid";
//
//    private static final String PUSH_LIST_CID_URL = BASE_URL + "/push/list/cid";
//
//    private static final String PUSH_LIST_MESSAGE_URL = BASE_URL + "/push/list/message";
//
//    private static final String ACTION = "action";
//
//    private static final String ACTION_TYPE = "actionType";
//
//    private static final String ACTION_DATA = "actionData";
//
//    private static final String DATA = "data";
//
//    private static final String TITLE = "title";
//
//    private static final String MESSAGE = "message";
//
//    private static final String CONTENT = "content";
//
//    private static final String PC_URL = "pcUrl";
//
//    private static final String MOBILE_URL = "mobileUrl";
//
//
////    @Autowired
////    private ForeignTokenMapper tokenMapper;
//
//    private String byte2Hex(byte[] bytes) {
//        StringBuffer stringBuffer = new StringBuffer();
//        String temp = null;
//        for (int i = 0; i < bytes.length; i++) {
//            temp = Integer.toHexString(bytes[i] & 0xFF);
//            if (temp.length() == 1) {
//                // 1得到一位的进行补0操作
//                stringBuffer.append("0");
//            }
//            stringBuffer.append(temp);
//        }
//        return stringBuffer.toString();
//    }
//
//    public TokenReqDTO generateTokenParams(Map<String, Object> params){
//        String appKey = APP_KEY;
//        String masterSecret = MASTER_SECRET;
//        MessageDigest messageDigest;
//        TokenReqDTO dto = new TokenReqDTO();
//        dto.setTimestamp(String.valueOf(System.currentTimeMillis()));
//        dto.setAppkey(appKey);
//        try {
//            messageDigest = MessageDigest.getInstance("SHA-256");
//            String origin = appKey + dto.getTimestamp() + masterSecret;
//            messageDigest.update(origin.getBytes());
//            dto.setSign(byte2Hex(messageDigest.digest()));
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return dto;
//    }
//
//    public String applyAccessToken(Map<String, Object> params) {
//        TokenReqDTO req = generateTokenParams(params);
//        String res = "";
//        Header[] headers = HttpHeader.custom().build();
//        try {
//            CloseableHttpClient client = HCB.custom().build();
//            HttpConfig config = HttpConfig.custom()
//                    .headers(headers)
//                    .url(TOKEN_URL)
//                    .json(JSON.toJSONString(req))
//                    .encoding("utf-8")
//                    .client(client)
//                    .inenc("utf-8");
//            res = HttpClientUtil.post(config);
//            client.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        JSONObject json = JSONObject.parseObject(res);
//        if ("0".equals(json.getString("code"))) {
//            String token = json.getJSONObject("data").getString("token");
//            String expire = json.getJSONObject("data").getString("expire");
////            ForeignToken foreignToken = new ForeignToken();
////            foreignToken.setToken(token); foreignToken.setExpire(expire);
////            foreignToken.setSys(SYS); foreignToken.setId(UUID.randomUUID().toString());
////
////            ForeignToken dbToken = tokenMapper.getToken(SYS);
////            if (Objects.isNull(dbToken)) {
////                tokenMapper.saveToken(foreignToken);
////            } else {
////                tokenMapper.resetToken(foreignToken);
////            }
//            return token;
//        }
//        return json.getString("msg");
//    }
//
////    public ForeignToken getAccessTokenFromDB() {
////        ForeignToken token = tokenMapper.getToken(SYS);
////        if (Objects.isNull(token)) {
////            throw new CommonException(ErrorCodes.GET_TOKEN_FAILD.getPcCode(), ErrorCodes.GET_TOKEN_FAILD.getMessage());
////        }
////        return token;
////    }
//
//    public String getAccessToken(Map<String, Object> params) {
////        ForeignToken token = getAccessTokenFromDB();
////        if (Long.valueOf(token.getExpire()) < System.currentTimeMillis()) {
//        return applyAccessToken(params);
////        }
////        return token.getToken();
//    }
//
//    public String convertAppPushParam(Map<String, Object> params) {
//        String pushParamJsonStr = convert(params);
//        System.out.println("Getui pushing:  " + pushParamJsonStr);
//        return pushParamJsonStr;
//    }
//
//    private String post(String token, String pushParmJsonStr, String url) {
//        String returnJson = null;
//        Header[] headers = HttpHeader.custom()
//                .other("token", token)
//                .build();
//        try {
//            CloseableHttpClient client = HCB.custom().build();
//            HttpConfig config = HttpConfig.custom()
//                    .headers(headers)
//                    .url(url)
//                    .json(pushParmJsonStr)
//                    .encoding("utf-8")
//                    .client(client)
//                    .inenc("utf-8");
//            returnJson = HttpClientUtil.post(config);
//            client.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return returnJson;
//    }
//
//    private String convertMessageParam(Map<String, Object> params){
//        System.out.println("Convert Message Param:" + params);
//        JSONObject res = new JSONObject();
//        JSONObject paramsJson = new JSONObject();
//        JSONObject message = new JSONObject();
//
////        message = addParams(params, ACTION, message);
////        message = addParams(ACTION, "newMessage", message);
//        addParams(params, ACTION, ACTION, message);
//        //1打开应用 2intent 3deeplink
////        message = addParams(params, ACTION_TYPE, message);
////        message = addParams(params, ACTION_DATA, message);
////        message = addParams(ACTION_TYPE, "2", message);
////        message = addParams(ACTION_DATA, "snap://open?action=openMessage", message);
//        addParams(params, ACTION_TYPE, ACTION_TYPE, message);
//        try {
//            addParams(ACTION_DATA, params.get(ACTION_DATA).toString() + URLEncoder.encode(params.get(MOBILE_URL).toString(), "UTF-8"), message);
//        } catch (UnsupportedEncodingException e) {
//            System.out.println("ActionData Exception");
//        }
//
//        JSONObject data = new JSONObject();
//        addParams(params, TITLE, TITLE, data);
//        addParams(params, MESSAGE, CONTENT, data);
//        message.put("DATA", data);
//
////        message = addParams(ACTION, "newMessage", message);
////        //1打开应用 2intent 3deeplink
////        message = addParams(ACTION_TYPE, "3", message);
////        message = addParams(ACTION_DATA, "", message);
////        JSONObject data = new JSONObject();
////        data = addParams(TITLE, "新消息测试", data);
////        data = addParams(MESSAGE, "新消息内容测试", data);
////        message.put("DATA", data);
//
//        paramsJson.put("transmission", message.toJSONString());
//        res.put("push_message", paramsJson);
//        System.out.println("message params: " + res);
//        return res.toString();
//    }
//
//    private JSONObject addParams(Map<String, Object> params, String stag, String tTag, JSONObject res) {
//        res.put(stag, params.get(tTag));
//        return res;
//    }
//
//    private JSONObject addParams(String tag, String value, JSONObject res) {
//        res.put(tag, value);
//        return res;
//    }
//
////    private String convertMessageParam(Map<String, Object> params) {
////        JSONObject pushParamJson = new JSONObject();
////        //transmission
//////        JSONObject transmission = new JSONObject();
////        String title = (String)params.get("title");
////        String content = (String)params.get("content");
//////        transmission.put("transmission", "title:" + title + "\n" + "content:" + content);
////        //notification
////        JSONObject notification = new JSONObject();
////        JSONObject param = new JSONObject();
////
////        param.put("title", title); param.put("body", content);
////        param.put("click_type", "intent");
//////        param.put("intent", "snap://open?action=openMessage");
////        param.put("intent", "intent://open?#Intent;schema=snap;S.action=openMessage;end");
//////        try {
//////            param.put("intent", URLEncoder.encode("intent://open?#Intent;schema=snap;S.action=openMessage;end", "utf-8"));
//////        } catch (UnsupportedEncodingException e) {
////////            throw new CommonException(ErrorCodes.ENCODING_NOT_SUPPORTED.getPcCode(), ErrorCodes.ENCODING_NOT_SUPPORTED.getMessage());
//////            e.printStackTrace();
//////        }
////
////        notification.put("notification", param);
////
//////        pushParamJson.put("push_message", transmission);
////        pushParamJson.put("push_message", notification);
////        System.out.println("message params: " + pushParamJson);
////        return pushParamJson.toString();
////    }
//
//
//    private class TokenReqDTO {
//        private String sign;
//        private String timestamp;
//        private String appkey;
//
//        public String getSign() {
//            return sign;
//        }
//
//        public void setSign(String sign) {
//            this.sign = sign;
//        }
//
//        public String getTimestamp() {
//            return timestamp;
//        }
//
//        public void setTimestamp(String timestamp) {
//            this.timestamp = timestamp;
//        }
//
//        public String getAppkey() {
//            return appkey;
//        }
//
//        public void setAppkey(String appKey) {
//            this.appkey = appKey;
//        }
//    }
//
//    public enum ErrorCodes {
//
//        ENCODING_NOT_SUPPORTED("1102", 32101102, "不支持的加密方法"),
//
//        GET_TOKEN_FAILD("1101",32101101, "从数据库获取TOKEN失败"),
//
//        ERROR_PARAM("1002", 32100001, "缺少了必须的参数"),
//
//        ERROR_PARAM_1("1003", 32100002, "参数值不合法"),
//
//        AUTH_FAIL("1004", 32100003, "验证失败"),
//
//        DATA_TOO_LARGE("1005", 32100006, "消息体太大"),
//
//        ERROR_APP_KEY("1008", 32100004, "app_key 参数非法"),
//
//        NOT_SUPPORT_KEY("1009", 32100005, "推送对象中有不支持的 key"),
//
//        SUCCESS("0", 0, "成功"),
//
//        UNKNOWN("-1", -1, "未收录错误");
//
//        private String code;
//
//        private Integer pcCode;
//
//        private String message;
//
//        ErrorCodes(String code, Integer pcCode, String message) {
//            this.code = code;
//            this.pcCode = pcCode;
//            this.message = message;
//        }
//
//        private static ErrorCodes find(String code) {
//            for (ErrorCodes errorCode : ErrorCodes.values()) {
//                if (errorCode.getCode().equals(code)) {
//                    return errorCode;
//                }
//            }
//            return UNKNOWN;
//        }
//
//        public String getCode() {
//            return code;
//        }
//
//        public Integer getPcCode() {
//            return pcCode;
//        }
//
//        public String getMessage() {
//            return message;
//        }
//    }
//
//    /**
//     * @param params
//     * @return
//     */
//    public String convert(Map<String, Object> params) {
//
//        JSONObject pushParamJson = new JSONObject();
////        List<GetuiPusher.Platform> platformList = Collections.singletonList(GetuiPusher.Platform.valueOf(Integer.parseInt(params.get("targetPlatform").toString())));
//        String[] registrationIds = (String[]) params.get("registrationId");
//
//        //推送目标用户
//        JSONObject audience = buildAudience(registrationIds, new JSONObject());
//        //构建推送平台信息
////        JSONArray platform = buildPlatform(platformList, new JSONArray());
//        //构建安卓的推送信息
////        JSONObject notification = new JSONObject();
////        buildAndroidInfo(notification, platformList, params);
////        buildIosInfo(notification, platformList, params);
//        //构建IOS参数信息
//        //设置推送平台信息
////        setPlatform(platform, pushParamJson);
//        //设置消息taskid
//        String taskid = (String)params.get("taskid");
//        pushParamJson.put("taskid", taskid);
//        //设置推送目标信息
//        setAudience(audience, pushParamJson);
//        //设置推送信息
////        pushParamJson.put(AppMessageType.valueOf(Integer.parseInt(params.get("messageType").toString())).messageType, notification);
////        pushParamJson.put(AppMessageType.NOTIFICATION.messageType, notification);
//
////        JSONObject options = new JSONObject();
////        buildOptions(options);
////        //设置options信息
////        pushParamJson.put("options", options);
//        return pushParamJson.toString();
//    }
//
//    private static void setAudience(JSONObject audience, JSONObject pushParamJson) {
////        if (audience == null) {
////            pushParamJson.put("audience", "all");//发广播
////        } else {
//        pushParamJson.put("audience", audience);
////        }
//    }
//
//    private static JSONObject buildAudience(String[] audienceMap, JSONObject audience) {
//        if (audienceMap == null || audienceMap.length == 0) {
//            return null;
//        }
//        JSONArray audienceArray = new JSONArray();
//        audienceArray.addAll(Arrays.asList(audienceMap));
//        audience.put("cid", audienceArray);
//        return audience;
//
//    }
//
//    private PcScript.Res pushMessage(String token, Map<String, Object> params) {
//        String pushRes = post(token, convertAppPushParam(params), PUSH_LIST_CID_URL);
//        System.out.println("======执行推送======:\n" + pushRes);
//
//        JSONObject resObj = JSONObject.parseObject(pushRes);
//
//        if (!Integer.valueOf(0).equals(resObj.get("code"))) {
//            String code = resObj.get("code").toString();
////            log.info("=====推送失败=====\n" + params.toString() + "\ntoken:" + token + "\n");
//            return new PcScript.Res(ErrorCodes.UNKNOWN.getPcCode(), ErrorCodes.UNKNOWN.getMessage());
//        }
////        log.info("=====推送成功=====\n" + params.toString() + "\ntoken:" + token + "\n");
//        return new PcScript.Res(0, "成功");
//    }
//
//    class PushTask extends TimerTask{
//        private String token;
//        private Map<String, Object> params;
//
//        PushTask(String token, Map<String, Object> params) {
//            this.token = token;
//            this.params = params;
//        }
//
//        @Override
//        public void run() {
//            pushMessage(token, params);
//        }
//    }
//
//    @Override
//    public Res execute(Map<String, Object> params) {
//        //创建消息体
//        String token = getAccessToken(params);
//        String messageRes = post(token, convertMessageParam(params), PUSH_LIST_MESSAGE_URL);
//        System.out.println("======创建消息体======:\n" + messageRes);
//        JSONObject msg = JSONObject.parseObject(messageRes);
//        if (!Integer.valueOf(0).equals(msg.get("code"))) {
//            return new PcScript.Res(ErrorCodes.UNKNOWN.getPcCode(), ErrorCodes.UNKNOWN.getMessage());
//        }
//        String taskId = JSONObject.parseObject(messageRes).getJSONObject("data").get("taskid").toString();
//        params.put("taskid", taskId);
//        //执行推送
////        String[] testIds = params.get("registrationId").toString().split(",");
////      	params.put("registrationId", testIds);
//        String[] registrationIds = (String[]) params.get("registrationId");
//        params.put("cid", registrationIds);
//        int batchSize = 1000;
//        //分批推送 1000条为一批
//        Timer timer = new Timer("推送消息");
//        for (int i = 0; i < registrationIds.length; i += batchSize) {
//            //延时任务，防止频繁调用api
//            params.put("registrationId", Arrays.copyOfRange(registrationIds, i, Math.min(i + batchSize, registrationIds.length)));
//            timer.schedule(new PushTask(token, params), i << 1);
//        }
//        return new PcScript.Res(0, "成功");
//    }
//}