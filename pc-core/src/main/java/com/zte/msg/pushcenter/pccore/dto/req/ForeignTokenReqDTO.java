package com.zte.msg.pushcenter.pccore.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class ForeignTokenReqDTO {

    @ApiModelProperty(value = "id")
    private String id;

    @ApiModelProperty(value = "token")
    private String token;

    @ApiModelProperty(value = "sys应用")
    private String sysCode;

    @ApiModelProperty(value = "到期时间")
    private String expire;

    @ApiModelProperty(value = "创建人ID")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private String createAt;

    @ApiModelProperty(value = "更新人ID")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private String updateAt;

    @ApiModelProperty(value = "操作人ID")
    private String personNo;

}
