package com.zte.msg.pushcenter.pccore.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/22 14:34
 */
@Data
@ApiModel
public class SmsConfigResDTO {

    private Long id;

    @ApiModelProperty(value = "服务供应商id")
    @JsonProperty(value = "provider_id")
    @NotNull(message = "32000006")
    private Long providerId;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "第三方短信服务配置的appId")
    @JsonProperty(value = "app_id")
    private String appId;

    @ApiModelProperty(value = "第三方短信服务配置的secretId")
    @JsonProperty(value = "secret_id")
    private String secretId;

    @ApiModelProperty(value = "第三方短信服务配置的secretKey，需加密")
    @JsonProperty(value = "secret_key")
    @NotNull(message = "32000006")
    private String secretKey;
}
