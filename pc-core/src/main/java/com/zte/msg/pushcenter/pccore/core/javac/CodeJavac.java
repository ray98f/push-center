package com.zte.msg.pushcenter.pccore.core.javac;

import com.zte.msg.pushcenter.pccore.mapper.ProviderMapper;
import com.zte.msg.pushcenter.pccore.model.ScriptModel;
import com.zte.msg.pushcenter.pccore.utils.JavaCodecUtils;
import com.zte.msg.pushcenter.pccore.utils.PathUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
    private ProviderMapper providerMapper;

    public static Map<String, byte[]> allBuffers;

    private PcClassLoader pcClassLoader;
    private JavaCompiler javaCompiler;
    private ScriptFileManager scriptFileManager;
    private StringWriter errorStringWriter;
    private List<String> options;

    @PostConstruct
    public void init() {
        List<ScriptModel> scripts = providerMapper.selectScripts();
        String projectPath = PathUtil.getAppHomePath();
        String classPath = String.format("%s/pc-script/target/pc-script-1.0.0-jar-with-dependencies.jar", projectPath);
        options = new ArrayList<>();
        options.add("-classpath");
        options.add(classPath);
        javaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager standardJavaFileManager = javaCompiler.getStandardFileManager(null, null, null);
        scriptFileManager = new ScriptFileManager(standardJavaFileManager);
        errorStringWriter = new StringWriter();
        scriptFlush(scripts);
    }

    public void scriptFlush(List<ScriptModel> scripts) {
        scriptFlush(scripts, false);
    }

    public void scriptFlush(ScriptModel o, boolean remove) {
        if (StringUtils.isAnyBlank(o.getScriptTag(), o.getScriptContext())) {
            return;
        }
        if (!remove) {
            Iterable<? extends JavaFileObject> compilationUnits = new ArrayList<JavaFileObject>() {{
                add(new JavaSourceFromString(o.getScriptTag(), JavaCodecUtils.replaceCodeJavaName(o.getScriptContext(), o.getScriptTag())));
            }};
            getTask(compilationUnits);
        } else {
            scriptFileManager.remove(o.getScriptTag());
        }
        allBuffers = scriptFileManager.getAllBuffers();
    }

    private void getTask(Iterable<? extends JavaFileObject> compilationUnits) {
        boolean ok = javaCompiler.getTask(errorStringWriter, scriptFileManager, diagnostic -> {
            if (diagnostic.getKind() == Diagnostic.Kind.ERROR) {
                errorStringWriter.append(diagnostic.toString());
            }
        }, options, null, compilationUnits).call();
        if (!ok) {
            String errorMessage = errorStringWriter.toString();
            log.error("Compile Error:{}" + errorMessage);
        }
    }

    public void scriptFlush(List<ScriptModel> scripts, boolean remove) {
        if (!remove) {
            Iterable<? extends JavaFileObject> compilationUnits = new ArrayList<JavaFileObject>() {{
                scripts.forEach(o -> {
                    if (!StringUtils.isAnyBlank(o.getScriptContext(), o.getScriptTag())) {
                        add(new JavaSourceFromString(o.getScriptTag(),
                                JavaCodecUtils.replaceCodeJavaName(o.getScriptContext(), o.getScriptTag())));
                    }
                });
            }};
            getTask(compilationUnits);
        } else {
            scripts.forEach(o -> scriptFileManager.remove(o.getScriptTag()));
        }
    }

    public void scriptFlush(ScriptModel o) {
        scriptFlush(o, false);
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
