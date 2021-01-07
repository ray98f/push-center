package com.zte.msg.pushcenter.pccore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zte.msg.pushcenter.pccore.model.SmsTemplateRelationModel;
import com.zte.msg.pushcenter.pccore.entity.SmsTemplateRelation;
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
                    " st.`status` s_status, " +
                    " st.updated_by, " +
                    " st.updated_at, " +
                    " str.id relation_id, " +
                    " str.priority, " +
                    " pst.`code`, " +
                    " pst.content p_content, " +
                    " pst.`status` p_status, " +
                    " pst.id p_template_id, " +
                    " pst.sign, " +
                    " p.provider_name " +
                    "FROM " +
                    " sms_template st " +
                    " LEFT JOIN sms_template_relation str ON st.id = str.sms_template_id " +
                    " LEFT JOIN platform_sms_template pst ON pst.id = str.platform_template_id " +
                    " LEFT JOIN provider p ON p.id = pst.provider_id " +
                    "WHERE st.id IN ",
            "<foreach collection = 'ids' item = 'id' open='(' separator=',' close=')'>",
            " #{id} ",
            "</foreach>",
            "</script>"
    })
    List<SmsTemplateRelationModel> selectByTemplateIds(@Param("ids") List<Long> ids);


}
