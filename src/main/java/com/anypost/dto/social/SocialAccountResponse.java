package com.anypost.dto.social;

import com.anypost.domain.enums.Platform;

import java.util.UUID;

public class SocialAccountResponse {

    private UUID id;
    private Platform platform;
    private boolean enabled;
    private String externalUserId;

    public SocialAccountResponse() { }

    public SocialAccountResponse(UUID id, Platform platform, boolean enabled, String externalUserId) {
        this.id = id;
        this.platform = platform;
        this.enabled = enabled;
        this.externalUserId = externalUserId;
    }

    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public Platform getPlatform() { return platform; }
    public void setPlatform(Platform platform) { this.platform = platform; }

    public boolean isEnabled() { return enabled; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }

    public String getExternalUserId() { return externalUserId; }
    public void setExternalUserId(String externalUserId) { this.externalUserId = externalUserId; }
}