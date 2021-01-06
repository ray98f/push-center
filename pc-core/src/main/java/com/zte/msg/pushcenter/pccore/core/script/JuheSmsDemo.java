import com.zte.msg.pushcenter.pcscript.Script;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;


public class JuheSmsDemo implements Script {
    public static final String DEF_CHATSET = "UTF-8";
    public static final int DEF_CONN_TIMEOUT = 30000;
    public static final int DEF_READ_TIMEOUT = 30000;
    public static String userAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36";

    @Override
    public String execute(Map<String, Object> params) {
        System.out.println("phoneNum :" + params.get("phoneNum") + "; templateId: " + params.get("templateId") + "; params size: " + params.size());
        StringBuilder var = new StringBuilder();
        Map<String, String> vars = (Map<String, String>) params.get("vars");
        vars.forEach((k, v) -> var.append("#").append(k).append("#").append("=").append(v).append("&"));
        String url = "http://v.juhe.cn/sms/send?";
        StringBuilder url1 = new StringBuilder(url);
        url1.append("mobile").append("=").append(params.get("phoneNum"))
                .append("&").append("tpl_id").append("=").append(params.get("sTemplateId"))
                .append("&").append("tpl_value").append("=").append(var.toString())
                .append("&").append("key").append("=").append(params.get("secretKey"));

        return net(url1.toString(), "GET");
    }

    /**
     * @param strUrl 请求地址
     * @param method 请求方法
     * @return 网络请求字符串
     * @throws Exception
     */
    public static String net(String strUrl, String method) {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        String rs = null;
        StringBuilder sb = new StringBuilder();
        try {
            strUrl = strUrl.replaceAll("#", URLEncoder.encode("#", "UTF-8"));
            System.out.println(strUrl);
            URL url = new URL(strUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(method);
            conn.setRequestProperty("User-agent", userAgent);
            conn.setUseCaches(false);
            conn.setConnectTimeout(DEF_CONN_TIMEOUT);
            conn.setReadTimeout(DEF_READ_TIMEOUT);
            conn.setInstanceFollowRedirects(false);
            conn.connect();
            InputStream is = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, DEF_CHATSET));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sb.append(strRead);
            }
            rs = sb.toString();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rs;
    }
}