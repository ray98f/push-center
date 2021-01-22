package com.zte.msg.pushcenter.pccore.core.pusher.msg;

import com.zte.msg.pushcenter.pccore.core.pusher.base.Message;
import com.zte.msg.pushcenter.pccore.dto.req.MailMessageReqDTO;
import com.zte.msg.pushcenter.pccore.enums.PushMethods;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.beans.BeanUtils;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/14 14:16
 */
@EqualsAndHashCode(callSuper = true)
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
        this.setPushMethod(PushMethods.MAIL);
        BeanUtils.copyProperties(reqDTO, this);
        return this;
    }

}
