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
            " st.template_id, " +
            " st.sms_config_id, " +
            " sc.config_id, " +
            " s.id script_id, " +
            " c.config_name, " +
            " c.provider_name, " +
            " s.context, " +
            " s.script_name, " +
            " s.script_tag, " +
            " sc.config_name sms_config_name, " +
            " sc.s_app_id, " +
            " sc.secret_id, " +
            " sc.secret_key, " +
            " st.example, " +
            " st.sign  " +
            "FROM " +
            " sms_template st " +
            " LEFT JOIN sms_config sc ON st.sms_config_id = sc.id " +
            " LEFT JOIN config c ON sc.config_id = c.id " +
            " LEFT JOIN script s ON s.config_id = c.id  " +
            "WHERE " +
            " st.flag = 0  " +
            " AND sc.flag = 0  " +
            " AND c.flag = 0  " +
            " AND s.flag = 0" +
            " AND s.related = 1")
    List<SmsPusher.ConfigDetail> selectConfigDetail();

}
