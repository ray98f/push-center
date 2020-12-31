package com.zte.msg.pushcenter.core.script;

import java.util.Map;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/31 9:46
 */
public interface Script {

    void parse(String phoneNum, String templateNum, Map<String, String> params);

    void execute();
}
