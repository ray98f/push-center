package com.zte.msg.pushcenter.pccore.kafka;

import com.alibaba.fastjson.JSONObject;
import com.zte.msg.pushcenter.pccore.core.pusher.MailPusher;
import com.zte.msg.pushcenter.pccore.core.pusher.SmsPusher;
import com.zte.msg.pushcenter.pccore.core.pusher.WeChatPusher;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.MailMessage;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.SmsMessage;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.WeChatMessage;
import com.zte.msg.pushcenter.pccore.enums.PushMethods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/9 13:42
 */
@Component
@Slf4j
public class KafkaConsumer extends BaseConsumer {

    @Resource
    private SmsPusher smsPusher;

    @Resource
    private MailPusher mailPusher;

    @Resource
    private WeChatPusher weChatPusher;

    @Override
    protected void onDealMessage(String messageStr) {

        JSONObject message = JSONObject.parseObject(messageStr);
        log.info("Receive a push request: {}", message);
        switch (PushMethods.valueOf(message.getString("pushMethod"))) {
            case SMS:
                smsPusher.push(message.toJavaObject(SmsMessage.class));
                break;
            case APP:
                // TODO: 2021/1/14
                break;
            case MAIL:
                mailPusher.push(message.toJavaObject(MailMessage.class));
                break;
            case WECHAT:
                weChatPusher.push(message.toJavaObject(WeChatMessage.class));
                break;
            default:
        }
    }
}
