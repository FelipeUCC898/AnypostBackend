package com.anypost.domain.model;

import com.anypost.domain.enums.MediaType;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "suggestion", indexes = {
        @Index(name = "ix_suggestion_user", columnList = "user_id"),
        @Index(name = "ix_suggestion_created_at", columnList = "createdAt")
})
@EntityListeners(AuditingEntityListener.class)
public class Suggestion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 2000, nullable = false)
    private String prompt;

    @Column(length = 1024)
    private String resultMediaUrl;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private MediaType resultType; // IMAGE o VIDEO_MP4

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_suggestion_user"))
    private User user;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant updatedAt;

    public Suggestion() { }

    // Getters/Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getPrompt() { return prompt; }
    public void setPrompt(String prompt) { this.prompt = prompt; }

    public String getResultMediaUrl() { return resultMediaUrl; }
    public void setResultMediaUrl(String resultMediaUrl) { this.resultMediaUrl = resultMediaUrl; }

    public MediaType getResultType() { return resultType; }
    public void setResultType(MediaType resultType) { this.resultType = resultType; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}