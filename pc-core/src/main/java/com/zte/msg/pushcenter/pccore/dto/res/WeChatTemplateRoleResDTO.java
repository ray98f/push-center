package com.zte.msg.pushcenter.pccore.dto.res;

import com.zte.msg.pushcenter.pccore.dto.BaseResDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author frp
 */
@Data
@ApiModel
public class WeChatTemplateRoleResDTO extends BaseResDTO {

    @ApiModelProperty(value = "应用模板权限Id")
    private Integer appRoleId;

    @ApiModelProperty(value = "微信公众号名称")
    private String wechatName;

    @ApiModelProperty(value = "模板启用状态")
    private Integer status;
}
