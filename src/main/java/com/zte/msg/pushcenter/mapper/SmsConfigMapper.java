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
