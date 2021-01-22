package com.zte.msg.pushcenter.pccore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zte.msg.pushcenter.pccore.entity.SmsTemplateRelation;
import com.zte.msg.pushcenter.pccore.model.SmsConfigModel;
import com.zte.msg.pushcenter.pccore.model.SmsTemplateRelationModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/6 15:02
 */
public interface SmsTemplateRelationMapper extends BaseMapper<SmsTemplateRelation> {

    /**
     * 根据templateId列表查询关联的所有第三方模版
     *
     * @return
     */
    @Select({
            "<script>",
            "SELECT " +
                    " st.id, " +
                    " st.content, " +
                    " st.params, " +
                    " st.is_enable s_status, " +
                    " st.updated_by, " +
                    " st.updated_at, " +
                    " str.id relation_id, " +
                    " str.priority, " +
                    " pst.`code`, " +
                    " pst.content p_content, " +
                    " pst.is_enable p_status, " +
                    " pst.id p_template_id, " +
                    " pst.sign, " +
                    " p.provider_name " +
                    "FROM " +
                    " sms_template st " +
                    " LEFT JOIN sms_template_relation str ON st.id = str.sms_template_id " +
                    " LEFT JOIN provider_sms_template pst ON pst.id = str.provider_template_id " +
                    " LEFT JOIN provider p ON p.id = pst.provider_id " +
                    "WHERE st.id IN ",
            "<foreach collection = 'ids' item = 'id' open='(' separator=',' close=')'>",
            " #{id} ",
            "</foreach>",
            "</script>"
    })
    List<SmsTemplateRelationModel> selectByTemplateIds(@Param("ids") List<Long> ids);


    @Select("SELECT " +
            " st.id, " +
            " st.content, " +
            " st.params, " +
            " st.is_enable s_status, " +
            " st.updated_by, " +
            " st.updated_at, " +
            " str.id relation_id, " +
            " str.priority, " +
            " pst.`code`, " +
            " pst.content p_content, " +
            " pst.is_enable p_status, " +
            " pst.id p_template_id, " +
            " pst.sign, " +
            " p.provider_name  " +
            "FROM " +
            " provider_sms_template pst " +
            " LEFT JOIN sms_template_relation str ON pst.id = str.provider_template_id " +
            " LEFT JOIN sms_template st ON st.id = str.sms_template_id " +
            " LEFT JOIN provider p ON p.id = pst.provider_id  " +
            "WHERE " +
            " pst.is_deleted = 0  " +
            " AND str.is_deleted = 0  " +
            " AND st.is_deleted = 0  " +
            " AND p.is_deleted = 0 " +
            " And st.id = #{id}")
    List<SmsTemplateRelationModel> selectByTemplateId(@Param("id") Long id);

    @Select("SELECT " +
            " str.sms_template_id, " +
            " str.provider_template_id, " +
            " str.priority, " +
            " st.is_enable template_status, " +
            " pst.`code`, " +
            " pst.sign, " +
            " pst.content, " +
            " pst.is_enable provider_template_status, " +
            " p.provider_name, " +
            " p.script_tag, " +
            " p.config  " +
            "FROM " +
            " sms_template_relation str " +
            " LEFT JOIN sms_template st ON str.sms_template_id = st.id " +
            " LEFT JOIN provider_sms_template pst ON str.provider_template_id = pst.id " +
            " LEFT JOIN provider p ON pst.provider_id = p.id  " +
            "WHERE " +
            " p.type = 1  " +
            " AND pst.is_enable = 1" +
            " AND st.is_enable = 1 "
    )
    List<SmsConfigModel> selectAllSmsConfigForInit();

    @Select({
            "<script>",
            "SELECT " +
                    " str.sms_template_id, " +
                    " str.provider_template_id, " +
                    " str.priority, " +
                    " st.is_enable template_status, " +
                    " pst.`code`, " +
                    " pst.sign, " +
                    " pst.content, " +
                    " pst.is_enable provider_template_status, " +
                    " p.provider_name, " +
                    " p.script_tag, " +
                    " p.config  " +
                    "FROM " +
                    " sms_template_relation str " +
                    " LEFT JOIN sms_template st ON str.sms_template_id = st.id " +
                    " LEFT JOIN provider_sms_template pst ON str.provider_template_id = pst.id " +
                    " LEFT JOIN provider p ON pst.provider_id = p.id  " +
                    "WHERE " +
                    " p.type = 1  " +
                    " AND pst.is_enable = 1" +
                    " AND st.is_enable = 1 " +
                    " AND st.id IN " +
                    "<foreach collection = 'ids' item = 'id' open='(' separator=',' close=')'>",
            " #{id} ",
            "</foreach>",
            "</script>"
    })
    List<SmsConfigModel> selectSmsConfigForFlush(@Param("ids") List<Long> templateIds);

    @Select({
            "<script>",
            "SELECT " +
                    " str.sms_template_id, " +
                    " str.provider_template_id, " +
                    " str.priority, " +
                    " st.is_enable template_status, " +
                    " pst.`code`, " +
                    " pst.sign, " +
                    " pst.content, " +
                    " pst.is_enable provider_template_status, " +
                    " p.provider_name, " +
                    " p.script_tag, " +
                    " p.config  " +
                    "FROM " +
                    " sms_template_relation str " +
                    " LEFT JOIN sms_template st ON str.sms_template_id = st.id " +
                    " LEFT JOIN provider_sms_template pst ON str.provider_template_id = pst.id " +
                    " LEFT JOIN provider p ON pst.provider_id = p.id  " +
                    "WHERE " +
                    " p.type = 1  " +
                    " AND str.is_deleted = 0  " +
                    " AND st.is_deleted = 0  " +
                    " AND pst.is_deleted = 0  " +
                    " AND p.is_deleted = 0 " +
                    " AND pst.is_enable = 1" +
                    " AND st.is_enable = 1 " +
                    " AND p.id IN " +
                    "<foreach collection = 'ids' item = 'id' open='(' separator=',' close=')'>",
            " #{id} ",
            "</foreach>",
            "</script>"
    })
    List<SmsConfigModel> selectSmsConfigForFlushByProviderIds(@Param("ids") List<Long> ids);

}
