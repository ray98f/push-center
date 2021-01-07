package com.zte.msg.pushcenter.pccore.dto.req;

import com.zte.msg.pushcenter.pccore.dto.PageReqDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AppListReqDTO extends PageReqDTO {

    @ApiModelProperty(value = "应用ID")
    private Integer appId;

    @ApiModelProperty(value = "应用名称")
    private String appName;

    @ApiModelProperty(value = "应用状态")
    private Integer status;

}
