package com.zte.msg.pushcenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zte.msg.pushcenter.dto.PageReqDTO;
import com.zte.msg.pushcenter.dto.req.ScriptReqDTO;
import com.zte.msg.pushcenter.dto.res.ScriptResDTO;
import com.zte.msg.pushcenter.entity.Script;
import com.zte.msg.pushcenter.mapper.ScriptMapper;
import com.zte.msg.pushcenter.service.ScriptService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/24 16:28
 */
@Service
public class ScriptServiceImpl extends ServiceImpl<ScriptMapper, Script> implements ScriptService {

    @Override
    public void addScript(ScriptReqDTO reqDTO) {
        Script script = new Script();
        BeanUtils.copyProperties(reqDTO, script);
        script.setScriptTag("Script" + System.currentTimeMillis() + "_" + reqDTO.getConfigId());
        getBaseMapper().insert(script);
    }

    @Override
    public Page<ScriptResDTO> getScriptByPage(PageReqDTO pageReqDTO) {
//        getBaseMapper().selectPage(pageReqDTO, new QueryWrapper<>());
        return null;
    }
}
