package com.zte.msg.pushcenter.pccore.service.impl;

import com.baomidou.mybatisplus.extension.api.R;
import com.zte.msg.pushcenter.pccore.dto.res.AppPushDataScreenResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.PushFailedStatisticsScreenResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.PushSuccessDataScreenResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.ScreenLeftTopResDTO;
import com.zte.msg.pushcenter.pccore.mapper.ScreenMapper;
import com.zte.msg.pushcenter.pccore.service.ScreenService;
import com.zte.msg.pushcenter.pccore.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
    @Resource
    private ScreenMapper screenMapper;

    @Override
    public ScreenLeftTopResDTO getLeftTop(long now) {

        screenMapper.getPushCount(DateUtils.zeroClockOf(now), DateUtils.now(now));
        screenMapper.getPushCount(DateUtils.sevenDayBefore(now), DateUtils.now(now));
        return null;
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
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        String startTime = sf.format(DateUtils.sevenDayBefore(now));
        String endTime = sf.format(new Timestamp(now));
        return screenMapper.pushInfoFailedStatistics(startTime, endTime);
    }
}
