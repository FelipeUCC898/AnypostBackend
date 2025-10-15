package com.anypost.service;

import com.anypost.domain.enums.Platform;
import com.anypost.domain.model.SocialAccount;
import com.anypost.domain.model.User;
import com.anypost.domain.repo.SocialAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class SocialAccountService {

    private final SocialAccountRepository socialAccountRepository;
    private final CryptoService cryptoService;

    public SocialAccountService(SocialAccountRepository socialAccountRepository, CryptoService cryptoService) {
        this.socialAccountRepository = socialAccountRepository;
        this.cryptoService = cryptoService;
    }

    public List<SocialAccount> listByUser(UUID userId) {
        return socialAccountRepository.findAllByUser_Id(userId);
    }

    @Transactional
    public SocialAccount toggleEnabled(UUID userId, Platform platform, boolean enabled) {
        SocialAccount acc = socialAccountRepository.findByUser_IdAndPlatform(userId, platform)
                .orElseThrow(() -> new IllegalArgumentException("Cuenta social no encontrada para " + platform));
        acc.setEnabled(enabled);
        return socialAccountRepository.save(acc);
    }

    /**
     * Stub: guarda/actualiza tokens recibidos desde n8n (OAuth callback).
     * Los tokens se almacenan cifrados (stub base64).
     */
    @Transactional
    public SocialAccount upsertTokens(User user,
                                      Platform platform,
                                      String externalUserId,
                                      String accessTokenPlain,
                                      String refreshTokenPlain,
                                      Instant expiresAt) {
        SocialAccount acc = socialAccountRepository
                .findByUser_IdAndPlatform(user.getId(), platform)
                .orElseGet(SocialAccount::new);

        acc.setUser(user);
        acc.setPlatform(platform);
        acc.setExternalUserId(externalUserId);
        acc.setAccessTokenEnc(cryptoService.encrypt(accessTokenPlain));
        acc.setRefreshTokenEnc(refreshTokenPlain != null ? cryptoService.encrypt(refreshTokenPlain) : null);
        acc.setExpiresAt(expiresAt);
        acc.setEnabled(true);

        return socialAccountRepository.save(acc);
    }
}