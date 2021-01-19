package com.zte.msg.pushcenter.pccore.dto.res;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author frp
 */
@Data
public class SuperMenuResDTO {

    @ApiModelProperty(value = "目录id")
    private Long catalogId;

    @ApiModelProperty(value = "目录名称")
    private String catalogName;

    @ApiModelProperty(value = "菜单信息")
    private List<MenuInfo> menuInfo;

    @Data
    public static class MenuInfo{
        @ApiModelProperty(value = "菜单id")
        private Long menuId;

        @ApiModelProperty(value = "菜单名称")
        private String menuName;
    }
}
