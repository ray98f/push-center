package com.zte.msg.pushcenter.pccore.core.javac;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/11 12:40
 */
@SuppressFBWarnings("URF_UNREAD_PUBLIC_OR_PROTECTED_FIELD")
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
