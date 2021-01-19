package com.zte.msg.pushcenter.pccore.entity;

import com.zte.msg.pushcenter.pccore.core.pusher.msg.SmsMessage;
import com.zte.msg.pushcenter.pcscript.PcScript;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/23 9:51
 */
@Data
@ApiModel
public class SmsInfo extends BaseEntity {

    private String messageId;

    @ApiModelProperty(value = "应用id")
    private Long appId;

    @ApiModelProperty(value = "应用名称")
    private String appName;

    @ApiModelProperty(value = "发送号码")
    private String phoneNum;

    @ApiModelProperty(value = "发送时间")
    private Timestamp transmitTime;

    @ApiModelProperty(value = "短信内容")
    private String content;

    @ApiModelProperty(value = "发送平台")
    private String providerName;

    @ApiModelProperty(value = "模板id")
    private Long templateId;

    @ApiModelProperty(value = "发送状态")
    private Integer result;

    @ApiModelProperty(value = "错误代码")
    private Integer failCode;

    @ApiModelProperty(value = "错误消息")
    private String failReason;

    @ApiModelProperty(value = "状态时间")
    private Timestamp resultTime;

    public SmsInfo() {
    }

    public SmsInfo(String messageId, Long appId, String appName, String phoneNum, Timestamp transmitTime, String content,
                   String providerName, Long templateId, Integer result, Integer failCode,
                   String failReason, Timestamp resultTime) {
        this.messageId = messageId;
        this.appId = appId;
        this.appName = appName;
        this.phoneNum = phoneNum;
        this.transmitTime = transmitTime;
        this.content = content;
        this.providerName = providerName;
        this.templateId = templateId;
        this.result = result;
        this.failCode = failCode;
        this.failReason = failReason;
        this.resultTime = resultTime;
    }

    public SmsInfo(SmsMessage message, PcScript.Res res) {
        this.messageId = message.getMessageId();
        this.appId = message.getAppId();
        this.appName = message.getAppName();
        this.phoneNum = message.getPhoneNum()[message.getIndex()];
        this.transmitTime = message.getTransmitTime();
        this.content = String.format(message.getContent()
                .replaceAll("#.*?#", "%s")
                .replaceAll("\\{.*?}", "%s"), message.getVars().values().toArray());
        this.providerName = message.getProviderName();
        this.templateId = message.getTemplateId();
        this.failCode = res.getCode();
        this.failReason = res.getMessage();
        // TODO: 2021/1/11
//        this.resultTime =
        if (res.getCode() == 0) {
            this.result = 0;
        } else {
            this.result = 1;
        }
    }
}
