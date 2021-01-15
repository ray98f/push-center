package com.zte.msg.pushcenter.pccore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zte.msg.pushcenter.pccore.dto.res.ProviderResDTO;
import com.zte.msg.pushcenter.pccore.entity.Provider;
import com.zte.msg.pushcenter.pccore.model.ScriptModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

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

    @Select("SELECT " +
            " script_tag, " +
            " script_context  " +
            "FROM " +
            " provider p " +
            "WHERE " +
            " flag = 0  " +
            " AND ISNULL( script_tag ) = 0  " +
            " AND LENGTH( trim( script_tag ) ) > 0  " +
            " AND ISNULL( script_context ) = 0  " +
            " AND LENGTH( trim( script_context ) ) >0" +
            " AND p.flag = 0 ")
    List<ScriptModel> selectScripts();


}
