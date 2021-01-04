package com.zte.msg.pushcenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zte.msg.pushcenter.core.pusher.SmsPusher;
import com.zte.msg.pushcenter.dto.res.SmsConfigDetailResDTO;
import com.zte.msg.pushcenter.dto.res.SmsConfigResDTO;
import com.zte.msg.pushcenter.entity.SmsConfig;
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
public interface SmsConfigMapper extends BaseMapper<SmsConfig> {

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
            " t.type, " +
            " t.id, " +
            " t.`name`, " +
            " pst.example, " +
            " pst.sign, " +
            " pst.`order`, " +
            " sc.s_app_id, " +
            " sc.secret_id, " +
            " sc.secret_key, " +
            " p.provider_name " +
            "FROM " +
            " template t " +
            " LEFT JOIN provider_sms_template pst ON t.id = pst.sms_template_id " +
            " LEFT JOIN sms_config sc ON sc.id = pst.sms_config_id " +
            " LEFT JOIN provider p ON pst.provider_id = p.id " +
            "WHERE t.type = #{type}" +
            " AND t.flag = 0 "
    )
    List<SmsPusher.ConfigDetail> selectConfigDetail(@Param("type") Integer type);

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
