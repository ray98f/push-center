package com.zte.msg.pushcenter.pccore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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

    /**
     * 查询可用脚本列表
     *
     * @return
     */
    @Select("SELECT " +
            " script_tag, " +
            " script_context  " +
            "FROM " +
            " provider p " +
            "WHERE " +
            " ISNULL( script_tag ) = 0  " +
            " AND LENGTH( trim( script_tag ) ) > 0  " +
            " AND ISNULL( script_context ) = 0  " +
            " AND LENGTH( trim( script_context ) ) >0")
    List<ScriptModel> selectScripts();


}
