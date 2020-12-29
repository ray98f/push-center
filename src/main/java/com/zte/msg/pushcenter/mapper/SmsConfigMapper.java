package com.zte.msg.pushcenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zte.msg.pushcenter.dto.res.SmsConfigDetailResDTO;
import com.zte.msg.pushcenter.dto.res.SmsConfigResDTO;
import com.zte.msg.pushcenter.entity.SmsConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
            " s.config_name, " +
            " s.description, " +
            " config_id, " +
            " app_id, " +
            " secret_id, " +
            " secret_key, " +
            " c.provider_name " +
            "FROM " +
            " sms_config s " +
            " LEFT JOIN config c ON s.config_id = c.id  " +
            "WHERE " +
            " s.flag = 0")
    Page<SmsConfigDetailResDTO> selectByPage(Page<SmsConfigResDTO> page);

    @Select("SELECT " +
            " s.id, " +
            " s.config_name, " +
            " s.description, " +
            " s.config_id, " +
            " app_id, " +
            " secret_id, " +
            " secret_key, " +
            " c.provider_name " +
            "FROM " +
            " sms_config s " +
            " LEFT JOIN config c ON s.config_id = c.id  " +
            "WHERE " +
            " s.id = #{id}" +
            " AND s.flag = 0")
    SmsConfigDetailResDTO selectDetailById(@Param("id") Long id);
}
