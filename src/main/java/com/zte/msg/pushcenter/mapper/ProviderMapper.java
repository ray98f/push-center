package com.zte.msg.pushcenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zte.msg.pushcenter.dto.res.ProviderResDTO;
import com.zte.msg.pushcenter.entity.Provider;
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
public interface ProviderMapper extends BaseMapper<Provider> {

    @Select("SELECT * FROM `provider` WHERE flag = 0")
    Page<ProviderResDTO> selectByPage(Page<ProviderResDTO> page);

}
