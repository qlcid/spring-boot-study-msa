package com.study.yeseul.common;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {

    private final RestTemplateBuilder restTemplateBuilder;

    public RestTemplateConfig(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Bean
    public RestTemplate orderRestTemplate() {
        return restTemplateBuilder.rootUri("http://127.0.0.1:2000")
//                .setConnectTimeout(Duration.ofMinutes(1))
//                .setReadTimeout(Duration.ofMinutes(5))
                .build();
    }

}
