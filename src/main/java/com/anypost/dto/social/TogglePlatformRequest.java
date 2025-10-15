package com.anypost.dto.social;

import jakarta.validation.constraints.NotNull;

public class TogglePlatformRequest {

    @NotNull
    private Boolean enabled;

    public TogglePlatformRequest() { }

    public Boolean getEnabled() { return enabled; }
    public void setEnabled(Boolean enabled) { this.enabled = enabled; }
}