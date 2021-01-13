package com.zte.msg.pushcenter.pccore.entity;

import com.zte.msg.pushcenter.pccore.core.pusher.msg.MailMessage;
import com.zte.msg.pushcenter.pcscript.PcScript;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;

/**
 * @author frp
 */
@Data
@ApiModel
public class MailInfo extends BaseEntity {

    @ApiModelProperty(value = "应用id")
    private Long appId;

    @ApiModelProperty(value = "应用名称")
    private String appName;

    @ApiModelProperty(value = "收件地址")
    private String receiveAddress;

    @ApiModelProperty(value = "抄送地址")
    private String ccAddress;

    @ApiModelProperty(value = "邮件标题")
    private String mailTitle;

    @ApiModelProperty(value = "邮件内容")
    private String mailBody;

    @ApiModelProperty(value = "发送平台")
    private String providerName;

    @ApiModelProperty(value = "发送时间")
    private Timestamp transmitTime;

    @ApiModelProperty(value = "发送状态")
    private Integer result;

    @ApiModelProperty(value = "错误代码")
    private Integer failCode;

    @ApiModelProperty(value = "错误消息")
    private String failReason;

    public MailInfo(MailMessage mailMessage, PcScript.Res res) {
        this.appId = mailMessage.getAppId();
        this.appName = mailMessage.getAppName();
        this.receiveAddress = StringUtils.join(mailMessage.getTo(), ",");
        this.ccAddress = StringUtils.join(mailMessage.getCc(), ",");
        this.mailTitle = mailMessage.getSubject();
        this.mailBody = mailMessage.getContent();
        // TODO: 2021/1/12
        this.providerName = "网易";
        this.transmitTime = mailMessage.getTransmitTime();
        this.result = res.getCode();
        if (res.getCode() != 0) {
            this.failCode = res.getCode();
            this.failReason = res.getMessage();
        }
    }

}
