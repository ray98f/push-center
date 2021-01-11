package com.zte.msg.pushcenter.pccore.core.javac;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/11 12:40
 */
public class ClassInfo {
    public Class<?> classType;

    public long lastModified;

    public ClassInfo(Class<?> classType) {
        this.classType = classType;
        this.lastModified = -1;
    }

    public ClassInfo(Class<?> classType, long lastModified) {
        this.classType = classType;
        this.lastModified = lastModified;
    }
}
