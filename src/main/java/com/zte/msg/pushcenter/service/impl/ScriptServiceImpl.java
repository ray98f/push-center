package com.zte.msg.pushcenter.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zte.msg.pushcenter.dto.PageReqDTO;
import com.zte.msg.pushcenter.dto.req.ScriptReqDTO;
import com.zte.msg.pushcenter.dto.res.ScriptResDTO;
import com.zte.msg.pushcenter.entity.Script;
import com.zte.msg.pushcenter.enums.ErrorCode;
import com.zte.msg.pushcenter.exception.CommonException;
import com.zte.msg.pushcenter.mapper.ScriptMapper;
import com.zte.msg.pushcenter.service.ScriptService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
        getBaseMapper().insert(script);
    }

    @Override
    public Page<ScriptResDTO> getScripts(PageReqDTO pageReqDTO) {

        return getBaseMapper().selectByPage(pageReqDTO.of());
    }

    @Override
    public void relate(Long configId, Long scriptId) {
        Script script = getBaseMapper().selectById(scriptId);
        if (Objects.isNull(script)) {
            throw new CommonException(ErrorCode.RESOURCE_NOT_EXIST);
        }
        script.setScriptTag("Script" + System.currentTimeMillis() + "_" + configId);
        script.setConfigId(scriptId);
        getBaseMapper().insert(script);
    }
}
