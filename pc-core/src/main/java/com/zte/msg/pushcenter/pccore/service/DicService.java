package com.zte.msg.pushcenter.pccore.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zte.msg.pushcenter.pccore.dto.req.DicReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.DicUpdateReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.DicResDTO;
import com.zte.msg.pushcenter.pccore.entity.Dic;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/19 9:54
 */
public interface DicService extends IService<Dic> {

    Page<DicResDTO> getDics(Page<DicResDTO> page,
                            String name,
                            String type,
                            Integer isEnable);

    void addDic(DicReqDTO reqDTO);

    void updateDic(DicUpdateReqDTO reqDTO);

    void deleteDic(Long[] ids);


}
