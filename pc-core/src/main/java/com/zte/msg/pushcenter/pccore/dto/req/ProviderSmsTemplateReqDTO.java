package com.zte.msg.pushcenter.pccore.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/7 15:50
 */
@Data
@ApiModel
public class ProviderSmsTemplateReqDTO {

    @ApiModelProperty(value = "服务商模版编号")
    private String code;

    @ApiModelProperty(value = "模版内容")
    private String content;

    @ApiModelProperty(value = "短信签名")
    private String sign;

    @ApiModelProperty(value = "启用状态")
    private Integer status;


}
