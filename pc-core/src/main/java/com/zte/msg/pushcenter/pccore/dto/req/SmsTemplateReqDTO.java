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
 * @date 2020/12/21 10:54
 */
@Data
@ApiModel
public class SmsTemplateReqDTO {

    @ApiModelProperty(value = "模版内容")
    @NotNull(message = "32000006")
    private String content;

    @ApiModelProperty(value = "参数列表")
    private String[] params;

    @ApiModelProperty(value = "状态：0-禁用，1-启用")
    private Integer status;


}
