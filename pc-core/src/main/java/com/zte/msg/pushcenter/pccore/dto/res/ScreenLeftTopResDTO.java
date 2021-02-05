package com.zte.msg.pushcenter.pccore.dto.res;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/2/5 15:38
 */
@Data
@ApiModel
public class ScreenLeftTopResDTO {

    private Integer smsDaily;

    private Integer appDaily;

    private Integer wechatDaily;

    private Integer mailDaily;

    private Integer smsWeekly;

    private Integer appWeekly;

    private Integer wechatWeekly;

    private Integer mailWeekly;
}
