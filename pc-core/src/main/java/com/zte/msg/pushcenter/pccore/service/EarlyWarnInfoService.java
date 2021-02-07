package com.zte.msg.pushcenter.pccore.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zte.msg.pushcenter.pccore.dto.PageReqDTO;
import com.zte.msg.pushcenter.pccore.entity.EarlyWarnInfo;

import java.sql.Timestamp;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/20 16:52
 */
public interface EarlyWarnInfoService extends IService<EarlyWarnInfo> {

    /**
     * 分页查询预警记录
     *
     * @param pageReqDTO
     * @param startTime
     * @param endTime
     * @return
     */
    Page<EarlyWarnInfo> getWarnInfoByPage(PageReqDTO pageReqDTO, Timestamp startTime, Timestamp endTime);

    /**
     * 处理预警信息
     *
     * @param id
     */
    void handleWarnInfo(Long id);

}
