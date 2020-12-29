package com.zte.msg.pushcenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zte.msg.pushcenter.entity.SmsTemplate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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

}
