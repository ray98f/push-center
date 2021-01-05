package com.zte.msg.pushcenter.kafka;

import com.alibaba.fastjson.JSONObject;
import com.zte.msg.pushcenter.core.pusher.SmsPusher;
import com.zte.msg.pushcenter.core.pusher.msg.Message;
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

    @Override
    protected void onDealMessage(String messageStr) {

        Message message = JSONObject.parseObject(messageStr, Message.class);
        switch (message.getPushMethods()) {
            case SMS:
                smsPusher.push(message);
                break;
            case APP:

            case MAIL:

            case WECHAT:
            default:
        }
        log.info("Receive a message: {}", message);
//        selector.select(JSONObject.parseObject(message, Message.class));
    }
}
