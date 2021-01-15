package com.zte.msg.pushcenter.pccore.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zte.msg.pushcenter.pccore.dto.req.StatisticsReqDTO;
import com.zte.msg.pushcenter.pccore.entity.EarlyWarnConfig;
import com.zte.msg.pushcenter.pccore.entity.EarlyWarnInfo;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.mapper.EarlyWarnMapper;
import com.zte.msg.pushcenter.pccore.service.EarlyWarnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2020/12/28 15:42
 */
@Service
@Slf4j
public class EarlyWarnServiceImpl implements EarlyWarnService {

    @Autowired
    private EarlyWarnMapper earlyWarnMapper;

    @Override
    public void editEarlyWarnConfig(EarlyWarnConfig earlyWarnConfig) {
        if (Objects.isNull(earlyWarnConfig)) {
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        int result = earlyWarnMapper.editEarlyWarnConfig(earlyWarnConfig);
        if (result > 0) {
            log.info("预警配置编辑成功");
        } else {
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }
    }

    @Override
    public PageInfo<EarlyWarnInfo> listEarlyWarnInfo(StatisticsReqDTO statisticsReqDTO) {
        if (null == statisticsReqDTO.getPage() || null == statisticsReqDTO.getSize()) {
            throw new CommonException(ErrorCode.PAGE_PARAM_EMPTY);
        }
        if (0 >= statisticsReqDTO.getPage() || 0 >= statisticsReqDTO.getSize()) {
            throw new CommonException(ErrorCode.PAGE_PARAM_ERROR);
        }
        PageHelper.startPage(statisticsReqDTO.getPage().intValue(), statisticsReqDTO.getSize().intValue());
        List<EarlyWarnInfo> list = earlyWarnMapper.listEarlyWarnInfo(statisticsReqDTO);
        return new PageInfo<>(list);
    }

    @Override
    public EarlyWarnConfig selectEarlyWarnConfig(){
        EarlyWarnConfig earlyWarnConfig = earlyWarnMapper.selectEarlyWarnConfig();
        if (Objects.isNull(earlyWarnConfig)){
            log.warn("预警配置不存在，请先创建");
        }
        log.info("查看预警配置成功");
        return earlyWarnConfig;
    }
}
