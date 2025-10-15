package com.anypost.domain.repo;

import com.anypost.domain.enums.Platform;
import com.anypost.domain.model.SocialAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SocialAccountRepository extends JpaRepository<SocialAccount, UUID> {

    List<SocialAccount> findAllByUser_Id(UUID userId);

    Optional<SocialAccount> findByUser_IdAndPlatform(UUID userId, Platform platform);

    boolean existsByUser_IdAndPlatform(UUID userId, Platform platform);
}