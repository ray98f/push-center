package com.zte.msg.pushcenter.pccore.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zte.msg.pushcenter.pccore.dto.req.DicDataReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.DicDataUpdateReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.DicDataResDTO;
import com.zte.msg.pushcenter.pccore.entity.DicData;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/19 15:04
 */
public interface DicDataService extends IService<DicData> {

    Page<DicDataResDTO> getDicDataByPage(Page<DicDataResDTO> page,
                                         String type,
                                         String value,
                                         Integer isEnable);

    void addDicData(DicDataReqDTO reqDTO);

    void updateDicData(DicDataUpdateReqDTO reqDTO);

    void deleteDicData(Long[] ids);
}
