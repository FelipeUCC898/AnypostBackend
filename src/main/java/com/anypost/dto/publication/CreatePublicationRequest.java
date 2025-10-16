package com.anypost.dto.publication;

import com.anypost.domain.enums.MediaType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Objects;

/**
 * DTO para crear una publicación.
 */
public class CreatePublicationRequest {

    @NotBlank
    @Size(max = 140)
    private String title;

    @Size(max = 2000)
    private String description;

    @NotNull
    private MediaType mediaType; // VIDEO_MP4 o IMAGE

    public CreatePublicationRequest() { }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public MediaType getMediaType() { return mediaType; }
    public void setMediaType(MediaType mediaType) { this.mediaType = mediaType; }

    @Override
    public String toString() {
        return "CreatePublicationRequest{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", mediaType=" + mediaType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreatePublicationRequest)) return false;
        CreatePublicationRequest that = (CreatePublicationRequest) o;
        return Objects.equals(title, that.title) &&
               Objects.equals(description, that.description) &&
               mediaType == that.mediaType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, mediaType);
    }
}