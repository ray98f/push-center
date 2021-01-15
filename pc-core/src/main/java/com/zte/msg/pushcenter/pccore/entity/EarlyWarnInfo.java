package com.zte.msg.pushcenter.pccore.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author frp
 */
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

}
