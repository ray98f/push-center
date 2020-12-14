package com.zte.msg.pushcenter.msg;

import com.zte.msg.pushcenter.dto.req.SmsMessageReqDTO;
import com.zte.msg.pushcenter.utils.UidUtils;
import lombok.Data;
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
public class SmsMessage extends Message {

    /**
     * 手机号码
     */
    private String[] phoneNums;

    /**
     * 短信模版id
     */
    private String templateId;

    /**
     * 短信服务提供商
     */
    private String provider;

    /**
     * 变量列表
     */
    private String[] var;

    private String secretId;
    private String secretKey;
    private String appId;
    private String sign;

    /**
     * 调用Auth接口返回的token
     */
    private String token;

    public SmsMessage build(SmsMessageReqDTO reqDTO) {
        BeanUtils.copyProperties(reqDTO, this);
        this.setMessageId(UidUtils.getInstance().nextId());
        this.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return this;
    }

}
