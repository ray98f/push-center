package com.zte.msg.pushcenter.pccore.mapper;

import com.zte.msg.pushcenter.pccore.dto.res.ScreenLeftTopResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.AppPushDataScreenResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.PushFailedStatisticsScreenResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.PushSuccessDataScreenResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
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

    @Select("")
    ScreenLeftTopResDTO getPushCount(Timestamp start, Timestamp end);
    /**
     * 应用推送信息数据
     * @param startTime
     * @param endTime
     * @return
     */
    List<AppPushDataScreenResDTO> appPushInfoData(Timestamp startTime, Timestamp endTime);

    /**
     * 信息推送成功数据
     * @param startTime
     * @param endTime
     * @return
     */
    List<PushSuccessDataScreenResDTO> pushInfoSuccessData(Timestamp startTime, Timestamp endTime);

    /**
     * 信息推送失败统计
     * @param startTime
     * @param endTime
     * @return
     */
    List<PushFailedStatisticsScreenResDTO> pushInfoFailedStatistics(Timestamp startTime, Timestamp endTime);

}
