package com.zte.msg.pushcenter.pccore.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/13 17:33
 */
@Data
@ApiModel
public class AppMessageReqDTO extends PushReqDTO {

    @ApiModelProperty(value = "推送平台id", required = true)
    @NotNull(message = "32000006")
    private Long providerId;

    @ApiModelProperty(value = "平台目标，1-ANDROID, 2-IOS, 3-全部", required = true)
    @NotNull(message = "32000006")
    private Integer targetPlatform;

    @ApiModelProperty(value = "推送目标，用户的cid或者设备注册的registrationId", required = true)
    @NotNull(message = "32000006")
    private String[] registrationId;

    @ApiModelProperty(value = "消息类型, 1-通知（有弹窗），2-透传（App内消息，无弹窗）", required = true)
    @NotNull(message = "32000006")
    private Integer messageType;

    @ApiModelProperty(value = "标题")
    @NotNull(message = "32000006")
    private String title;

    @ApiModelProperty(value = "内容")
    private String content;

}
