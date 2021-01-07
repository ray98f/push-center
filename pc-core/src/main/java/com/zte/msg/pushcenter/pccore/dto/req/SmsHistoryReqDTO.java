package com.zte.msg.pushcenter.pccore.dto.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zte.msg.pushcenter.pccore.dto.PageReqDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel
public class SmsHistoryReqDTO extends PageReqDTO {

    @ApiModelProperty(value = "应用id")
    @JsonProperty(value = "app_id")
    private Integer appId;

    @ApiModelProperty(value = "应用名称")
    @JsonProperty(value = "app_name")
    private String appName;

    @ApiModelProperty(value = "发送号码")
    @JsonProperty(value = "phone_num")
    private String phoneNum;

    @ApiModelProperty(value = "搜索开始时间")
    @DateTimeFormat(pattern = "yyyy-MM-DD HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-DD HH:mm:ss")
    private Date startTime;

    @ApiModelProperty(value = "搜索结束时间")
    @DateTimeFormat(pattern = "yyyy-MM-DD HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-DD HH:mm:ss")
    private Date endTime;

    @ApiModelProperty(value = "发送状态")
    private Integer result;

    @ApiModelProperty(value = "模板id")
    @JsonProperty(value = "template_id")
    private Integer templateId;
}
