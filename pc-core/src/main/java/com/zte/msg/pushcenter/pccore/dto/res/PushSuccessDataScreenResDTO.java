package com.zte.msg.pushcenter.pccore.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author frp
 */
@ApiModel
@Data
public class PushSuccessDataScreenResDTO {
    @ApiModelProperty(value = "推送方式")
    private String type;

    @ApiModelProperty(value = "推送方式推送总数")
    private Integer total;

    @ApiModelProperty(value = "推送方式推送成功数")
    private Integer successNum;
}
