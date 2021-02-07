package com.zte.msg.pushcenter.pccore.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.sql.Timestamp;

/**
 * @author frp
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class EarlyWarnInfo extends BaseEntity{

    @ApiModelProperty(value = "预警时间")
    private Timestamp time;

    @ApiModelProperty(value = "预警原因")
    private String reason;

    @ApiModelProperty(value = "预警内容")
    private String content;

    @ApiModelProperty(value = "处置人")
    private String disposer;

    @ApiModelProperty(value = "是否处理，0-未处理，1-已处理")
    private Integer isHandle;

}
