package com.anypost.domain.model;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.anypost.domain.enums.Platform;
import com.anypost.domain.enums.PostStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "platform_post", indexes = {
        @Index(name = "ix_platform_post_job", columnList = "post_job_id"),
        @Index(name = "ix_platform_post_updated_at", columnList = "updatedAt"),
        @Index(name = "ux_platform_post_unique", columnList = "post_job_id,platform", unique = true)
})
@EntityListeners(AuditingEntityListener.class)
public class PlatformPost {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Platform platform;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private PostStatus status = PostStatus.QUEUED;

    @Column(length = 160)
    private String externalPostId;

    @Column(length = 2000)
    private String errorMessage;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "post_job_id", nullable = false, foreignKey = @ForeignKey(name = "fk_platform_post_job"))
    private PostJob postJob;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private Instant updatedAt;

    public PlatformPost() { }

    // Getters/Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Platform getPlatform() { return platform; }
    public void setPlatform(Platform platform) { this.platform = platform; }

    public PostStatus getStatus() { return status; }
    public void setStatus(PostStatus status) { this.status = status; }

    public String getExternalPostId() { return externalPostId; }
    public void setExternalPostId(String externalPostId) { this.externalPostId = externalPostId; }

    public String getErrorMessage() { return errorMessage; }
    public void setErrorMessage(String errorMessage) { this.errorMessage = errorMessage; }

    public PostJob getPostJob() { return postJob; }
    public void setPostJob(PostJob postJob) { this.postJob = postJob; }

    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }

    public Instant getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
}