package org.tatastrive.callbackapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
@Configuration
public class RestTemplateConfig {

    @Bean
//    public RestTemplate restTemplate() {
//        try {
//            TrustManager[] trustAllCertificates = new TrustManager[]{
//                    new X509TrustManager() {
//                        public X509Certificate[] getAcceptedIssuers() {
//                            return new X509Certificate[0];
//                        }
//
//                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
//                            //bypassing the ssl checking for client
//                        }
//
//                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
//                            //bypassing ssl checking for server
//                        }
//                    }
//            };
//
//            SSLContext sslContext = SSLContext.getInstance("TLS");
//            sslContext.init(null, trustAllCertificates, new java.security.SecureRandom());
//
//            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
//
//            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
//            requestFactory.setBufferRequestBody(false);
//
//            return new RestTemplate(requestFactory);
//        } catch (Exception e) {
//            throw new RuntimeException("ssl exception occured", e);        }
//    }

    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
