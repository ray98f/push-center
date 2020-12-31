package com.zte.msg.pushcenter.core;

import java.util.Map;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/31 9:46
 */
public interface Script {

    default void smsParse(String phoneNum, String templateNum, Map<String, String> vars) {
    }

    default void mailParse() {
    }

    default void wechatParse() {
    }

    default void appParse() {
    }

    void execute();

}
