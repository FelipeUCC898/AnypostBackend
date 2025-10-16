package com.anypost.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;
import org.springframework.http.CacheControl;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

@Configuration
public class WebConfig {

    /** CORS configurable desde application.yml (app.cors.*). */
    @Bean
    public CorsFilter corsFilter(AppProperties props) {
        var cfg = new CorsConfiguration();

        List<String> origins = props.getCors().getAllowedOrigins();
        if (origins == null || origins.isEmpty()) {
            // DEV: permitir todo. En PROD define orígenes explícitos en app.cors.allowed-origins
            cfg.setAllowedOriginPatterns(List.of("*"));
        } else {
            cfg.setAllowedOrigins(origins);
        }

        cfg.setAllowedMethods(props.getCors().getAllowedMethods());
        cfg.setAllowedHeaders(props.getCors().getAllowedHeaders());
        cfg.setAllowCredentials(props.getCors().isAllowCredentials());
        cfg.setMaxAge(props.getCors().getMaxAgeSeconds());

        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", cfg);
        return new CorsFilter(source);
    }

    /** Jackson: ISO-8601 y TZ consistente. */
    @Bean
    public Jackson2ObjectMapperBuilder jacksonCustomizer() {
        Locale.setDefault(Locale.US);
        TimeZone.setDefault(TimeZone.getTimeZone("America/Bogota"));
        return Jackson2ObjectMapperBuilder.json()
                .indentOutput(false)
                .failOnUnknownProperties(false)
                .timeZone(TimeZone.getDefault());
    }

    /** Pageable defaults. */
    @Bean
    public PageableHandlerMethodArgumentResolverCustomizer pageableCustomizer() {
        return resolver -> {
            resolver.setOneIndexedParameters(false); // 0..N
            resolver.setMaxPageSize(200);
        };
    }

    /** Estáticos (si usas /static); cache control moderno. */
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/**")
                        .addResourceLocations("classpath:/static/")
                        .setCacheControl(CacheControl.maxAge(Duration.ofDays(7)).cachePublic());
            }
        };
    }

    /** RestTemplate para llamadas HTTP externas (n8n, etc.) */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}