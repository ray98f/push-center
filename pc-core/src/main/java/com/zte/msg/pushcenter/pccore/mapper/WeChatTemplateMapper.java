package com.zte.msg.pushcenter.pccore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zte.msg.pushcenter.pccore.entity.WeChatTemplate;
import com.zte.msg.pushcenter.pccore.model.WxConfigModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/14 10:36
 */
@Mapper
public interface WeChatTemplateMapper extends BaseMapper<WeChatTemplate> {

    @Select("")
    List<WxConfigModel> selectWxConfigs(@Param("ids") List<Long> ids);
}
