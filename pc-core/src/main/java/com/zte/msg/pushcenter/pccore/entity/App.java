package com.zte.msg.pushcenter.pccore.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author frp
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class App extends BaseEntity{

    @ApiModelProperty(value = "应用名称")
    private String appName;

    @ApiModelProperty(value = "SecretId")
    private String appKey;

    @ApiModelProperty(value = "SecretKey")
    private String appSecret;

    @ApiModelProperty(value = "应用状态")
    private Integer status;
}
