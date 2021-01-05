package com.zte.msg.pushcenter.pccore.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.Instant;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/15 8:54
 */
@Data
public class BaseEntity {

    @ApiModelProperty(hidden = true)
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @JsonIgnore
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private Instant createdAt;

    @JsonIgnore
    @TableField(value = "updated_at", fill = FieldFill.INSERT, update = "NOW()")
    private Instant updatedAt;

    @JsonIgnore
    @TableLogic
    @TableField(value = "flag", fill = FieldFill.INSERT)
    private Integer flag;
}
