package com.yomakase.etc.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI springShopOpenAPI(@Value("${springdoc.version}") String version) {
        return new OpenAPI()
                .info(new Info().title("Yomakase API")
                        .description("Yomakase API Docs")
                        .version(version))
                .components(new Components()
                        .addSecuritySchemes("AccessToken", securityScheme("AccessToken"))
                        .addSecuritySchemes("RefreshToken", securityScheme("RefreshToken"))
                )
                .addSecurityItem(securityRequirement());
    }

    private SecurityScheme securityScheme(String schemeName) {
        return new SecurityScheme()
                .name(schemeName)
                .type(Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(In.HEADER);
    }

    private SecurityRequirement securityRequirement() {
        SecurityRequirement securityRequirement = new SecurityRequirement();

        securityRequirement.addList("AccessToken");
        securityRequirement.addList("RefreshToken");

        return securityRequirement;
    }
}
