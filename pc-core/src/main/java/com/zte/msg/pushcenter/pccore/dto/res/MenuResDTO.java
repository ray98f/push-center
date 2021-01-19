package com.zte.msg.pushcenter.pccore.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author frp
 */
@Data
@ApiModel
public class MenuResDTO{

    @ApiModelProperty(value = "目录id")
    private Long menuId;

    @ApiModelProperty(value = "目录名称")
    private String menuName;

    @ApiModelProperty(value = "目录图标")
    private String menuIcon;

    @ApiModelProperty(value = "目录排序")
    private String menuSort;

    @ApiModelProperty(value = "权限标识")
    private String roleIdentify;

    @ApiModelProperty(value = "组件路径")
    private String componentPath;

    @ApiModelProperty(value = "状态(0正常,1禁用)")
    private String isUse;

    @ApiModelProperty(value = "操作时间")
    private Timestamp updatedAt;

    @ApiModelProperty(value = "菜单信息")
    private List<MenuInfo> children;

    @Data
    public static class MenuInfo{
        @ApiModelProperty(value = "菜单id")
        private Long menuId;

        @ApiModelProperty(value = "菜单名称")
        private String menuName;

        @ApiModelProperty(value = "菜单图标")
        private String menuIcon;

        @ApiModelProperty(value = "菜单排序")
        private String menuSort;

        @ApiModelProperty(value = "权限标识")
        private String roleIdentify;

        @ApiModelProperty(value = "组件路径")
        private String componentPath;

        @ApiModelProperty(value = "状态(0正常,1禁用)")
        private String isUse;

        @ApiModelProperty(value = "操作时间")
        private Timestamp updatedAt;

        @ApiModelProperty(value = "按钮信息")
        private List<ButtonInfo> children;

        @Data
        public static class ButtonInfo{
            @ApiModelProperty(value = "按钮id")
            private Long menuId;

            @ApiModelProperty(value = "按钮名称")
            private String menuName;

            @ApiModelProperty(value = "按钮图标")
            private String menuIcon;

            @ApiModelProperty(value = "按钮排序")
            private String menuSort;

            @ApiModelProperty(value = "权限标识")
            private String roleIdentify;

            @ApiModelProperty(value = "组件路径")
            private String componentPath;

            @ApiModelProperty(value = "状态(0正常,1禁用)")
            private String isUse;

            @ApiModelProperty(value = "操作时间")
            private Timestamp updatedAt;
        }
    }
}
