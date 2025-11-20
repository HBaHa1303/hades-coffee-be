package com.hades.hadescoffee.infrastructure.configs;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.headers.Header;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        SecurityRequirement securityRequirement = new SecurityRequirement().addList("Bearer Authentication");
        Components components = new Components()
                .addSecuritySchemes("Bearer Authentication", createAPIKeyScheme())
                .addParameters("Language", createAcceptLanguageParameter())
                .addParameters("globalAcceptLanguage", createAcceptLanguageParameter());
        return new OpenAPI().addSecurityItem(securityRequirement)
                .components(components)
                .info(createAuthorInfo());
    }

    private Info createAuthorInfo() {
        return new Info().title("Hades Coffee")
                .description("This is my API in Hades Coffee project")
                .version("1.0.0")
                .contact(new Contact().name("Hoàng Bá Hà")
                        .email("hoangbaha1303@gmail.com")
                        .url(""))
                .license(new License()
                        .name("License of API")
                        .url("API license URL")
                );
    }

    private Parameter createAcceptLanguageParameter() {
        return new Parameter()
                .name("Accept-Language")
                .in("header")
                .required(false)
                .schema(new StringSchema()._default("vi"))
                .description("Language for i18n (vi, en, en-US, ...)");
    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}
