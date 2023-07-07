package com.zte.msg.pushcenter.pccore.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@ApiModel
public class ForeignToken {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "token")
    private String token;

    @ApiModelProperty(value = "sys应用")
    private String sys;

    @ApiModelProperty(value = "到期时间")
    private String expire;

    @ApiModelProperty(value = "创建人ID")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private String createAt;

    @ApiModelProperty(value = "更新人ID")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private String updateAt;
}
