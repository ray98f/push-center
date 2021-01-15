package com.zte.msg.pushcenter.pccore.config;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/5/13 15:56
 */
@Configuration
public class RestTemplateConfig {
    /**
     * 连接超时时间
     */
    private static final int CONN_TIMEOUT = 10000;
    /**
     * 请求超时时间
     */
    private static final int READ_TIMEOUT = 10000;

    private static RestTemplate restTemplate = null;

    static {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectionRequestTimeout(CONN_TIMEOUT);
        factory.setConnectTimeout(CONN_TIMEOUT);
        factory.setReadTimeout(READ_TIMEOUT);
        try {
            //设置SSL
            TrustStrategy trustStrategy = (X509Certificate[] chain, String authType) -> true;
            SSLContext sslContexts = SSLContexts.custom().loadTrustMaterial(null, trustStrategy).build();
            SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContexts);
            CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();
            factory.setHttpClient(httpClient);
            restTemplate = new RestTemplate(factory);
        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public RestTemplate restTemplateFactory() {
        return restTemplate;
    }
}
