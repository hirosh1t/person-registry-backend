package com.sermaluc.registry.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI personRegistryOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Person Registry API")
                        .version("v1")
                        .description("API para registrar y gestionar personas"));
    }
}