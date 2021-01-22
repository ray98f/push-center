package com.zte.msg.pushcenter.pccore.core.pusher.msg;

import com.zte.msg.pushcenter.pccore.core.pusher.SmsPusher;
import com.zte.msg.pushcenter.pccore.core.pusher.base.Message;
import com.zte.msg.pushcenter.pccore.dto.req.SmsMessageReqDTO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.Instant;
import org.springframework.beans.BeanUtils;

import java.sql.Timestamp;
import java.util.Map;


/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/10 11:13
 */
@Data
@Slf4j
public class SmsMessage extends Message {

    /**
     * 手机号码
     */
    private String[] phoneNum;

    /**
     * 手机号码数组index
     */
    private int index;

    private String content;

    /**
     * 短信模版id
     */
    private Long templateId;

    private String code;

    private Map<String, String> vars;

    public SmsMessage build(SmsMessageReqDTO reqDTO) {
        BeanUtils.copyProperties(reqDTO, this);
        return this;
    }

    public SmsMessage build(SmsPusher.SmsConfig smsConfig) {
        this.setTransmitTime(new Timestamp(Instant.now().getMillis()));
        this.setContent(smsConfig.getContent());
        this.setProviderName(smsConfig.getProviderName());
        this.setCode(smsConfig.getCode());
        return this;
    }
}
