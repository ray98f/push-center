package com.zte.msg.pushcenter.pccore.core.javac;

import com.zte.msg.pushcenter.pccore.service.ScriptService;
import com.zte.msg.pushcenter.pccore.utils.JavaCodecUtils;
import com.zte.msg.pushcenter.pccore.utils.PathUtil;
import com.zte.msg.pushcenter.pcscript.inte.AbstractScript;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.tools.*;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/31 17:35
 */
@Component
public class CodeJavac {

    @Resource
    private ScriptService scriptService;

    private ScriptFileManager scriptFileManager;

    @PostConstruct
    public void init() {

        List<com.zte.msg.pushcenter.pccore.entity.Script> scripts = scriptService.getScripts();

        Iterable<? extends JavaFileObject> compilationUnits = new ArrayList<JavaFileObject>() {{
            scripts.forEach(o -> add(new JavaSourceFromString(o.getScriptTag(), JavaCodecUtils.replaceCodeJavaName(o.getContext(), o.getScriptTag()))));
        }};
        String projectPath = PathUtil.getAppHomePath();
        System.out.println("projectPath : " + projectPath);
        String classPath = String.format("%s/pc-script/target/pc-script-1.0.0.jar", projectPath);
        List<String> options = new ArrayList<>();
        options.add("-classpath");
        options.add(classPath);
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();

        StandardJavaFileManager standardJavaFileManager = javaCompiler.getStandardFileManager(null, null, null);
        scriptFileManager = new ScriptFileManager(standardJavaFileManager);
        StringWriter errorStringWriter = new StringWriter();
        boolean ok = javaCompiler.getTask(errorStringWriter, scriptFileManager, diagnostic -> {
            if (diagnostic.getKind() == Diagnostic.Kind.ERROR) {
                errorStringWriter.append(diagnostic.toString());
            }
        }, options, null, compilationUnits).call();
        if (!ok) {
            String errorMessage = errorStringWriter.toString();
            throw new RuntimeException("Compile Error:{}" + errorMessage);
        }
    }

    public AbstractScript getScriptClass(String scriptTag) {
        final Map<String, byte[]> allBuffers = scriptFileManager.getAllBuffers();
        final byte[] catBytes = allBuffers.get(scriptTag);
        PcClassLoader pcClassLoader = new PcClassLoader(scriptTag, catBytes);
        Class<?> catClass = pcClassLoader.findClass(scriptTag);
        Object obj = null;
        try {
            obj = catClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return (AbstractScript) obj;
    }

}
