package com.zte.msg.pushcenter.pccore.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class RegisterInfo extends BaseEntity{

    @ApiModelProperty(value = "系统编码")
    private String sysCode;

    @ApiModelProperty(value = "注册ID")
    private String cid;

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "手机型号")
    private String mobileVersion;
}
