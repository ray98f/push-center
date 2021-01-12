package com.zte.msg.pushcenter.pccore.core.pusher.msg;

import com.zte.msg.pushcenter.pccore.core.pusher.base.Message;
import com.zte.msg.pushcenter.pccore.dto.req.MailMessageReqDTO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.sql.Timestamp;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/14 14:16
 */
@Data
public class MailMessage extends Message {

    /**
     * 收件人地址
     */
    private String[] to;

    /**
     * 主题
     */
    private String subject;

    private String content;

    private Long appId;

    private Timestamp transmitTime;

    private Long providerId;

    /**
     * 抄送地址
     */
    private String[] cc;

    public MailMessage build(MailMessageReqDTO reqDTO) {
        BeanUtils.copyProperties(reqDTO, this);
        return this;
    }

}
