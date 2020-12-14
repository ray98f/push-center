package com.zte.msg.pushcenter.common.pusher;

import com.alibaba.fastjson.JSONObject;
import com.zte.msg.pushcenter.msg.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/11 9:36
 */
@Slf4j
@Component
public class TelePusher extends BasePusher {

    @Override
    public CompletableFuture<JSONObject> push(Message message) {
        return null;
    }
}
