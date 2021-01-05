package com.zte.msg.pushcenter.pccore.core.javac;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/31 17:35
 */
public class PcClassLoader extends ClassLoader {

    private String fullyName;

    private byte[] data;

    public PcClassLoader() {

    }

    public PcClassLoader(String name, byte[] data) {
        this.fullyName = name;
        this.data = data;
    }

    @Override
    protected Class<?> findClass(String name) {
        return defineClass(fullyName, data, 0, data.length);
    }
}
