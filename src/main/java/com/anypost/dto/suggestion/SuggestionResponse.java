package com.anypost.dto.suggestion;

import com.anypost.domain.enums.MediaType;

import java.time.Instant;
import java.util.UUID;

public class SuggestionResponse {

    private UUID id;
    private String prompt;
    private String resultMediaUrl;  // puede ser null si aún no hay asset generado
    private MediaType resultType;   // IMAGE o VIDEO_MP4
    private UUID userId;
    private Instant createdAt;

    public SuggestionResponse() { }

    public SuggestionResponse(UUID id, String prompt, String resultMediaUrl,
                              MediaType resultType, UUID userId, Instant createdAt) {
        this.id = id;
        this.prompt = prompt;
        this.resultMediaUrl = resultMediaUrl;
        this.resultType = resultType;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getPrompt() { return prompt; }
    public void setPrompt(String prompt) { this.prompt = prompt; }

    public String getResultMediaUrl() { return resultMediaUrl; }
    public void setResultMediaUrl(String resultMediaUrl) { this.resultMediaUrl = resultMediaUrl; }

    public MediaType getResultType() { return resultType; }
    public void setResultType(MediaType resultType) { this.resultType = resultType; }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}