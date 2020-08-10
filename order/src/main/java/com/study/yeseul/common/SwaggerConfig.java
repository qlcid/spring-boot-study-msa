package com.study.yeseul.common;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket all() {
        final ApiInfo apiInfo = new ApiInfo("STUDY API",
                "",
                "0.0.1",
                "",
                new Contact("Study Studio", "", "yeseul@gmail.com"),
                "",
                "",
                Collections.emptyList()
        );

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("All")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.study"))
                .build()
                .apiInfo(apiInfo);
    }


}