package com.anypost.integration.n8n;

import com.anypost.config.AppProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class N8nClient {

    private final RestTemplate restTemplate;
    private final AppProperties props;

    /**
     * Dispara el workflow de n8n para publicar una publicación.
     */
    public void triggerSubmit(UUID jobId, UUID publicationId) {
        String url = props.getN8n().getWebhookUrl();
        if (url == null || url.isBlank()) {
            throw new IllegalStateException("Falta configurar app.n8n.webhook-url en application.yml");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String apiKey = props.getN8n().getApiKey();
        if (apiKey != null && !apiKey.isBlank()) {
            // ajusta el nombre del header a como lo espere tu n8n (por ejemplo, "X-API-KEY" o "Authorization: Bearer ...")
            headers.set("X-API-KEY", apiKey);
        }

        Map<String, Object> payload = Map.of(
                "jobId", jobId,
                "publicationId", publicationId
        );

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

        log.info("Calling n8n webhook: {} body={}", url, payload);
        restTemplate.postForEntity(url, request, Void.class);
    }
}