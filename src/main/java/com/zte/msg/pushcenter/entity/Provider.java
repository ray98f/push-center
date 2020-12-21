package com.zte.msg.pushcenter.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/21 10:35
 */
@Data
@TableName(value = "provider")
public class Provider extends BaseEntity{

    @TableField(value = "provider_name")
    private String providerName;

}
