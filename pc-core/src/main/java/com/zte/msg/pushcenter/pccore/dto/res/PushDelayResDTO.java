package com.zte.msg.pushcenter.pccore.dto.res;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/2/5 17:00
 */
@ApiModel
@Data
public class PushDelayResDTO {

    private List<PushDelay> smsPushDelays;

    private List<PushDelay> mailPushDelays;

    private List<PushDelay> appPushDelays;

    private List<PushDelay> wechatDelays;

    @Data
    public static class PushDelay {

        private Timestamp time;

        private Integer delayAvg;

    }
}
