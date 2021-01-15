package com.zte.msg.pushcenter.pccore.core.pusher.msg;

import com.zte.msg.pushcenter.pccore.core.pusher.base.Message;
import com.zte.msg.pushcenter.pccore.dto.req.WeChatMessageReqDTO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/15 10:27
 */
@Data
public class WeChatMessage extends Message {

    private String openId;

    private Long providerId;

    private String templateId;

    private String appletAppId;

    private String data;

    private String appletData;

    private String skipUrl;

    private String appName;

    public WeChatMessage build(WeChatMessageReqDTO reqDTO) {
        BeanUtils.copyProperties(reqDTO, this);
        return this;
    }
}
