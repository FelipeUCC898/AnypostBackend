package com.anypost.service;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anypost.domain.model.User;
import com.anypost.domain.repo.UserRepository;

/**
 * Maneja lookup/alta de usuarios. La autenticación real la provee Firebase.
 */
@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /** Busca por email. */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /** Busca por id. */
    public Optional<User> findById(UUID id) {
        return userRepository.findById(id);
    }

    /**
     * Stub: a partir de un UID/email de Firebase, crea o devuelve el User local.
     * TODO: traer nickname desde claims de Firebase si existe.
     */
    @Transactional
    public User getOrCreateByEmail(String email, String nicknameFallback) {
        return userRepository.findByEmail(email)
                .orElseGet(() -> {
                    User u = new User(null, nicknameFallback != null ? nicknameFallback : email, email);
                    return userRepository.save(u);
                });
    }
}