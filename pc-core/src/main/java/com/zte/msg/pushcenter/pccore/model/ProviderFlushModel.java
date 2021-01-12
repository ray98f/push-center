package com.zte.msg.pushcenter.pccore.model;

import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/12 14:25
 */
@Data
public class ProviderFlushModel {

    private String scriptTag;

    private String scriptContext;

    private String config;

    public ProviderFlushModel(String scriptTag, String scriptContext, String config) {
        this.config = config;
        this.scriptTag = scriptTag;
        this.scriptContext = scriptContext;
    }
}
