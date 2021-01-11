package com.zte.msg.pushcenter.pccore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zte.msg.pushcenter.pccore.dto.res.SmsConfigDetailResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.SmsConfigResDTO;
import com.zte.msg.pushcenter.pccore.entity.SmsConfig;
import com.zte.msg.pushcenter.pccore.model.SmsConfigDetailModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/22 14:38
 */
@Mapper
public interface SmsMapper extends BaseMapper<SmsConfig> {

    @Select("SELECT " +
            " s.id, " +
            " s.name, " +
            " s.description, " +
            " provider_id, " +
            " s_app_id, " +
            " secret_id, " +
            " secret_key, " +
            " c.provider_name " +
            "FROM " +
            " sms_config s " +
            " LEFT JOIN provider c ON s.provider_id = c.id  " +
            "WHERE " +
            " s.flag = 0")
    Page<SmsConfigDetailResDTO> selectByPage(Page<SmsConfigResDTO> page);

    @Select("SELECT " +
            " str.sms_template_id, " +
            " str.platform_template_id, " +
            " str.priority, " +
            " st.`status` template_status, " +
            " pst.`code`, " +
            " pst.sign, " +
            " pst.content, " +
            " pst.`status` platform_template_status, " +
            " p.provider_name, " +
            " p.type, " +
            " p.script_tag, " +
            " p.config  " +
            "FROM " +
            " sms_template_relation str " +
            " LEFT JOIN sms_template st ON str.sms_template_id = st.id " +
            " LEFT JOIN platform_sms_template pst ON str.platform_template_id = pst.id " +
            " LEFT JOIN provider p ON pst.provider_id = p.id  " +
            "WHERE " +
            " p.type = 1  " +
            " AND str.flag = 0  " +
            " AND st.flag = 0  " +
            " AND pst.flag = 0  " +
            " AND p.flag = 0 "
    )
    List<SmsConfigDetailModel> selectAllSmsConfigForInit();

    @Select("SELECT " +
            " s.id, " +
            " s.name, " +
            " s.description, " +
            " s.provider_id, " +
            " s_app_id, " +
            " secret_id, " +
            " secret_key, " +
            " c.provider_name " +
            "FROM " +
            " sms_config s " +
            " LEFT JOIN provider c ON s.provider_id = c.id  " +
            "WHERE " +
            " s.id = #{id}" +
            " AND s.flag = 0")
    SmsConfigDetailResDTO selectDetailById(@Param("id") Long id);

}
