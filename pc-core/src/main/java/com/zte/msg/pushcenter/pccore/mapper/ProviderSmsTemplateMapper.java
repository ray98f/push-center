package com.zte.msg.pushcenter.pccore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zte.msg.pushcenter.pccore.dto.res.ProviderSmsTemplateResDTO;
import com.zte.msg.pushcenter.pccore.entity.ProviderSmsTemplate;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/31 15:18
 */
public interface ProviderSmsTemplateMapper extends BaseMapper<ProviderSmsTemplate> {

    @Select({"<script>" +
            "SELECT " +
            " pst.id, " +
            " pst.sms_template_id, " +
            " pst.sign, " +
            " pst.example, " +
            " pst.`order`, " +
            " p.provider_name, " +
            " pst.sms_config_id, " +
            " sc.`name` sms_config_name  " +
            "FROM " +
            " provider_sms_template pst " +
            " LEFT JOIN provider p ON p.id = pst.provider_id " +
            " LEFT JOIN sms_config sc ON pst.sms_config_id = sc.id  " +
            "WHERE " +
            " pst.flag = 0  " +
            " AND p.flag = 0  " +
            " AND sc.flag = 0 " +
            " AND pst.sms_template_id IN ",
            "<foreach collection = 'ids' item = 'id' open='(' separator=',' close=')'>",
            " #{id} ",
            "</foreach>",
            "</script>"
    })
    List<ProviderSmsTemplateResDTO> selectProviderSmsTemplateListByTemplateIds(@Param("ids") List<Long> ids);
}
