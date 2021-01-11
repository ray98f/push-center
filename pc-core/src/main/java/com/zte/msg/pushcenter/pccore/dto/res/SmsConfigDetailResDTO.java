package com.zte.msg.pushcenter.pccore.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/28 16:21
 */
@Data
@ApiModel
public class SmsConfigDetailResDTO {

    private Long id;

    @ApiModelProperty(value = "配置名称")
    private String name;

    @ApiModelProperty(value = "基础配置表id")
    private Long providerId;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "第三方短信服务配置的appId")
    private String sAppId;

    @ApiModelProperty(value = "第三方短信服务配置的secretId")
    private String secretId;

    @ApiModelProperty(value = "第三方短信服务配置的secretKey，需加密")
    private String secretKey;

    @ApiModelProperty(value = "供应商")
    private String providerName;

}
