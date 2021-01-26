package com.zte.msg.pushcenter.pccore.core.javac;

import lombok.extern.slf4j.Slf4j;

import java.util.Hashtable;
import java.util.Objects;

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
        log.info("findClass name : {}" ,name);
        if (!classInfos.containsKey(name)) {
            byte[] bytes = CodeJavac.allBuffers.get(name);
            if (Objects.isNull(bytes)) {
                bytes = CodeJavac.allBuffers.get("com.zte.msg.pushcenter.pccore.core.script." + name);
            }
            Class<?> result = null;
            try {
                log.info("findClass before defined name : {}" ,name);
                result = defineClass(name, bytes, 0, bytes.length);
                classInfos.put(name, new ClassInfo(result, System.currentTimeMillis()));
            } catch (Exception e) {
                log.error("error when take script :{}, message :{}", name, e.getMessage());
            }
            return result;
        }
        ClassInfo loadedClassInfo = classInfos.get(name);
        return loadedClassInfo.classType;
    }
}
