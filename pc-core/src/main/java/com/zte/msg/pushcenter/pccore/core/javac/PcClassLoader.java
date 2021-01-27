package com.zte.msg.pushcenter.pccore.core.javac;

import lombok.extern.slf4j.Slf4j;

import java.util.Hashtable;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/31 17:35
 */
@Slf4j
public class PcClassLoader extends ClassLoader {

    private final Hashtable<String, ClassInfo> classInfos = new Hashtable<>();

    @Override
    protected Class<?> findClass(String name) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        if (!classInfos.containsKey(name)) {
            byte[] bytes = CodeJavac.allBuffers.get(name);
            Class<?> result = null;
            try {
                result = defineClass(name, bytes, 0, bytes.length);
                classInfos.put(name, new ClassInfo(result, System.currentTimeMillis()));
            } catch (Exception e) {
                try {
                    return contextClassLoader.loadClass(name);
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
            return result;
        }
        ClassInfo loadedClassInfo = classInfos.get(name);
        return loadedClassInfo.classType;
    }
}
