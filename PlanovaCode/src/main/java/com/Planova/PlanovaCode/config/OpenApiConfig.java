// src/main/java/com/Planova/PlanovaCode/config/OpenApiConfig.java
package com.Planova.PlanovaCode.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Planova Catalog API")
                        .version("v1")
                        .description("Catálogo In-Memory de Events y Venues - demo"));
    }
}
