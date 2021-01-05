package com.zte.msg.pushcenter.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/21 13:54
 */
@Data
@TableName(value = "template")
public class Template extends BaseEntity {

    private Integer appRoleId;

    private String name;

    private Integer type;

    private String description;

    private Integer status;

}
