package com.zte.msg.pushcenter.pccore.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.WeChatMessage;
import com.zte.msg.pushcenter.pcscript.PcScript;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2021/1/13 8:47
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
@TableName(value = "wechat_info")
public class WeChatInfo extends BaseEntity {

    private String messageId;

    @ApiModelProperty(value = "应用id")
    private Long appId;

    @ApiModelProperty(value = "应用名称")
    private String appName;

    @ApiModelProperty(value = "公众号名称")
    @TableField(value = "wechat_name")
    private String weChatName;

    @ApiModelProperty(value = "OpenID")
    private String openId;

    @ApiModelProperty(value = "模板id")
    private Long templateId;

    @ApiModelProperty(value = "模板数据")
    private String templateData;

    @ApiModelProperty(value = "跳转URL")
    private String skipUrl;

    private Integer delay;

    @ApiModelProperty(value = "小程序数据")
    private String appletData;

    @ApiModelProperty(value = "发送时间")
    private Timestamp transmitTime;

    @ApiModelProperty(value = "发送状态")
    private Integer result;

    @ApiModelProperty(value = "错误代码")
    private Integer failCode;

    @ApiModelProperty(value = "错误消息")
    private String failReason;

    public WeChatInfo() {
    }

    public WeChatInfo(String messageId, Long appId, String appName, String weChatName, String openId, Long templateId,
                      String templateData, String skipUrl, Integer delay, String appletData, Timestamp transmitTime,
                      Integer result, Integer failCode, String failReason) {
        this.messageId = messageId;
        this.appId = appId;
        this.appName = appName;
        this.weChatName = weChatName;
        this.openId = openId;
        this.templateId = templateId;
        this.templateData = templateData;
        this.skipUrl = skipUrl;
        this.delay = delay;
        this.appletData = appletData;
        this.transmitTime = transmitTime;
        this.result = result;
        this.failCode = failCode;
        this.failReason = failReason;
    }

    public WeChatInfo(WeChatMessage message, PcScript.Res res) {
        this.messageId = message.getMessageId();
        this.appId = message.getAppId();
        this.appName = message.getAppName();
        this.weChatName = message.getProviderName();
        this.openId = message.getOpenId();
        this.templateId = message.getTemplateId();
        this.templateData = message.getData();
        this.skipUrl = message.getSkipUrl();
        this.delay = message.getDelay();
        this.appletData = message.getAppletData();
        this.transmitTime = message.getTransmitTime();
        if (res.getCode() == 0) {
            this.result = 0;
        } else {
            this.result = 1;
        }
        this.failCode = res.getCode();
        this.failReason = res.getMessage();
    }
}
