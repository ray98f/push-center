package com.zte.msg.pushcenter.pccore.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/15 14:43
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class WeChatMessageReqDTO extends PushReqDTO {

    @ApiModelProperty(value = "接收者OpenId", required = true)
    @NotNull(message = "32000006")
    private String openId;

    @ApiModelProperty(value = "公众号providerId")
    @NotNull(message = "32000006")
    private Long providerId;

    @ApiModelProperty(value = "模版id", required = true)
    private Long templateId;

    @ApiModelProperty(value = "模板数据", required = true)
    @NotNull(message = "32000006")
    private String data;

    @ApiModelProperty(value = "跳转url")
    private String url;

    @ApiModelProperty(value = "小程序数据")
    private String appletData;

}
