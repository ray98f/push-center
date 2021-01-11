package com.zte.msg.pushcenter.pccore.core.javac;

import com.zte.msg.pushcenter.pccore.model.ScriptModel;
import com.zte.msg.pushcenter.pccore.service.ProviderService;
import com.zte.msg.pushcenter.pccore.utils.JavaCodecUtils;
import com.zte.msg.pushcenter.pccore.utils.PathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.tools.*;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/31 17:35
 */
@Component
@Slf4j
public class CodeJavac {

    @Resource
    private ProviderService providerService;

    public static Map<String, byte[]> allBuffers;

    private PcClassLoader pcClassLoader;

    private JavaCompiler javaCompiler;
    private ScriptFileManager scriptFileManager;
    private StringWriter errorStringWriter;
    private List<String> options;

    @PostConstruct
    public void init() {

        List<ScriptModel> scripts = providerService.getScripts();
        String projectPath = PathUtil.getAppHomePath();
        String classPath = String.format("%s/pc-script/target/pc-script-1.0.0-jar-with-dependencies.jar", projectPath);
        options = new ArrayList<>();
        options.add("-classpath");
        options.add(classPath);
        javaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager standardJavaFileManager = javaCompiler.getStandardFileManager(null, null, null);
        scriptFileManager = new ScriptFileManager(standardJavaFileManager);
        errorStringWriter = new StringWriter();
        scripts.forEach(this::flush);
        allBuffers = scriptFileManager.getAllBuffers();
    }

    public void flush(ScriptModel o) {

        Iterable<? extends JavaFileObject> compilationUnits = new ArrayList<JavaFileObject>() {{
            add(new JavaSourceFromString(o.getScriptTag(), JavaCodecUtils.replaceCodeJavaName(o.getScriptContext(), o.getScriptTag())));
        }};

        boolean ok = javaCompiler.getTask(errorStringWriter, scriptFileManager, diagnostic -> {
            if (diagnostic.getKind() == Diagnostic.Kind.ERROR) {
                errorStringWriter.append(diagnostic.toString());
            }
        }, options, null, compilationUnits).call();
        if (!ok) {
            String errorMessage = errorStringWriter.toString();
            log.error("Compile Error:{}" + errorMessage);
        }
        // TODO: 2021/1/11  flush
        scriptFileManager.flush();
    }

    public Class<?> getScriptClass(String scriptTag) {
        if (Objects.isNull(pcClassLoader)) {
            pcClassLoader = new PcClassLoader();
        }
        Class<?> aClass = pcClassLoader.findClass(scriptTag);
        if (Objects.isNull(aClass)) {
            log.error("ClassNotFoundException: {}", scriptTag);
        }
        return aClass;
    }

}
