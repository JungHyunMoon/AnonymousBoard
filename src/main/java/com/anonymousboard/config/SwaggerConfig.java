package com.anonymousboard.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@OpenAPIDefinition(
        info = @Info(
                title = "AnonymousBoard API 명세서",
                description = "AnonymousBoard 프로젝트에 사용되는 API 명세서입니다.",
                version = "v1"
        )
)
@Configuration
public class SwaggerConfig {

}