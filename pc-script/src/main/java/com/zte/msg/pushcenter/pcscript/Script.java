package com.zte.msg.pushcenter.pcscript;

import java.util.Map;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/31 9:46
 */
public interface Script {

    String execute(Map<String, Object> params);

}
