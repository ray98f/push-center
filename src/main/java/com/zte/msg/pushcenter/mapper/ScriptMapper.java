package com.zte.msg.pushcenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zte.msg.pushcenter.dto.res.ScriptResDTO;
import com.zte.msg.pushcenter.entity.Script;
import org.apache.ibatis.annotations.Select;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/24 16:30
 */
public interface ScriptMapper extends BaseMapper<Script> {

    @Select("SELECT * FROM `script` WHERE flag = 0")
    Page<ScriptResDTO> selectByPage(Page<ScriptResDTO> page);

}
