package com.zte.msg.pushcenter.pccore.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/15 10:51
 */
@Data
public class WeChatInfoModel {

    @ApiModelProperty(value = "应用id")
    private Long appId;

    @ApiModelProperty(value = "应用名称")
    private String appName;

    @ApiModelProperty(value = "公众号名称")
    private String weChatName;

    @ApiModelProperty(value = "OpenID")
    private String openId;

    @ApiModelProperty(value = "模板id")
    private String templateId;

    @ApiModelProperty(value = "模板数据")
    private String templateData;

    @ApiModelProperty(value = "跳转URL")
    private String skipUrl;

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
}
