package com.zte.msg.pushcenter.pccore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zte.msg.pushcenter.pccore.dto.req.StatisticsReqDTO;
import com.zte.msg.pushcenter.pccore.entity.EarlyWarnConfig;
import com.zte.msg.pushcenter.pccore.entity.EarlyWarnInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author frp
 */
@Mapper
@Repository
public interface EarlyWarnMapper extends BaseMapper<EarlyWarnConfig> {

    int editEarlyWarnConfig(EarlyWarnConfig earlyWarnConfig);

    List<EarlyWarnInfo> listEarlyWarnInfo(StatisticsReqDTO statisticsReqDTO);

    EarlyWarnConfig selectEarlyWarnConfig();
}
