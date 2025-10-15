package com.anypost.mapper;

import com.anypost.domain.model.Suggestion;
import com.anypost.domain.model.User;
import com.anypost.dto.suggestion.CreateSuggestionRequest;
import com.anypost.dto.suggestion.SuggestionResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface SuggestionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "prompt", source = "req.prompt")
    @Mapping(target = "resultMediaUrl", ignore = true)
    @Mapping(target = "resultType", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "createdAt", ignore = true)
    Suggestion fromCreate(CreateSuggestionRequest req, User user);

    SuggestionResponse toResponse(Suggestion suggestion);

    @AfterMapping
    default void setAudit(@MappingTarget Suggestion suggestion) {
        if (suggestion.getCreatedAt() == null) {
            suggestion.setCreatedAt(Instant.now());
        }
    }
}