package com.anypost.domain.repo;

import com.anypost.domain.model.Suggestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SuggestionRepository extends JpaRepository<Suggestion, UUID> {

    Page<Suggestion> findAllByUser_IdOrderByCreatedAtDesc(UUID userId, Pageable pageable);
}