package com.zte.msg.pushcenter.pccore.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/19 14:58
 */
@Data
public class DicData extends BaseEntity {

    @TableField(value = "`key`")
    private Integer key;

    private String value;

    private String type;

    private String description;

    @TableField(value = "`order`")
    private Integer order;

    private Integer isEnable;


}
