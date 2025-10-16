package com.anypost;

import com.anypost.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Punto de entrada principal del backend de AnyPost.
 * 
 * - Usa Spring Boot 3.x (Java 21)
 * - Habilita la carga automática de las propiedades definidas en application.yml bajo el prefijo "app"
 */
@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class AnyPostApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnyPostApplication.class, args);
    }
}