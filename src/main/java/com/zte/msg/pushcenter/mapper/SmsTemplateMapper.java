package com.zte.msg.pushcenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zte.msg.pushcenter.core.pusher.SmsPusher;
import com.zte.msg.pushcenter.entity.SmsTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/21 13:53
 */
@Mapper
public interface SmsTemplateMapper extends BaseMapper<SmsTemplate> {

    @Select("SELECT * FROM sms_template WHERE flag = 0")
    Page<SmsTemplate> selectByPage(Page<SmsTemplate> page);

    @Select("SELECT " +
            " st.id, " +
            " st.`name`, " +
            " pst.example, " +
            " pst.sign, " +
            " pst.`order`, " +
            " sc.s_app_id, " +
            " sc.secret_id, " +
            " sc.secret_key, " +
            " p.provider_name " +
            "FROM " +
            " sms_template st " +
            " LEFT JOIN provider_sms_template pst ON st.id = pst.sms_template_id " +
            " LEFT JOIN sms_config sc ON sc.id = pst.sms_config_id " +
            " LEFT JOIN provider p ON pst.provider_id = p.id")
    List<SmsPusher.ConfigDetail> selectConfigDetail();

}
