package com.zte.msg.pushcenter.pccore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zte.msg.pushcenter.pccore.dto.req.DicDataReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.DicDataUpdateReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.DicDataResDTO;
import com.zte.msg.pushcenter.pccore.entity.Dic;
import com.zte.msg.pushcenter.pccore.entity.DicData;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.mapper.DicDataMapper;
import com.zte.msg.pushcenter.pccore.mapper.DicMapper;
import com.zte.msg.pushcenter.pccore.service.DicDataService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/19 15:04
 */
@Service
public class DicDataServiceImpl extends ServiceImpl<DicDataMapper, DicData> implements DicDataService {

    public static final String TYPE = "type";
    @Resource
    private DicMapper dicMapper;

    @Override
    public Page<DicDataResDTO> getDicDataByPage(Page<DicDataResDTO> page, String type, String value, Integer isEnable) {
        return getBaseMapper().selectDicDataByPage(page, type, value, isEnable);
    }

    @Override
    public void addDicData(DicDataReqDTO reqDTO) {

        if (dicMapper.selectCount(new QueryWrapper<Dic>().eq(TYPE, reqDTO.getType())) <= 0) {
            throw new CommonException(ErrorCode.DIC_TYPE_NOT_EXIST);
        }
        DicData dicData = new DicData();
        BeanUtils.copyProperties(reqDTO, dicData);
        getBaseMapper().insert(dicData);

    }

    @Override
    public void updateDicData(DicDataUpdateReqDTO reqDTO) {

        if (dicMapper.selectCount(new QueryWrapper<Dic>().eq(TYPE, reqDTO.getType())) <= 0) {
            throw new CommonException(ErrorCode.DIC_TYPE_NOT_EXIST);
        }
        DicData dicData = new DicData();
        BeanUtils.copyProperties(reqDTO, dicData);
        getBaseMapper().updateById(dicData);
    }

    @Override
    public void deleteDicData(Long[] ids) {
        getBaseMapper().deleteBatchIds(Arrays.asList(ids));
    }
}
