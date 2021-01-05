package com.zte.msg.pushcenter.pccore.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zte.msg.pushcenter.pccore.utils.Constants;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/14 16:54
 */
@Configuration
public class MetaObjectHandlerConfig implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        //默认未删除
        setFieldValByName("flag", Constants.DATA_NOT_DELETED, metaObject);
        //创建时间默认当前时间
        setFieldValByName("createdAt", Instant.now(), metaObject);
        //更新时间默认当前时间
        setFieldValByName("updatedAt", Instant.now(), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {

        setFieldValByName("updatedAt", Instant.now(), metaObject);
    }

}
