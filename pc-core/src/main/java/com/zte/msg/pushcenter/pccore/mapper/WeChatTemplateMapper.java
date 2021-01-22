package com.zte.msg.pushcenter.pccore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zte.msg.pushcenter.pccore.entity.WeChatTemplate;
import com.zte.msg.pushcenter.pccore.model.WxConfigModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/14 10:36
 */
@Mapper
public interface WeChatTemplateMapper extends BaseMapper<WeChatTemplate> {

    /**
     * 查询微信模板用于配置初始化
     *
     * @return
     */
    @Select("SELECT " +
            " wt.id template_id, " +
            " wt.provider_id, " +
            " wt.provider_name, " +
            " wt.wechat_template_id, " +
            " wt.title, " +
            " wt.content, " +
            " p.script_tag, " +
            " p.script_context, " +
            " p.config  " +
            "FROM " +
            " `wechat_template` wt " +
            " LEFT JOIN provider p ON wt.provider_id = p.id  " +
            "WHERE " +
            " p.type = 4  " +
            " AND wt.status = 1")
    List<WxConfigModel> selectWxConfigsForInit();

    /**
     * 查询模板用于更新配置
     *
     * @param ids
     * @return
     */
    @Select({
            "<script>" +
                    "SELECT " +
                    " wt.id template_id, " +
                    " wt.provider_id, " +
                    " wt.provider_name, " +
                    " wt.wechat_template_id, " +
                    " wt.title, " +
                    " wt.content, " +
                    " p.script_tag, " +
                    " p.script_context, " +
                    " p.config  " +
                    "FROM " +
                    " `wechat_template` wt " +
                    " LEFT JOIN provider p ON wt.provider_id = p.id  " +
                    "WHERE " +
                    " p.type = 4  " +
                    " AND wt.status = 1 " +
                    " AND wt.id IN " +
                    "<foreach collection = 'ids' item = 'id' open='(' separator=',' close=')'>",
            " #{id} ",
            "</foreach>",
            "</script>"
    })
    List<WxConfigModel> selectSmsConfigForFlushByTemplateIds(@Param("ids") List<Long> ids);

    /**
     * 根据providerId查询微信配置模板
     *
     * @param ids
     * @return
     */
    @Select({
            "<script>",
            "SELECT " +
                    " wt.id template_id, " +
                    " wt.provider_id, " +
                    " wt.provider_name, " +
                    " wt.wechat_template_id, " +
                    " wt.title, " +
                    " wt.content, " +
                    " p.script_tag, " +
                    " p.script_context, " +
                    " p.config  " +
                    "FROM " +
                    " `wechat_template` wt " +
                    " LEFT JOIN provider p ON wt.provider_id = p.id  " +
                    "WHERE " +
                    " p.type = 4  " +
                    " AND wt.status = 1 " +
                    " AND p.id IN ",
            "<foreach collection = 'ids' item = 'id' open='(' separator=',' close=')'>",
            " #{id} ",
            "</foreach>",
            "</script>"
    })
    List<WxConfigModel> selectWxConfigForFlushByProviderIds(@Param("ids") List<Long> ids);
}
