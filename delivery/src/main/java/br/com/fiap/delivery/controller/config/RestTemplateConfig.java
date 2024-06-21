package br.com.fiap.delivery.controller.config;

import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.client5.http.impl.io.PoolingHttpClientConnectionManagerBuilder;
import org.apache.hc.client5.http.io.HttpClientConnectionManager;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactory;
import org.apache.hc.client5.http.ssl.SSLConnectionSocketFactoryBuilder;
import org.apache.hc.core5.ssl.SSLContexts;
import org.apache.hc.core5.ssl.TrustStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.cert.X509Certificate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Bean
    @Qualifier("ssl")
    public RestTemplate restTemplateWithSSL() throws Exception {
        TrustStrategy trust = (X509Certificate[] chain, String authType) -> true;
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, trust).build();
        SSLConnectionSocketFactory csf = SSLConnectionSocketFactoryBuilder.create().setSslContext(sslContext).build();
        HttpClientConnectionManager cm = PoolingHttpClientConnectionManagerBuilder.create().setSSLSocketFactory(csf).build();
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
        return new RestTemplate(requestFactory);
    }

}