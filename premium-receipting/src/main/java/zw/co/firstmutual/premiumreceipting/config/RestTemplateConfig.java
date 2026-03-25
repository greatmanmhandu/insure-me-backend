package zw.co.firstmutual.premiumreceipting.config;

//import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;



@Configuration
public class RestTemplateConfig {

//    @Bean
//    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
//        TrustManager[] trustManagers = new TrustManager[]{new CustomTrustManager()};
//        SSLContext sslContext = SSLContext.getInstance("TLS");
//        sslContext.init(null, trustManagers, null);
//        ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(HttpClients.custom().setSSLContext(sslContext).build());
//        return restTemplateBuilder.requestFactory(() -> requestFactory).build();
//    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
