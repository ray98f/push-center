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
 * @date 2020/12/21 10:52
 */
@Data
@ApiModel
public class ProviderReqDTO {

    @ApiModelProperty(value = "描述信息")
    private String description;

    @NotNull(message = "32000006")
    @ApiModelProperty(value = "服务商名")
    private String providerName;

    @ApiModelProperty(value = "服务商类型")
    @NotNull(message = "32000006")
    private Integer type;

    @ApiModelProperty(value = "推送脚本内容")
    private String scriptContext;

    @ApiModelProperty(value = "配置信息Json字符串")
    private String config;


}
