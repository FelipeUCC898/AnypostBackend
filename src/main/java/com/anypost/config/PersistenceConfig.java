package com.anypost.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/**
 * Habilita Auditoría JPA (@CreatedDate, @LastModifiedDate).
 * Puedes enriquecer el AuditorAware para devolver el uid de Firebase cuando integremos seguridad.
 */
@Configuration
@EnableJpaAuditing
public class PersistenceConfig {

    @Bean
    public AuditorAware<String> auditorAware() {
        // TODO: Integrar con seguridad para devolver el usuario real (uid/email).
        // Por ahora, dejamos "system" como auditor.
        return () -> Optional.of("system");
    }
}