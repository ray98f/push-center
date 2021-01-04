package com.zte.msg.pushcenter.pccore.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/31 14:35
 */
@Data
@ApiModel
@Validated
public class ProviderSmsTemplateReqDTO {

    @JsonProperty(value = "provider_id")
    @ApiModelProperty(value = "服务提供商id")
    @NotNull(message = "32000006")
    private Long providerId;

    @ApiModelProperty(value = "服务商短信模版id")
    @JsonProperty(value = "s_template_id")
    @NotNull(message = "32000006")
    private String sTemplateId;

    @ApiModelProperty(value = "短信配置id")
    @JsonProperty(value = "sms_config_id")
    @NotNull(message = "32000006")
    private Long smsConfigId;

    @ApiModelProperty(value = "短信签名")
    @NotNull(message = "32000006")
    private String sign;

    @ApiModelProperty(value = "短信内容")
    @NotNull(message = "32000006")
    private String example;

    @ApiModelProperty(value = "优先级")
    @Min(value = 0)
    private Integer order;

}
