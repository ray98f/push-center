package com.zte.msg.pushcenter.pccore.service.impl;

import com.zte.msg.pushcenter.pccore.dto.res.AppPushDataScreenResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.PushFailedStatisticsScreenResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.PushSuccessDataScreenResDTO;
import com.zte.msg.pushcenter.pccore.mapper.ScreenMapper;
import com.zte.msg.pushcenter.pccore.service.ScreenService;
import com.zte.msg.pushcenter.pccore.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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

    @Autowired
    private ScreenMapper screenMapper;

    /**
     * 应用推送信息数据
     * @return
     */
    @Override
    public List<AppPushDataScreenResDTO> appPushInfoData(){
        long now = System.currentTimeMillis();
        return screenMapper.appPushInfoData(DateUtils.zeroClockOf(now), new Timestamp(now));
    }

    /**
     * 信息推送成功数据
     * @return
     */
    @Override
    public List<PushSuccessDataScreenResDTO> pushInfoSuccessData(){
        long now = System.currentTimeMillis();
        return screenMapper.pushInfoSuccessData(DateUtils.zeroClockOf(now), new Timestamp(now));
    }

    /**
     * 信息推送失败统计
     * @return
     */
    @Override
    public List<PushFailedStatisticsScreenResDTO> pushInfoFailedStatistics(){
        long now = System.currentTimeMillis();
        return  screenMapper.pushInfoFailedStatistics(DateUtils.sevenDayBefore(now), new Timestamp(now));

    }
}
