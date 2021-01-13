package com.zte.msg.pushcenter.pccore.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author frp
 */
@Data
@ApiModel
public class SecretKeyResDTO {

    @ApiModelProperty(value = "应用Key")
    private String appKey;

    @ApiModelProperty(value = "应用密码")
    private String appSecret;

    @ApiModelProperty(value = "状态")
    private Integer status;
}
