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
public class RoleReqDTO extends PageReqDTO {

    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "权限字符")
    private String roleStr;

    @ApiModelProperty(value = "排序")
    private Integer roleSort;

    @ApiModelProperty(value = "状态")
    private String status;

    @ApiModelProperty(value = "菜单权限id（逗号隔开）")
    private String menuId;

    @ApiModelProperty(value = "菜单权限标识（逗号隔开）")
    private String menuRole;

    @ApiModelProperty(value = "备注")
    private String remark;
}
