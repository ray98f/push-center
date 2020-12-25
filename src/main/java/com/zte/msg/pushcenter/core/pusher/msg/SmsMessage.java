package com.zte.msg.pushcenter.core.pusher.msg;

import com.alibaba.fastjson.JSONObject;
import com.zte.msg.pushcenter.dto.req.SmsMessageReqDTO;
import com.zte.msg.pushcenter.utils.UidUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    private String secretId;

    private String secretKey;

    private String appId;

    private String sign;

    private List<SmsMessage> smsMessages = new ArrayList<>(50);

    public SmsMessage build(SmsMessageReqDTO reqDTO) {
        BeanUtils.copyProperties(reqDTO, this);
        this.setMessageId(UidUtils.getInstance().nextId());
        this.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return this;
    }

    @Override
    public JSONObject push() {

        log.info("==========start sms push==========");

        return null;
    }

    @Override
    public void persistence() {

        if (smsMessages.size() >= 30) {
            log.info("==========start sms persistence==========");
        }
    }



}
