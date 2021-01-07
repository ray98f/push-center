package com.zte.msg.pushcenter.pccore.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/21 10:59
 */
@Data
@ApiModel
public class ProviderResDTO {

    private Long id;

    @ApiModelProperty(value = "供应商名称")
    private String providerName;

    @ApiModelProperty(value = "供应商服务类型")
    private Integer type;

    @ApiModelProperty(value = "脚本内容")
    private String scriptContext;

    @ApiModelProperty(value = "配置信息")
    private String config;

    @ApiModelProperty(value = "描述")
    private String description;

}
