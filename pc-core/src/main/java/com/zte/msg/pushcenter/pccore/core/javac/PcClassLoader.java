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

    private Hashtable<String, ClassInfo> classInfos = new Hashtable<>();

//    private String fullyName;
//
//    private byte[] data;
//
//    public PcClassLoader() {
//
//    }
//
//    public PcClassLoader(String name, byte[] data) {
//        this.fullyName = name;
//        this.data = data;
//    }

    //    @Override
//    protected Class<?> findClass(String name) {
//        Class<?> loadedClass = findLoadedClass(fullyName);
//        if (Objects.isNull(loadedClass)) {
//            return defineClass(fullyName, data, 0, data.length);
//        }
//        return loadedClass;
//    }

    @Override
    protected Class<?> findClass(String name) {
        if (!classInfos.containsKey(name)) {
            byte[] bytes = CodeJavac.allBuffers.get(name);
            Class<?> result = defineClass(name, bytes, 0, bytes.length);
            classInfos.put(name, new ClassInfo(result, System.currentTimeMillis()));
            return result;
        }
        ClassInfo loadedClassInfo = classInfos.get(name);
        return loadedClassInfo.classType;
    }
}
