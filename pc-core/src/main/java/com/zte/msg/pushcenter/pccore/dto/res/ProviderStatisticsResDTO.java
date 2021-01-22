package com.zte.msg.pushcenter.pccore.dto.res;

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
public class ProviderStatisticsResDTO extends StatisticsResDTO{

    @ApiModelProperty(value = "消息平台名称")
    private String providerName;

    @ApiModelProperty(value = "准确率")
    private Double accuracy;
}
