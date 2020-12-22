package com.zte.msg.pushcenter.common.pusher.msg;

import com.zte.msg.pushcenter.dto.req.SmsMessageReqDTO;
import com.zte.msg.pushcenter.utils.UidUtils;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.sql.Timestamp;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/10 11:13
 */
@Data
public class SmsMessage extends Message {

    /**
     * 手机号码
     */
    private String[] phoneNums;

    /**
     * 短信模版id
     */
    private String templateId;

    private String secretId;
    private String secretKey;
    private String appId;
    private String sign;


    public SmsMessage build(SmsMessageReqDTO reqDTO) {
        BeanUtils.copyProperties(reqDTO, this);
        this.setMessageId(UidUtils.getInstance().nextId());
        this.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return this;
    }

}
