package com.zte.msg.pushcenter.pccore.model;

import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/11 9:49
 */
@Data
public class ScriptModel {

    private String scriptTag;

    private String scriptContext;

    public ScriptModel(String scriptTag, String scriptContext) {
        this.scriptTag = scriptTag;
        this.scriptContext = scriptContext;
    }
}
