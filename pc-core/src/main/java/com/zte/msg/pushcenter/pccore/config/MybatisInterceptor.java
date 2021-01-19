package com.zte.msg.pushcenter.pccore.config;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Properties;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/19 11:01
 */
@Component
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class MybatisInterceptor implements Interceptor {

    @Value("${mybatis-plus.global-config.db-config.logic-delete-field}")
    private String logicDeleteField;

    private static final String WHERE = "where";

    private static final String LIMIT = "limit";

    private static final String GROUP_BY = "group by";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        MetaObject metaObject = MetaObject
                .forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY,
                        SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,
                        new DefaultReflectorFactory());
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");
        if (SqlCommandType.SELECT == mappedStatement.getSqlCommandType()) {
            BoundSql boundSql = statementHandler.getBoundSql();
            String sql = boundSql.getSql().toLowerCase();
            StringBuilder sqlBuilder = new StringBuilder(sql);
            boolean where = sql.contains(WHERE);
            boolean limit = sql.contains(LIMIT);
            boolean group = sql.contains(GROUP_BY);
            boolean deleted = sql.contains(logicDeleteField);
            if (where && !deleted) {
                sqlBuilder.insert(sqlBuilder.indexOf(WHERE) + 6, logicDeleteField + " = 0 and ");
            }
            if (!where && !limit && !group) {
                sqlBuilder.append(" ").append(WHERE).append(" ").append(logicDeleteField).append(" = 0 ");
            }
            if (!where && group) {
                sqlBuilder.insert(sqlBuilder.indexOf(GROUP_BY), WHERE + " " + logicDeleteField + " = 0 ");
            }
            if (!where && limit && !group) {
                sqlBuilder.insert(sqlBuilder.indexOf(LIMIT), WHERE + " " + logicDeleteField + " = 0 ");
            }
            Field field = boundSql.getClass().getDeclaredField("sql");
            field.setAccessible(true);
            field.set(boundSql, sqlBuilder.toString());
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }


}
