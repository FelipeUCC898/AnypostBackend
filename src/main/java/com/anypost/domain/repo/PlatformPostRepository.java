package com.anypost.domain.repo;

import com.anypost.domain.enums.Platform;
import com.anypost.domain.model.PlatformPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PlatformPostRepository extends JpaRepository<PlatformPost, UUID> {

    List<PlatformPost> findAllByPostJob_IdOrderByPlatformAsc(UUID postJobId);

    Optional<PlatformPost> findByPostJob_IdAndPlatform(UUID postJobId, Platform platform);
}