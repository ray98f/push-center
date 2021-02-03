package com.zte.msg.pushcenter.pccore.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author frp
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class WhiteIp extends BaseEntity{
    @ApiModelProperty(value = "应用id")
    private Long appId;

    @ApiModelProperty(value = "白名单ip")
    private String ip;
}
