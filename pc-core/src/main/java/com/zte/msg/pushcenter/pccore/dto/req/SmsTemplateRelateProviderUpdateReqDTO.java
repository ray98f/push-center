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
 * @date 2021/1/7 9:50
 */
@Data
@ApiModel
public class SmsTemplateRelateProviderUpdateReqDTO {

    @ApiModelProperty(value = "模版关联关系id")
    @NotNull(message = "32000006")
    private Long relationId;

    @ApiModelProperty(value = "优先级")
    @NotNull(message = "32000006")
    private Integer priority;

    @ApiModelProperty(value = "启用状态：0-启用，1-禁用")
    @NotNull(message = "32000006")
    private Integer status;

}
