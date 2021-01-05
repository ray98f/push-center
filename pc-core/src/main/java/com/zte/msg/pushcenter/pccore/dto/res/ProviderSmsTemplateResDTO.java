package com.zte.msg.pushcenter.pccore.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/5 12:37
 */
@Data
@ApiModel
public class ProviderSmsTemplateResDTO {

//    private Long id;

    @ApiModelProperty(value = "模版主表id")
    @JsonProperty(value = "sms_template_id")
    private Long smsTemplateId;

    @JsonProperty(value = "provider_name")
    @ApiModelProperty(value = "服务商")
    private String providerName;

    @ApiModelProperty(value = "模版关联的短信配置id")
    @JsonProperty(value = "sms_config_id")
    private Long smsConfigId;

    @ApiModelProperty(value = "模版关联的短信配置名称")
    private String smsConfigName;

    @ApiModelProperty(value = "短信签名")
    private String sign;

    @ApiModelProperty(value = "短信内容")
    private String example;

    @ApiModelProperty(value = "优先级")
    private Integer order;

}
