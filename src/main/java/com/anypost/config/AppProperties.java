package com.anypost.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private final N8n n8n = new N8n();
    private final Firebase firebase = new Firebase();
    private final Storage storage = new Storage();
    private final Cors cors = new Cors();
    private final Security security = new Security();

    public N8n getN8n() { return n8n; }
    public Firebase getFirebase() { return firebase; }
    public Storage getStorage() { return storage; }
    public Cors getCors() { return cors; }
    public Security getSecurity() { return security; }

    // ---------- Sección n8n ----------
    public static class N8n {
        /**
         * URL base del n8n (por ejemplo, https://n8n.example.com)
         */
        private String baseUrl;

        /**
         * Token / firma compartida para invocar webhooks (si aplica).
         */
        private String accessToken;

        /**
         * Timeout HTTP para llamadas al n8n.
         */
        private Duration httpTimeout = Duration.ofSeconds(20);

        public String getBaseUrl() { return baseUrl; }
        public void setBaseUrl(String baseUrl) { this.baseUrl = baseUrl; }

        public String getAccessToken() { return accessToken; }
        public void setAccessToken(String accessToken) { this.accessToken = accessToken; }

        public Duration getHttpTimeout() { return httpTimeout; }
        public void setHttpTimeout(Duration httpTimeout) { this.httpTimeout = httpTimeout; }
    }

    // ---------- Sección Firebase ----------
    public static class Firebase {
        /**
         * Si se usa Admin SDK, ruta a credenciales o variable con el JSON.
         */
        private String credentialsLocation;

        /**
         * Habilitar/Deshabilitar verificación de tokens (útil en dev).
         */
        private boolean enabled = false;

        public String getCredentialsLocation() { return credentialsLocation; }
        public void setCredentialsLocation(String credentialsLocation) { this.credentialsLocation = credentialsLocation; }

        public boolean isEnabled() { return enabled; }
        public void setEnabled(boolean enabled) { this.enabled = enabled; }
    }

    // ---------- Sección Storage ----------
    public static class Storage {
        /**
         * Nombre de la cuenta/contenedor (Azure Blob) o bucket equivalente.
         */
        private String container;
        private String accountName;
        private String endpoint; // e.g. https://<account>.blob.core.windows.net
        private Duration signedUrlTtl = Duration.ofMinutes(15);

        public String getContainer() { return container; }
        public void setContainer(String container) { this.container = container; }

        public String getAccountName() { return accountName; }
        public void setAccountName(String accountName) { this.accountName = accountName; }

        public String getEndpoint() { return endpoint; }
        public void setEndpoint(String endpoint) { this.endpoint = endpoint; }

        public Duration getSignedUrlTtl() { return signedUrlTtl; }
        public void setSignedUrlTtl(Duration signedUrlTtl) { this.signedUrlTtl = signedUrlTtl; }
    }

    // ---------- Sección CORS ----------
    public static class Cors {
        private List<String> allowedOrigins = new ArrayList<>();
        private List<String> allowedMethods = List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS");
        private List<String> allowedHeaders = List.of("Authorization", "Content-Type", "X-Requested-With", "X-Request-Id");
        private boolean allowCredentials = true;
        private long maxAgeSeconds = 3600L;

        public List<String> getAllowedOrigins() { return allowedOrigins; }
        public void setAllowedOrigins(List<String> allowedOrigins) { this.allowedOrigins = allowedOrigins; }

        public List<String> getAllowedMethods() { return allowedMethods; }
        public void setAllowedMethods(List<String> allowedMethods) { this.allowedMethods = allowedMethods; }

        public List<String> getAllowedHeaders() { return allowedHeaders; }
        public void setAllowedHeaders(List<String> allowedHeaders) { this.allowedHeaders = allowedHeaders; }

        public boolean isAllowCredentials() { return allowCredentials; }
        public void setAllowCredentials(boolean allowCredentials) { this.allowCredentials = allowCredentials; }

        public long getMaxAgeSeconds() { return maxAgeSeconds; }
        public void setMaxAgeSeconds(long maxAgeSeconds) { this.maxAgeSeconds = maxAgeSeconds; }
    }

    // ---------- Sección Security (varios toggles útiles) ----------
    public static class Security {
        /**
         * Nombre del esquema de seguridad para Swagger.
         */
        private String swaggerSchemeName = "bearer-jwt";

        /**
         * ¿Permitir Swagger UI en prod?
         */
        private boolean swaggerUiEnabled = true;

        public String getSwaggerSchemeName() { return swaggerSchemeName; }
        public void setSwaggerSchemeName(String swaggerSchemeName) { this.swaggerSchemeName = swaggerSchemeName; }

        public boolean isSwaggerUiEnabled() { return swaggerUiEnabled; }
        public void setSwaggerUiEnabled(boolean swaggerUiEnabled) { this.swaggerUiEnabled = swaggerUiEnabled; }
    }
}