package com.anypost.service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.stereotype.Service;

/**
 * Stub de cifrado/descifrado para tokens.
 * IMPORTANTE: Esto NO es seguro. Úsalo solo en dev.
 * TODO: Reemplazar por Azure Key Vault / Managed HSM.
 */
@Service
public class CryptoService {

    public String encrypt(String plain) {
        if (plain == null) return null;
        // Stub: Base64
        return Base64.getEncoder().encodeToString(plain.getBytes(StandardCharsets.UTF_8));
    }

    public String decrypt(String enc) {
        if (enc == null) return null;
        return new String(Base64.getDecoder().decode(enc), StandardCharsets.UTF_8);
    }
}