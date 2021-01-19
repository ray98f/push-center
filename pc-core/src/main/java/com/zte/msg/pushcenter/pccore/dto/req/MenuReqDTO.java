package com.zte.msg.pushcenter.pccore.dto.req;

import com.zte.msg.pushcenter.pccore.dto.PageReqDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author frp
 */
@Data
@ApiModel
public class MenuReqDTO {

    @ApiModelProperty(value = "名称")
    private String menuName;

    @ApiModelProperty(value = "是否使用")
    private Integer isUse;
}
