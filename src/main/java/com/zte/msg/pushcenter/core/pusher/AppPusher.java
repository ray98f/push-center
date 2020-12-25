package com.zte.msg.pushcenter.core.pusher;

import com.alibaba.fastjson.JSONObject;
import com.zte.msg.pushcenter.core.BasePusher;
import com.zte.msg.pushcenter.core.pusher.msg.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/11 9:35
 */
@Slf4j
@Component
public class AppPusher extends BasePusher {
    @Override
    public void submit(Message message) {
    }

}
