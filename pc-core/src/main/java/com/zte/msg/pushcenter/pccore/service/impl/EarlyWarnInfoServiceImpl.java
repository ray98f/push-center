package com.zte.msg.pushcenter.pccore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zte.msg.pushcenter.pccore.dto.PageReqDTO;
import com.zte.msg.pushcenter.pccore.entity.EarlyWarnInfo;
import com.zte.msg.pushcenter.pccore.mapper.EarlyWarnInfoMapper;
import com.zte.msg.pushcenter.pccore.service.EarlyWarnInfoService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.Objects;

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
        LambdaQueryWrapper<EarlyWarnInfo> wrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(startTime) && Objects.nonNull(endTime)) {
            wrapper.between(EarlyWarnInfo::getTime, startTime, endTime);
        }
        wrapper.orderByDesc(EarlyWarnInfo::getUpdatedAt);
        Page<EarlyWarnInfo> earlyWarnInfoPage = getBaseMapper().selectPage(pageReqDTO.of(), wrapper);
        earlyWarnInfoPage.getRecords().sort(Comparator.comparing(EarlyWarnInfo::getUpdatedAt).reversed());
        return earlyWarnInfoPage;
    }
}
