package com.example.study01.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;



@Configuration
public class HttpClientConfig {

    @Bean
    public RestTemplate restTemplate() {
        // HttpClient 설정은 개발자가 원하는 데로 커스터마이징
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        HttpClient httpClient = HttpClientBuilder.create()
                .setMaxConnTotal(50) //최대 커넥션 수
                .setMaxConnPerRoute(10) // 각 호스트당 커넥션 풀에 생성가능한 커넥션
                .build();

        factory.setHttpClient(httpClient);
        factory.setConnectTimeout(3000); //connection timeout
        factory.setReadTimeout(5000); //read timeout

        return new RestTemplate(factory);
    }
}
