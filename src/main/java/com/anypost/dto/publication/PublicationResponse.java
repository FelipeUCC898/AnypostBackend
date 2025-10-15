package com.anypost.dto.publication;

import com.anypost.domain.enums.MediaType;

import java.time.Instant;
import java.util.UUID;

public class PublicationResponse {

    private UUID id;
    private String title;
    private String description;
    private MediaType mediaType;
    private String mediaUrl;     // puede ser null si el upload lo maneja n8n
    private UUID userId;
    private Instant createdAt;

    public PublicationResponse() { }

    public PublicationResponse(UUID id, String title, String description,
                               MediaType mediaType, String mediaUrl,
                               UUID userId, Instant createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.mediaType = mediaType;
        this.mediaUrl = mediaUrl;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public MediaType getMediaType() { return mediaType; }
    public void setMediaType(MediaType mediaType) { this.mediaType = mediaType; }

    public String getMediaUrl() { return mediaUrl; }
    public void setMediaUrl(String mediaUrl) { this.mediaUrl = mediaUrl; }

    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}