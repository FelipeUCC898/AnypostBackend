package com.anypost.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
// OJO: sin @Component aquí

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

    public static class N8n {
        private String webhookUrl;
        private String apiKey;
        private Duration httpTimeout = Duration.ofSeconds(20);

        public String getWebhookUrl() { return webhookUrl; }
        public void setWebhookUrl(String webhookUrl) { this.webhookUrl = webhookUrl; }

        public String getApiKey() { return apiKey; }
        public void setApiKey(String apiKey) { this.apiKey = apiKey; }

        public Duration getHttpTimeout() { return httpTimeout; }
        public void setHttpTimeout(Duration httpTimeout) { this.httpTimeout = httpTimeout; }
    }

    public static class Firebase {
        private String credentialsLocation;
        private boolean enabled = false;

        public String getCredentialsLocation() { return credentialsLocation; }
        public void setCredentialsLocation(String credentialsLocation) { this.credentialsLocation = credentialsLocation; }

        public boolean isEnabled() { return enabled; }
        public void setEnabled(boolean enabled) { this.enabled = enabled; }
    }

    public static class Storage {
        private String container;
        private String accountName;
        private String endpoint;
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

    public static class Cors {
        private List<String> allowedOrigins = new ArrayList<>();
        private List<String> allowedMethods = List.of("GET","POST","PUT","PATCH","DELETE","OPTIONS");
        private List<String> allowedHeaders = List.of("Authorization","Content-Type","X-Requested-With","X-Request-Id");
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

    public static class Security {
        private String swaggerSchemeName = "bearer-jwt";
        private boolean swaggerUiEnabled = true;

        public String getSwaggerSchemeName() { return swaggerSchemeName; }
        public void setSwaggerSchemeName(String swaggerSchemeName) { this.swaggerSchemeName = swaggerSchemeName; }

        public boolean isSwaggerUiEnabled() { return swaggerUiEnabled; }
        public void setSwaggerUiEnabled(boolean swaggerUiEnabled) { this.swaggerUiEnabled = swaggerUiEnabled; }
    }
}