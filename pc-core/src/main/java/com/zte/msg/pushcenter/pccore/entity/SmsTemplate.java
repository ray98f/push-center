package com.zte.msg.pushcenter.pccore.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/6 14:22
 */
@Data
@ApiModel
public class SmsTemplate extends BaseEntity{

    @ApiModelProperty(value = "短信内容")
    private String content;

    @ApiModelProperty(value = "短信参数")
    private String params;

    @ApiModelProperty(value = "模板启用状态")
    private Integer status;

}
