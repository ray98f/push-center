package com.zte.msg.pushcenter.pccore.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zte.msg.pushcenter.pccore.utils.Constants;
import com.zte.msg.pushcenter.pccore.utils.TokenUtil;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;

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
        setFieldValByName("createdAt", new Timestamp(System.currentTimeMillis()), metaObject);
        // TODO: 2021/1/6
        setFieldValByName("createdBy", TokenUtil.getCurrentUserName(), metaObject);

        setFieldValByName("updatedBy", TokenUtil.getCurrentUserName(), metaObject);
        //更新时间默认当前时间
        setFieldValByName("updatedAt", new Timestamp(System.currentTimeMillis()), metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // TODO: 2021/1/6
        setFieldValByName("updateBy", TokenUtil.getCurrentUserName(), metaObject);

        setFieldValByName("updatedAt", new Timestamp(System.currentTimeMillis()), metaObject);
    }

}
