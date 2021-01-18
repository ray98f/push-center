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
            " AND p.flag = 0  " +
            " AND wt.flag = 0 " +
            " AND wt.status = 1")
    List<WxConfigModel> selectWxConfigsForInit();

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
                    " AND p.flag = 0  " +
                    " AND wt.flag = 0 " +
                    " AND wt.status = 1 " +
                    " AND wt.id IN " +
                    "<foreach collection = 'ids' item = 'id' open='(' separator=',' close=')'>",
            " #{id} ",
            "</foreach>",
            "</script>"
    })
    List<WxConfigModel> selectSmsConfigForFlushByTemplateIds(@Param("ids") List<Long> ids);

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
                    " AND p.flag = 0  " +
                    " AND wt.flag = 0 " +
                    " AND wt.status = 1 " +
                    " AND p.id IN ",
            "<foreach collection = 'ids' item = 'id' open='(' separator=',' close=')'>",
            " #{id} ",
            "</foreach>",
            "</script>"
    })
    List<WxConfigModel> selectWxConfigForFlushByProviderIds(@Param("ids") List<Long> ids);
}
