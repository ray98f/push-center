package com.zte.msg.pushcenter.pccore.service.impl;

import com.zte.msg.pushcenter.pccore.dto.res.*;
import com.zte.msg.pushcenter.pccore.mapper.ScreenMapper;
import com.zte.msg.pushcenter.pccore.service.ScreenService;
import com.zte.msg.pushcenter.pccore.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2020/12/28 15:42
 */
@Service
@Slf4j
public class ScreenServiceImpl implements ScreenService {

    public static final int SEVEN = 7;

    public static final long FIFTEEN_MINUTES_MILL = 900000;

    @Resource
    private ScreenMapper screenMapper;

    @Override
    public PushDelayResDTO getAvgDelayByFifteenMinute(long now) {
        Timestamp start = DateUtils.timestamp(now - now % FIFTEEN_MINUTES_MILL - SEVEN * FIFTEEN_MINUTES_MILL);
        Timestamp end = DateUtils.timestamp(now - now % FIFTEEN_MINUTES_MILL);
        PushDelayResDTO pushDelayResDTO = new PushDelayResDTO();
        List<PushDelayResDTO.PushDelay> appPushDelays = screenMapper.selectAppPushDelayByFifteenMinute(start, end);
        List<PushDelayResDTO.PushDelay> smsPushDelays = screenMapper.selectSmsPushDelayByFifteenMinute(start, end);
        List<PushDelayResDTO.PushDelay> wechatPushDelays = screenMapper.selectWechatPushDelayByFifteenMinute(start, end);
        List<PushDelayResDTO.PushDelay> mailPushDelays = screenMapper.selectMailPushDelayByFifteenMinute(start, end);
        appPushDelays.sort(Comparator.comparing(PushDelayResDTO.PushDelay::getTime));
        smsPushDelays.sort(Comparator.comparing(PushDelayResDTO.PushDelay::getTime));
        wechatPushDelays.sort(Comparator.comparing(PushDelayResDTO.PushDelay::getTime));
        mailPushDelays.sort(Comparator.comparing(PushDelayResDTO.PushDelay::getTime));
        for (int i = 0; i < SEVEN; i++) {


        }


        pushDelayResDTO.setAppPushDelays(appPushDelays);
        pushDelayResDTO.setSmsPushDelays(smsPushDelays);
        pushDelayResDTO.setMailPushDelays(mailPushDelays);
        pushDelayResDTO.setWechatDelays(wechatPushDelays);
        return pushDelayResDTO;
    }

    /**
     * 分类每日和每周推送数
     *
     * @param now
     * @return
     */
    @Override
    public ScreenLeftTopResDTO getLeftTop(long now) {

        return screenMapper.getPushCount(DateUtils.zeroClockOf(now), DateUtils.timestamp(now),
                DateUtils.sevenDayBefore(now), DateUtils.timestamp(now));
    }

    /**
     * 应用推送信息数据
     *
     * @return
     */
    @Override
    public List<AppPushDataScreenResDTO> appPushInfoData() {
        long now = System.currentTimeMillis();
        return screenMapper.appPushInfoData(DateUtils.zeroClockOf(now), new Timestamp(now));
    }

    /**
     * 信息推送成功数据
     *
     * @return
     */
    @Override
    public List<PushSuccessDataScreenResDTO> pushInfoSuccessData() {
        long now = System.currentTimeMillis();
        return screenMapper.pushInfoSuccessData(DateUtils.zeroClockOf(now), new Timestamp(now));
    }

    /**
     * 信息推送失败统计
     *
     * @return
     */
    @Override
    public List<PushFailedStatisticsScreenResDTO> pushInfoFailedStatistics() {
        long now = System.currentTimeMillis();
        return screenMapper.pushInfoFailedStatistics(DateUtils.sevenDayBefore(now), new Timestamp(now));
    }
}
