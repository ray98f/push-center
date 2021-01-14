package com.zte.msg.pushcenter.pccore.core.pusher;

import com.zte.msg.pushcenter.pccore.core.pusher.base.BasePusher;
import com.zte.msg.pushcenter.pccore.core.pusher.base.Config;
import com.zte.msg.pushcenter.pccore.core.pusher.base.Message;
import com.zte.msg.pushcenter.pcscript.PcScript;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * key为公众号的AppId
     */
    private Map<Long, AccessToken> accessTokens = new HashMap<>();

    @Override
    public void push(Message message) {

    }

    @Override
    protected void persist(Message message, PcScript.Res res) {

    }

    @Override
    protected void init() {

    }

    @Data
    protected static class WxConfig implements Config {

        private Long providerId;

        private String weChatTemplateId;

        private String title;

        private String content;

        private String appId;

        private String appSecret;

    }

    @Data
    private static class AccessToken {

        private Long expire;

        private String accessToken;
    }
}
