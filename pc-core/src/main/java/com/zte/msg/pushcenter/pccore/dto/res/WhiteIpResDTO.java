package com.zte.msg.pushcenter.pccore.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author frp
 */
@Data
@ApiModel
public class WhiteIpResDTO {
    @ApiModelProperty(value = "应用id")
    private Long appId;

    @ApiModelProperty(value = "白名单ip")
    private List<String> ip;
}
