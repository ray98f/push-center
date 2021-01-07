package com.zte.msg.pushcenter.pccore.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

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
public class PlatformSmsTemplateReqDTO {

    @ApiModelProperty(value = "供应商短信模版编号")
    @NotNull(message = "32000006")
    private String code;

    @ApiModelProperty(value = "短信签名")
    @NotNull(message = "32000006")
    private String sign;

    @ApiModelProperty(value = "短信内容")
    @NotNull(message = "32000006")
    private String content;

    @ApiModelProperty(value = "状态 0 禁用 1 启用")
    private Integer status;

    @ApiModelProperty(value = "优先级，数值越大优先级越高")
    @NotNull(message = "32000006")
    private Integer priority;

}
