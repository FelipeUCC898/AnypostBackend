package com.anypost.integration.n8n;

import com.anypost.domain.enums.Platform;
import com.anypost.domain.enums.PostStatus;
import com.anypost.service.JobStatusService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/webhooks/n8n")
public class N8nWebhookController {

    private final JobStatusService jobStatusService;

    /**
     * n8n parchea el estado de publicación de una plataforma para un Job.
     * Ejemplo payload:
     * {
     *   "platform": "INSTAGRAM",
     *   "status": "PUBLISHED",
     *   "externalId": "178964..._some_id",
     *   "errorMessage": null
     * }
     */
    @PatchMapping("/jobs/{jobId}/platform-status")
    public ResponseEntity<Void> patchPlatformStatus(
            @PathVariable UUID jobId,
            @RequestBody PlatformStatusUpdate body
    ) {
        log.info("[n8n webhook] jobId={}, platform={}, status={}, externalId={}",
                jobId, body.platform(), body.status(), body.externalId());

        // String -> enum (case-insensitive)
        Platform platform = Platform.valueOf(body.platform().toUpperCase(Locale.ROOT));
        PostStatus status = PostStatus.valueOf(body.status().toUpperCase(Locale.ROOT));

        jobStatusService.updatePlatformStatus(
                jobId,
                platform,
                status,
                body.externalId(),
                body.errorMessage()
        );

        return ResponseEntity.noContent().build();
    }

    // DTO simple del cuerpo que manda n8n
    public record PlatformStatusUpdate(
            String platform,     // p.ej. "INSTAGRAM", "TIKTOK"
            String status,       // p.ej. "PUBLISHED", "FAILED"
            String externalId,   // id devuelto por la plataforma (opcional)
            String errorMessage  // error si FAILED (opcional)
    ) {}
}