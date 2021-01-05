package com.zte.msg.pushcenter.core.javac;

import com.zte.msg.pushcenter.core.Script;

import javax.tools.*;
import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/31 17:35
 */
public class CodeJavac {

    public static String getTencentCode() {
        return "import com.alibaba.fusu.facade.SmsScript;\n" +
                "\n" +
                "import java.util.Map;\n" +
                "import com.tencentcloudapi.common.Credential;\n" +
                "import com.tencentcloudapi.common.exception.TencentCloudSDKException;\n" +
                "import com.tencentcloudapi.common.profile.ClientProfile;\n" +
                "import com.tencentcloudapi.common.profile.HttpProfile;\n" +
                "import com.tencentcloudapi.sms.v20190711.SmsClient;\n" +
                "import com.tencentcloudapi.sms.v20190711.models.SendSmsRequest;\n" +
                "import com.tencentcloudapi.sms.v20190711.models.SendSmsResponse;\n" +
                "\n" +
                "/**\n" +
                " * description:\n" +
                " *\n" +
                " * @author chentong\n" +
                " * @version 1.0\n" +
                " * @date 2020/12/25 14:19\n" +
                " */\n" +
                "public class TencentDemo implements SmsScript {\n" +
                "    @Override\n" +
                "    public void parse(String phoneNum, String templateNum, Map<String, String> params) {\n" +
                "\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void execute() {\n" +
                "        try {\n" +
                "            /* 必要步骤：\n" +
                "             * 实例化一个认证对象，入参需要传入腾讯云账户密钥对 secretId 和 secretKey\n" +
                "             * 本示例采用从环境变量读取的方式，需要预先在环境变量中设置这两个值\n" +
                "             * 您也可以直接在代码中写入密钥对，但需谨防泄露，不要将代码复制、上传或者分享给他人\n" +
                "             * CAM 密钥查询：https://console.cloud.tencent.com/cam/capi\n" +
                "             */\n" +
                "            Credential cred = new Credential(\"AKIDyid2EePjPs75ljCt6qMbWDfrtf7h9JFv\", \"INDn8zKQHuLYUDOM9dqo68tPnunQEo86\");\n" +
                "            // 实例化一个 http 选项，可选，无特殊需求时可以跳过\n" +
                "            HttpProfile httpProfile = new HttpProfile();\n" +
                "            /* SDK 默认使用 POST 方法。\n" +
                "             * 如需使用 GET 方法，可以在此处设置，但 GET 方法无法处理较大的请求 */\n" +
                "            httpProfile.setReqMethod(\"POST\");\n" +
                "            /* SDK 有默认的超时时间，非必要请不要进行调整\n" +
                "             * 如有需要请在代码中查阅以获取最新的默认值 */\n" +
                "            httpProfile.setConnTimeout(60);\n" +
                "            /* SDK 会自动指定域名，通常无需指定域名，但访问金融区的服务时必须手动指定域名\n" +
                "             * 例如 SMS 的上海金融区域名为 sms.ap-shanghai-fsi.tencentcloudapi.com */\n" +
                "            httpProfile.setEndpoint(\"sms.tencentcloudapi.com\");\n" +
                "            /* 非必要步骤:\n" +
                "             * 实例化一个客户端配置对象，可以指定超时时间等配置 */\n" +
                "            ClientProfile clientProfile = new ClientProfile();\n" +
                "            /* SDK 默认用 TC3-HMAC-SHA256 进行签名\n" +
                "             * 非必要请不要修改该字段 */\n" +
                "            clientProfile.setSignMethod(\"HmacSHA256\");\n" +
                "            clientProfile.setHttpProfile(httpProfile);\n" +
                "            /* 实例化 SMS 的 client 对象\n" +
                "             * 第二个参数是地域信息，可以直接填写字符串 ap-guangzhou，或者引用预设的常量 */\n" +
                "            SmsClient client = new SmsClient(cred, \"\",clientProfile);\n" +
                "            /* 实例化一个请求对象，根据调用的接口和实际情况，可以进一步设置请求参数\n" +
                "             * 您可以直接查询 SDK 源码确定接口有哪些属性可以设置\n" +
                "             * 属性可能是基本类型，也可能引用了另一个数据结构\n" +
                "             * 推荐使用 IDE 进行开发，可以方便地跳转查阅各个接口和数据结构的文档说明 */\n" +
                "            SendSmsRequest req = new SendSmsRequest();\n" +
                "            /* 填充请求参数，这里 request 对象的成员变量即对应接口的入参\n" +
                "             * 您可以通过官网接口文档或跳转到 request 对象的定义处查看请求参数的定义\n" +
                "             * 基本类型的设置:\n" +
                "             * 帮助链接：\n" +
                "             * 短信控制台：https://console.cloud.tencent.com/smsv2\n" +
                "             * sms helper：https://cloud.tencent.com/document/product/382/3773 */\n" +
                "            /* 短信应用 ID: 在 [短信控制台] 添加应用后生成的实际 SDKAppID，例如1400006666 */\n" +
                "            String appid = \"1400465545\";\n" +
                "            req.setSmsSdkAppid(appid);\n" +
                "            /* 短信签名内容: 使用 UTF-8 编码，必须填写已审核通过的签名，可登录 [短信控制台] 查看签名信息 */\n" +
                "            String sign = \"中兴轨道通讯\";\n" +
                "            req.setSign(sign);\n" +
                "            /* 国际/港澳台短信 senderid: 国内短信填空，默认未开通，如需开通请联系 [sms helper] */\n" +
                "//            String senderid = \"xxx\";\n" +
                "//            req.setSenderId(senderid);\n" +
                "            /* 用户的 session 内容: 可以携带用户侧 ID 等上下文信息，server 会原样返回 */\n" +
                "//            String session = \"xxx\";\n" +
                "//            req.setSessionContext(session);\n" +
                "            /* 短信码号扩展号: 默认未开通，如需开通请联系 [sms helper] */\n" +
                "//            String extendcode = \"xxx\";\n" +
                "//            req.setExtendCode(extendcode);\n" +
                "            /* 模板 ID: 必须填写已审核通过的模板 ID，可登录 [短信控制台] 查看模板 ID */\n" +
                "            String templateId = \"821098\";\n" +
                "            req.setTemplateID(templateId);\n" +
                "            /* 下发手机号码，采用 e.164 标准，+[国家或地区码][手机号]\n" +
                "             * 例如+8613711112222， 其中前面有一个+号 ，86为国家码，13711112222为手机号，最多不要超过200个手机号*/\n" +
                "            String[] phoneNumbers = {\"+8618349342711\"};\n" +
                "            req.setPhoneNumberSet(phoneNumbers);\n" +
                "            /* 模板参数: 若无模板参数，则设置为空*/\n" +
                "            String[] templateParams = {\"5678\"};\n" +
                "            req.setTemplateParamSet(templateParams);\n" +
                "            /* 通过 client 对象调用 SendSms 方法发起请求。注意请求方法名与请求对象是对应的\n" +
                "             * 返回的 res 是一个 SendSmsResponse 类的实例，与请求对象对应 */\n" +
                "            SendSmsResponse res = client.SendSms(req);\n" +
                "            // 输出 JSON 格式的字符串回包\n" +
                "            System.out.println(SendSmsResponse.toJsonString(res));\n" +
                "            // 可以取出单个值，您可以通过官网接口文档或跳转到 response 对象的定义处查看返回字段的定义\n" +
                "            System.out.println(res.getRequestId());\n" +
                "        } catch (TencentCloudSDKException e) {\n" +
                "            e.printStackTrace();\n" +
                "        }\n" +
                "    }\n" +
                "}";
    }

    public static String getJuheCode() {
        return "import com.alibaba.fusu.facade.SmsScript;\n" +
                "\n" +
                "import java.io.*;\n" +
                "import java.net.HttpURLConnection;\n" +
                "import java.net.URL;\n" +
                "import java.net.URLEncoder;\n" +
                "import java.util.HashMap;\n" +
                "import java.util.Map;\n" +
                "\n" +
                "\n" +
                "public class JuheDemo implements SmsScript {\n" +
                "    public static final String DEF_CHATSET = \"UTF-8\";\n" +
                "    public static final int DEF_CONN_TIMEOUT = 30000;\n" +
                "    public static final int DEF_READ_TIMEOUT = 30000;\n" +
                "    public static String userAgent = \"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36\";\n" +
                "    Map<String, String> map = new HashMap<>();\n" +
                "\n" +
                "    @Override\n" +
                "    public void parse(String phoneNum, String templateNum, Map<String, String> params) {\n" +
                "        map.put(\"mobile\", phoneNum);\n" +
                "        map.put(\"tpl_id\", templateNum);\n" +
                "        StringBuilder var = new StringBuilder();\n" +
                "        params.forEach((k, v) -> {\n" +
                "            var.append(\"#\").append(k).append(\"#\").append(\"=\").append(v).append(\"&\");\n" +
                "        });\n" +
                "        map.put(\"tpl_value\", var.toString());\n" +
                "        map.put(\"key\", params.get(\"secretKey\"));\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public void execute() {\n" +
                "        String url = \"http://v.juhe.cn/sms/send\";\n" +
                "        try {\n" +
                "            System.out.println(\"Juhe send sms : \"+net(url, map, \"GET\"));\n" +
                "        } catch (Exception e) {\n" +
                "            e.printStackTrace();\n" +
                "        }\n" +
                "    }\n" +
                "\n" +
                "    /**\n" +
                "     * @param strUrl 请求地址\n" +
                "     * @param params 请求参数\n" +
                "     * @param method 请求方法\n" +
                "     * @return 网络请求字符串\n" +
                "     * @throws Exception\n" +
                "     */\n" +
                "    public static String net(String strUrl, Map params, String method) throws Exception {\n" +
                "        HttpURLConnection conn = null;\n" +
                "        BufferedReader reader = null;\n" +
                "        String rs = null;\n" +
                "        try {\n" +
                "            StringBuffer sb = new StringBuffer();\n" +
                "            if (method == null || method.equals(\"GET\")) {\n" +
                "                strUrl = strUrl + \"?\" + urlencode(params);\n" +
                "            }\n" +
                "            System.out.println(strUrl);" +
                "            URL url = new URL(strUrl);\n" +
                "            conn = (HttpURLConnection) url.openConnection();\n" +
                "            if (method == null || method.equals(\"GET\")) {\n" +
                "                conn.setRequestMethod(\"GET\");\n" +
                "            } else {\n" +
                "                conn.setRequestMethod(\"POST\");\n" +
                "                conn.setDoOutput(true);\n" +
                "            }\n" +
                "            conn.setRequestProperty(\"User-agent\", userAgent);\n" +
                "            conn.setUseCaches(false);\n" +
                "            conn.setConnectTimeout(DEF_CONN_TIMEOUT);\n" +
                "            conn.setReadTimeout(DEF_READ_TIMEOUT);\n" +
                "            conn.setInstanceFollowRedirects(false);\n" +
                "            conn.connect();\n" +
                "            if (params != null && method.equals(\"POST\")) {\n" +
                "                try {\n" +
                "                    DataOutputStream out = new DataOutputStream(conn.getOutputStream());\n" +
                "                    out.writeBytes(urlencode(params));\n" +
                "                } catch (Exception e) {\n" +
                "                    // TODO: handle exception\n" +
                "                    e.printStackTrace();\n" +
                "                }\n" +
                "            }\n" +
                "            InputStream is = conn.getInputStream();\n" +
                "            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));\n" +
                "            String strRead = null;\n" +
                "            while ((strRead = reader.readLine()) != null) {\n" +
                "                sb.append(strRead);\n" +
                "            }\n" +
                "            rs = sb.toString();\n" +
                "        } catch (IOException e) {\n" +
                "            e.printStackTrace();\n" +
                "        } finally {\n" +
                "            if (reader != null) {\n" +
                "                reader.close();\n" +
                "            }\n" +
                "            if (conn != null) {\n" +
                "                conn.disconnect();\n" +
                "            }\n" +
                "        }\n" +
                "        return rs;\n" +
                "    }\n" +
                "\n" +
                "    //将map型转为请求参数型\n" +
                "    public static String urlencode(Map<String, String> data) {\n" +
                "        StringBuilder sb = new StringBuilder();\n" +
                "        data.forEach((key, value) -> {\n" +
                "            try {\n" +
                "                sb.append(key).append(\"=\").append(URLEncoder.encode(value + \"\", \"UTF-8\")).append(\"&\");\n" +
                "            } catch (UnsupportedEncodingException e) {\n" +
                "                e.printStackTrace();\n" +
                "            }\n" +
                "        });\n" +
                "        return sb.toString();\n" +
                "    }\n" +
                "}";
    }

    private static String getJarFiles(String jarPath) {
        File sourceFile = new File(jarPath);
        AtomicReference<String> jars = new AtomicReference<>("");
        if (sourceFile.exists()) {
            if (sourceFile.isDirectory()) {

                File[] files = sourceFile.listFiles(pathname -> {
                    if (pathname.isDirectory()) {
                        return true;
                    } else {
                        String name = pathname.getName();
                        if (name.endsWith(".jar")) {
                            jars.set(jars + pathname.getPath() + ";");
                            return true;
                        }
                        return false;
                    }
                });
            }
        }
        return jars.get();
    }

    public static void main(String[] args) throws Exception {

        long start = System.currentTimeMillis();
        //类名
//        String className = "JuheDemo";
        String className = "TencentDemo";
//        String className = "Cat";
        //项目所在路径
//        String projectPath = PathUtil.getAppHomePath();
//        String facadeJarPath = String.format("%s\\facade\\target\\facade-1.0.jar", projectPath);

        //需要进行编译的代码
        Iterable<? extends JavaFileObject> compilationUnits = new ArrayList<JavaFileObject>() {{
//            add(new JavaSourceFromString(className, getJavaCode()));
//            add(new JavaSourceFromString(className, getJuheCode()));
            add(new JavaSourceFromString(className, getTencentCode()));
        }};

        //编译的选项，对应于命令行参数
        List<String> options = new ArrayList<>();
        options.add("-classpath");
        options.add(getJarFiles("F:\\workespace\\dynamic-script\\dynamic-script\\facade\\src\\main\\resources\\lib"));
//        Iterable<String> options = Arrays.asList("-encoding", encoding, "-classpath", jars, "-d", targetDir, "-sourcepath", sourceDir);


        //使用系统的编译器
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();

        StandardJavaFileManager standardJavaFileManager = javaCompiler.getStandardFileManager(null, null, null);
        ScriptFileManager scriptFileManager = new ScriptFileManager(standardJavaFileManager);

        //使用stringWriter来收集错误。
        StringWriter errorStringWriter = new StringWriter();

        //开始进行编译
        boolean ok = javaCompiler.getTask(errorStringWriter, scriptFileManager, diagnostic -> {
            if (diagnostic.getKind() == Diagnostic.Kind.ERROR) {

                errorStringWriter.append(diagnostic.toString());
            }
        }, options, null, compilationUnits).call();

        if (!ok) {
            String errorMessage = errorStringWriter.toString();
            //编译出错，直接抛错。
            throw new RuntimeException("Compile Error:{}" + errorMessage);
        }

        //获取到编译后的二进制数据。
        final Map<String, byte[]> allBuffers = scriptFileManager.getAllBuffers();
        final byte[] catBytes = allBuffers.get(className);

        //使用自定义的ClassLoader加载类
        FsClassLoader fsClassLoader = new FsClassLoader(className, catBytes);
        Class<?> catClass = fsClassLoader.findClass(className);
        Object obj = catClass.newInstance();
//        if (obj instanceof Animal) {
//            Animal animal = (Animal) obj;
//            animal.hello("Moss");
//        }

        if (obj instanceof Script) {
            Script script = (Script) obj;
//            Map<String, String> map = new HashMap<>();
//            map.put("code", "15151");
//            map.put("m", "5");
//            map.put("secretKey", "f7d9458d592713b8cff52a3cd9939fcb");
//            script.parse("18349342711", "227510", map);
            script.execute();
        }
        System.out.println("Cost : " + (System.currentTimeMillis() - start) + "ms");
    }
}
