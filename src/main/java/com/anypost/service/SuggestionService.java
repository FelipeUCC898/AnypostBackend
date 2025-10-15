package com.anypost.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anypost.domain.model.Suggestion;
import com.anypost.domain.model.User;
import com.anypost.domain.repo.SuggestionRepository;
import com.anypost.dto.suggestion.CreateSuggestionRequest;
import com.anypost.dto.suggestion.SuggestionResponse;
import com.anypost.mapper.SuggestionMapper;

@Service
@Transactional(readOnly = true)
public class SuggestionService {

    private final SuggestionRepository suggestionRepository;
    private final SuggestionMapper suggestionMapper;

    public SuggestionService(SuggestionRepository suggestionRepository, SuggestionMapper suggestionMapper) {
        this.suggestionRepository = suggestionRepository;
        this.suggestionMapper = suggestionMapper;
    }

    /** Registra el prompt (la generación del asset la hará n8n/IA). */
    @Transactional
    public SuggestionResponse create(User user, CreateSuggestionRequest req) {
        Suggestion s = suggestionMapper.fromCreate(req, user);
        s = suggestionRepository.save(s);
        return suggestionMapper.toResponse(s);
    }

    /** Lista prompts del usuario. */
    public Page<SuggestionResponse> listByUser(UUID userId, Pageable pageable) {
        return suggestionRepository.findAllByUser_IdOrderByCreatedAtDesc(userId, pageable)
                .map(suggestionMapper::toResponse);
    }
}