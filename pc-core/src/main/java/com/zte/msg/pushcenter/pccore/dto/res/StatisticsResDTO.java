package com.zte.msg.pushcenter.pccore.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author frp
 */
@Data
@ApiModel
public class StatisticsResDTO {

    @ApiModelProperty(value = "推送总数")
    private Long totalNum;

    @ApiModelProperty(value = "成功数")
    private Long successNum;

    @ApiModelProperty(value = "失败数")
    private Long failNum;

    @ApiModelProperty(value = "平均响应时长")
    private Double avgResponseTime;

    @ApiModelProperty(value = "最小相应时长")
    private Double minResponseTime;

    @ApiModelProperty(value = "最大响应时长")
    private Double maxResponseTime;
}
