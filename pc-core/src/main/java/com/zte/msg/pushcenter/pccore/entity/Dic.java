package com.zte.msg.pushcenter.pccore.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/21 10:31
 */
@Data
public class Dic extends BaseEntity {

    @TableField(value = "`name`")
    private String name;

    private String type;

    private Integer isEnable;

    private String description;

}
