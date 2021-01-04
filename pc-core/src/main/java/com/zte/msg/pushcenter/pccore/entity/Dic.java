package com.zte.msg.pushcenter.pccore.entity;

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

    private Integer key;

    private String value;

    private String type;
}
