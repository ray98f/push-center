package com.zte.msg.pushcenter.pccore.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author frp
 */
@Data
@ApiModel
public class ProviderStatisticsResDTO extends StatisticsResDTO{

    @ApiModelProperty(value = "消息平台名称")
    private String providerName;
}
