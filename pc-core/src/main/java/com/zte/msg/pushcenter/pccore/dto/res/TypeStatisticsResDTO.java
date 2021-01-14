package com.zte.msg.pushcenter.pccore.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author frp
 */
@Data
@ApiModel
public class TypeStatisticsResDTO extends StatisticsResDTO{

    @ApiModelProperty(value = "消息类型")
    private String type;
}
