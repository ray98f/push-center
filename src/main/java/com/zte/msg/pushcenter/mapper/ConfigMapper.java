package com.zte.msg.pushcenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zte.msg.pushcenter.dto.PageReqDTO;
import com.zte.msg.pushcenter.dto.res.ConfigResDTO;
import com.zte.msg.pushcenter.entity.Config;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/21 10:34
 */
@Mapper
public interface ConfigMapper extends BaseMapper<Config> {

    @Select("SELECT * FROM `config`")
    Page<ConfigResDTO> selectByPage(Page<ConfigResDTO> page);

}
