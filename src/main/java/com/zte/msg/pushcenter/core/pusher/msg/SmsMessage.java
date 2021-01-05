package com.zte.msg.pushcenter.core.pusher.msg;

import com.zte.msg.pushcenter.dto.req.SmsMessageReqDTO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
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
    private String phoneNum;

    /**
     * 短信模版id
     */
    private String templateId;

    private String appId;

    private Map<String, String> vars;

    public SmsMessage build(SmsMessageReqDTO reqDTO) {
        BeanUtils.copyProperties(reqDTO, this);
        this.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return this;
    }
}
