package com.zte.msg.pushcenter.pccore.service;

import com.zte.msg.pushcenter.pccore.dto.res.AppPushDataScreenResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.PushFailedStatisticsScreenResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.PushSuccessDataScreenResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.ScreenLeftTopResDTO;

import java.util.List;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2020/12/23 15:42
 */
public interface ScreenService {

    ScreenLeftTopResDTO getLeftTop(long now);

    /**
     * 应用推送信息数据
     * @return
     */
    List<AppPushDataScreenResDTO> appPushInfoData();

    /**
     * 信息推送成功数据
     * @return
     */
    List<PushSuccessDataScreenResDTO> pushInfoSuccessData();

    /**
     * 信息推送失败统计
     * @return
     */
    List<PushFailedStatisticsScreenResDTO> pushInfoFailedStatistics();
}
