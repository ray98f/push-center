package com.zte.msg.pushcenter.pccore.dto.req;

import com.zte.msg.pushcenter.pccore.dto.PageReqDTO;
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
public class StatisticsReqDTO extends PageReqDTO {

    @ApiModelProperty(value = "搜索开始时间")
    private String startTime;

    @ApiModelProperty(value = "搜索结束时间")
    private String endTime;
}
