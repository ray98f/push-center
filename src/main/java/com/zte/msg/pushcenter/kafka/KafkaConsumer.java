package com.zte.msg.pushcenter.kafka;

import com.alibaba.fastjson.JSONObject;
import com.zte.msg.pushcenter.common.Selector;
import com.zte.msg.pushcenter.msg.Message;
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
public class KafkaConsumer extends BaseConsumer {

    @Resource
    private Selector selector;

    @Override
    protected void onDealMessage(String message) {

        selector.select(JSONObject.parseObject(message, Message.class));
    }
}
