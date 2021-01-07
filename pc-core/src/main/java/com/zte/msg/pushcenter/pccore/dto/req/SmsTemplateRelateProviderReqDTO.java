package com.zte.msg.pushcenter.pccore.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/7 8:56
 */
@Data
@ApiModel
public class SmsTemplateRelateProviderReqDTO {

    @ApiModelProperty(value = "第三方消息平台短信模版id")
    private Long pTemplateId;

    @ApiModelProperty(value = "优先级")
    private Integer priority;

    @ApiModelProperty(value = "启用状态：0-禁用，1-启用")
    private Integer status;
}
