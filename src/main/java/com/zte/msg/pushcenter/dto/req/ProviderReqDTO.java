package com.zte.msg.pushcenter.dto.req;

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
 * @date 2020/12/21 10:52
 */
@Data
@ApiModel
public class ProviderReqDTO {

    @JsonProperty(value = "provider_name")
    @NotNull(message = "服务商名")
    @ApiModelProperty(value = "服务商名")
    private String providerName;

    @ApiModelProperty(value = "配置的类型：1-APP推送，2-邮件推送，3-短信推送，4-微信公众号推送")
    private Integer type;

    @JsonProperty(value = "secret_id")
    @ApiModelProperty(value = "第三方服务配置的secretId")
    private String secretId;

    @JsonProperty(value = "secret_key")
    @ApiModelProperty(value = "第三方服务配置的secretKey")
    private String secretKey;

    @JsonProperty(value = "app_id")
    @ApiModelProperty(value = "第三方服务配置的appId")
    private String appId;
}
