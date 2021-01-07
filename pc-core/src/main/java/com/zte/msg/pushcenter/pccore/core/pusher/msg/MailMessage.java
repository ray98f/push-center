package com.zte.msg.pushcenter.pccore.core.pusher.msg;

import com.zte.msg.pushcenter.pccore.dto.req.MailMessageReqDTO;
import com.zte.msg.pushcenter.pccore.utils.UidUtils;
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

    /**
     * 抄送地址
     */
    private String[] cc;

    public MailMessage build(MailMessageReqDTO reqDTO) {
        BeanUtils.copyProperties(reqDTO, this);
        this.setMessageId(UidUtils.getInstance().nextId());
        this.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return this;
    }

}
