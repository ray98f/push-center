package com.zte.msg.pushcenter.pccore.dto.res;

import com.zte.msg.pushcenter.pccore.entity.SmsTemplate;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author frp
 */
@Data
@ApiModel
public class TemplateResDTO extends SmsTemplate {

    @ApiModelProperty(value = "应用模板权限Id")
    private Integer appRoleId;
}
