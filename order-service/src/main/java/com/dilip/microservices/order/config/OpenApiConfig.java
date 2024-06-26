package com.dilip.microservices.order.config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI orderServiceOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info().title("Order Service API")
                        .description("Order Service API reference for developers")
                        .version("v0.0.1")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("You can refer to Order Service Documentation")
                        .url("https://springdoc.org"));
    }
}

