package com.zte.msg.pushcenter.pccore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zte.msg.pushcenter.pccore.dto.PageReqDTO;
import com.zte.msg.pushcenter.pccore.entity.EarlyWarnInfo;
import com.zte.msg.pushcenter.pccore.mapper.EarlyWarnInfoMapper;
import com.zte.msg.pushcenter.pccore.service.EarlyWarnInfoService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/20 16:52
 */
@Service
public class EarlyWarnInfoServiceImpl extends ServiceImpl<EarlyWarnInfoMapper, EarlyWarnInfo> implements EarlyWarnInfoService {
    @Override
    public Page<EarlyWarnInfo> getWarnInfoByPage(PageReqDTO pageReqDTO, Timestamp startTime, Timestamp endTime) {

        return getBaseMapper().selectPage(pageReqDTO.of(),
                new QueryWrapper<EarlyWarnInfo>().between("time", startTime, endTime));
    }
}
