package com.anypost.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    private static final String DEFAULT_SCHEME_NAME = "bearer-jwt";

    @Bean
    public OpenAPI baseOpenAPI(AppProperties appProperties) {
        final String schemeName = appProperties.getSecurity().getSwaggerSchemeName() != null
                ? appProperties.getSecurity().getSwaggerSchemeName()
                : DEFAULT_SCHEME_NAME;

        return new OpenAPI()
                .info(new Info()
                        .title("AnyPost API")
                        .version("v1")
                        .description("API para orquestar publicaciones multi-plataforma con n8n")
                        .contact(new Contact().name("AnyPost Team")))
                .components(new Components().addSecuritySchemes(
                        schemeName, new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                ))
                .addSecurityItem(new SecurityRequirement().addList(schemeName));
    }

    /**
     * Permite ocultar endpoints o anotar metadata adicional si lo necesitas.
     */
    @Bean
    @ConditionalOnProperty(prefix = "app.security", name = "swagger-ui-enabled", havingValue = "true", matchIfMissing = true)
    public OpenApiCustomizer openApiCustomizer() {
        return openApi -> {
            // Aquí podrías aplicar filtros globales de tags, ordenar paths, etc.
        };
    }
}