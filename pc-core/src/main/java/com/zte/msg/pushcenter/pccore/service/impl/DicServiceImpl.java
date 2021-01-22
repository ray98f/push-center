package com.zte.msg.pushcenter.pccore.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zte.msg.pushcenter.pccore.dto.req.DicReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.DicUpdateReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.DicResDTO;
import com.zte.msg.pushcenter.pccore.entity.Dic;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.mapper.DicMapper;
import com.zte.msg.pushcenter.pccore.service.DicService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/19 9:54
 */
@Service
public class DicServiceImpl extends ServiceImpl<DicMapper, Dic> implements DicService {

    public static final String TYPE = "type";

    @Override
    public Page<DicResDTO> getDics(Page<DicResDTO> page, String name, String type, Integer isEnable) {
        return getBaseMapper().selectDicByPage(page, name, type, isEnable);
    }

    @Override
    public void addDic(DicReqDTO reqDTO) {
        if (getBaseMapper().selectCount(new QueryWrapper<Dic>().eq(TYPE, reqDTO.getType())) > 0) {
            throw new CommonException(ErrorCode.DIC_TYPE_ALREADY_EXIST, reqDTO.getType());
        }
        Dic dic = new Dic();
        BeanUtils.copyProperties(reqDTO, dic);
        getBaseMapper().insert(dic);
    }

    @Override
    public void updateDic(DicUpdateReqDTO reqDTO) {
        Dic dic1 = getBaseMapper().selectOne(new QueryWrapper<Dic>().eq(TYPE, reqDTO.getType()));
        if (Objects.nonNull(dic1) && !dic1.getId().equals(reqDTO.getId())) {
            throw new CommonException(ErrorCode.DIC_TYPE_ALREADY_EXIST, reqDTO.getType());
        }
        Dic dic = new Dic();
        BeanUtils.copyProperties(reqDTO, dic);
        getBaseMapper().updateById(dic);
    }

    @Override
    public void deleteDic(Long[] ids) {
        getBaseMapper().deleteBatchIds(Arrays.asList(ids));
    }

}
