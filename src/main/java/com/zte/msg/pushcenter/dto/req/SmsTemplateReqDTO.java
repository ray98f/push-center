package com.zte.msg.pushcenter.dto.req;

import com.alibaba.fastjson.JSONObject;
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
 * @date 2020/12/21 10:54
 */
@Data
@ApiModel
public class SmsTemplateReqDTO {

    @ApiModelProperty(value = "模版id")
    @NotNull(message = "32000006")
    @JsonProperty(value = "provider_id")
    private Integer providerId;

    @ApiModelProperty(value = "供应商id")
    @NotNull(message = "32000006")
    @JsonProperty(value = "template_id")
    private String templateId;

    private String[] vars;
}
