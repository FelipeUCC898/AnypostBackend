package com.anypost.dto.publication;

import com.anypost.domain.enums.Platform;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class SubmitPublicationRequest {

    @NotNull
    @NotEmpty
    private List<Platform> platforms; // FACEBOOK, INSTAGRAM, TIKTOK, YOUTUBE

    public SubmitPublicationRequest() { }

    public List<Platform> getPlatforms() { return platforms; }
    public void setPlatforms(List<Platform> platforms) { this.platforms = platforms; }
}