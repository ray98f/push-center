package com.zte.msg.pushcenter.common.pusher;

import com.alibaba.fastjson.JSONObject;
import com.zte.msg.pushcenter.enums.ErrorCode;
import com.zte.msg.pushcenter.exception.CommonException;
import com.zte.msg.pushcenter.common.pusher.msg.Message;
import com.zte.msg.pushcenter.common.pusher.msg.WeChatMessage;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/15 9:26
 */
@Slf4j
@Component
public class WeChatPusher extends BasePusher {

    private static WxMpService service = new WxMpServiceImpl();
    private static Map<String, WxMpConfigStorage> map = new HashMap<>();

    @Resource
    private RestTemplate restTemplate;

    @Override
    public void submit(Message message) {
        WeChatMessage weChatMessage = (WeChatMessage) message;
        if (map.containsKey(weChatMessage.getWeChatAppId())) {
            WxMpDefaultConfigImpl configStorage = new WxMpDefaultConfigImpl();
            configStorage.setAppId(weChatMessage.getWeChatAppId());
            configStorage.setSecret(weChatMessage.getAppSecret());
            configStorage.setToken(weChatMessage.getAppToken());
            configStorage.setAesKey(weChatMessage.getAesKey());
            map.put(weChatMessage.getWeChatAppId(), configStorage);
            service.setMultiConfigStorages(map);
        }
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser(weChatMessage.getTargetWeChatId())
                .templateId(weChatMessage.getTemplateId())
//                .url(weChatMessage.get)
                .build();
        CompletableFuture.supplyAsync(() -> {
            JSONObject var = weChatMessage.getVar();
            Map<String, Object> innerMap = var.getInnerMap();
            for (String key : innerMap.keySet()) {
                templateMessage.addData(new WxMpTemplateData(key, var.getString(key)));
            }
            String res = "";
            try {
                res = service.getTemplateMsgService().sendTemplateMsg(templateMessage);
            } catch (WxErrorException e) {
                e.printStackTrace();
            }
            return JSONObject.parseObject(res);
        }, pushExecutor).exceptionally(e -> {
            log.error("Error while send a weChat message: {}", e.getMessage());
            throw new CommonException(ErrorCode.WECHAT_PUSH_ERROR);
        }).thenAccept(this::response);
    }

    @Override
    public void response(JSONObject res) {
        String url = "http://";
    }

//    private JSONObject getAccessKey() {
//
//    }
}
