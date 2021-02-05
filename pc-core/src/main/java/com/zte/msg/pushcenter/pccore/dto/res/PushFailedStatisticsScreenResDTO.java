package com.zte.msg.pushcenter.pccore.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author frp
 */
@ApiModel
@Data
public class PushFailedStatisticsScreenResDTO {
    @ApiModelProperty(value = "时间")
    private Timestamp transmitTime;

    @ApiModelProperty(value = "推送方式推送失败数")
    private Integer failNum;
}
