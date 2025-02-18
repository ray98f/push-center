package com.zte.msg.pushcenter.pccore.mapper;

import com.zte.msg.pushcenter.pccore.dto.res.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/21 10:29
 */
@Mapper
@Repository
public interface ScreenMapper {

    /**
     * 获取每日和每周分类推送数量
     *
     * @param startDaily
     * @param endDaily
     * @param startWeekly
     * @param endWeekly
     * @return
     */
    ScreenLeftTopResDTO getPushCount(Timestamp startDaily, Timestamp endDaily, Timestamp startWeekly, Timestamp endWeekly);

    List<PushDelayResDTO.PushDelay> selectSmsPushDelayByFifteenMinute(Timestamp start, Timestamp end);

    List<PushDelayResDTO.PushDelay> selectMailPushDelayByFifteenMinute(Timestamp start, Timestamp end);

    List<PushDelayResDTO.PushDelay> selectWechatPushDelayByFifteenMinute(Timestamp start, Timestamp end);

    List<PushDelayResDTO.PushDelay> selectAppPushDelayByFifteenMinute(Timestamp start, Timestamp end);

    /**
     * 应用推送信息数据
     *
     * @param startTime
     * @param endTime
     * @return
     */
    List<AppPushDataScreenResDTO> appPushInfoData(Timestamp startTime, Timestamp endTime);

    /**
     * 信息推送成功数据
     *
     * @param startTime
     * @param endTime
     * @return
     */
    List<PushSuccessDataScreenResDTO> pushInfoSuccessData(Timestamp startTime, Timestamp endTime);

    /**
     * 信息推送失败统计
     *
     * @param startTime
     * @param endTime
     * @return
     */
    List<PushFailedStatisticsScreenResDTO> pushInfoFailedStatistics(String startTime, String endTime);

}
