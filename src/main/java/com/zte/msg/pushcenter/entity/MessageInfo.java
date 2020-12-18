package com.zte.msg.pushcenter.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/15 9:00
 */
@Data
public class MessageInfo extends BaseEntity {

    @TableField(value = "app_id")
    private Integer appId;

    private String title;

    @TableField(value = "push_type")
    private Integer pushType;

    private Integer result;

    private Integer delay;

}
