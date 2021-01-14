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
 * @date 2020/12/15 14:43
 */
@ApiModel
@Data
public class WeChatMessageReqDTO extends PushReqDTO {

    @ApiModelProperty(value = "接收者OpenId", required = true)
    @NotNull(message = "32000006")
    private String openId;

    @ApiModelProperty(value = "公众号providerId")
    @NotNull(message = "32000006")
    private String providerId;

    @ApiModelProperty(value = "模版id", required = true)
    private String templateId;

    @ApiModelProperty(value = "所需跳转到的小程序appid")
    @NotNull(message = "32000006")
    private String appletAppId;

    @ApiModelProperty(value = "模板数据", required = true)
    @NotNull(message = "32000006")
    private String data;

    @ApiModelProperty(value = "小程序数据")
    private String appletData;

}
