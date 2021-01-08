package com.zte.msg.pushcenter.pccore.config;

import com.alibaba.fastjson.JSONObject;
import com.zte.msg.pushcenter.pccore.entity.User;
import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/4/30 15:00
 */
@Data
public class RequestHeaderContext {

    private static final ThreadLocal<RequestHeaderContext> REQUEST_HEADER_CONTEXT_THREAD_LOCAL = new ThreadLocal<>();

    private User user;

    public static RequestHeaderContext getInstance() {
        return REQUEST_HEADER_CONTEXT_THREAD_LOCAL.get();
    }

    public void setContext(RequestHeaderContext context) {
        REQUEST_HEADER_CONTEXT_THREAD_LOCAL.set(context);
    }

    public static void clean() {
        REQUEST_HEADER_CONTEXT_THREAD_LOCAL.remove();
    }

    private RequestHeaderContext(RequestHeaderContextBuild requestHeaderContextBuild) {
        this.user = JSONObject.parseObject(requestHeaderContextBuild.user, User.class);
        setContext(this);
    }

    @Data
    public static class RequestHeaderContextBuild {

        private String user;

        public RequestHeaderContextBuild user(String user) {
            this.user = user;
            return this;
        }

        public RequestHeaderContext build() {
            return new RequestHeaderContext(this);
        }
    }

}
