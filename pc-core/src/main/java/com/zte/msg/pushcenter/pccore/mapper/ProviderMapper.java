package com.zte.msg.pushcenter.pccore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zte.msg.pushcenter.pccore.dto.res.ProviderResDTO;
import com.zte.msg.pushcenter.pccore.entity.Provider;
import com.zte.msg.pushcenter.pccore.model.ScriptModel;
import com.zte.msg.pushcenter.pccore.model.SmsConfigDetailModel;
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
            " provider  " +
            "WHERE " +
            " flag = 0  " +
            " AND ISNULL( script_tag ) = 0  " +
            " AND LENGTH( trim( script_tag ) ) > 0  " +
            " AND ISNULL( script_context ) = 0  " +
            " AND LENGTH( trim( script_context ) ) >0")
    List<ScriptModel> selectScripts();

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


}
