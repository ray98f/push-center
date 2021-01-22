package com.zte.msg.pushcenter.pccore.entity;

import com.zte.msg.pushcenter.pccore.core.pusher.msg.AppMessage;
import com.zte.msg.pushcenter.pcscript.PcScript;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;

/**
 * @author frp
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class ApplicationInfo extends BaseEntity {

    private String messageId;

    @ApiModelProperty(value = "应用id")
    private Long appId;

    @ApiModelProperty(value = "应用名称")
    private String appName;

    @ApiModelProperty(value = "目标平台")
    private Integer targetPlatform;

    @ApiModelProperty(value = "推送目标（设备注册id）")
    private String audience;

    @ApiModelProperty(value = "app消息标题")
    private String title;

    @ApiModelProperty(value = "app消息内容")
    private String content;

    @ApiModelProperty(value = "发送时间")
    private Timestamp transmitTime;

    @ApiModelProperty(value = "发送平台")
    private String providerName;

    private Integer delay;

    @ApiModelProperty(value = "发送状态")
    private Integer result;

    @ApiModelProperty(value = "错误代码")
    private Integer failCode;

    @ApiModelProperty(value = "错误消息")
    private String failReason;

    public ApplicationInfo() {
    }

    public ApplicationInfo(String messageId, Long appId, String appName, Integer targetPlatform,
                           String audience, String title, String content, Timestamp transmitTime,
                           String providerName, Integer delay, Integer result, Integer failCode, String failReason) {
        this.messageId = messageId;
        this.appId = appId;
        this.appName = appName;
        this.targetPlatform = targetPlatform;
        this.audience = audience;
        this.title = title;
        this.content = content;
        this.transmitTime = transmitTime;
        this.providerName = providerName;
        this.delay = delay;
        this.result = result;
        this.failCode = failCode;
        this.failReason = failReason;
    }

    public ApplicationInfo(AppMessage appMessage, PcScript.Res res) {
        this.messageId = appMessage.getMessageId();
        this.appId = appMessage.getAppId();
        this.appName = appMessage.getAppName();
        this.targetPlatform = appMessage.getTargetPlatform();
        this.audience = StringUtils.join(appMessage.getRegistrationId(), ",");
        this.title = appMessage.getTitle();
        this.content = appMessage.getContent();
        this.transmitTime = appMessage.getTransmitTime();
        this.providerName = appMessage.getProviderName();
        this.delay = appMessage.getDelay();
        if (res.getCode() != 0) {
            this.result = 1;
        } else {
            this.result = 0;
        }
        this.failCode = res.getCode();
        this.failReason = res.getMessage();
    }
}
