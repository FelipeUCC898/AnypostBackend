package com.anypost.mapper;

import com.anypost.domain.model.Suggestion;
import com.anypost.domain.model.User;
import com.anypost.dto.suggestion.CreateSuggestionRequest;
import com.anypost.dto.suggestion.SuggestionResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SuggestionMapper {

    // Entity -> DTO
    @Mapping(target = "userId", source = "user.id")
    SuggestionResponse toResponse(Suggestion entity);

    // Create DTO + User -> Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "resultMediaUrl", ignore = true)  // aún no generado por IA/n8n
    @Mapping(target = "resultType", ignore = true)      // se define cuando llegue el asset
    @Mapping(target = "createdAt", ignore = true)       // auditoría JPA
    @Mapping(target = "updatedAt", ignore = true)       // auditoría JPA
    Suggestion fromCreate(CreateSuggestionRequest req, User user);

    @AfterMapping
    default void normalize(@MappingTarget Suggestion entity) {
        if (entity.getPrompt() != null) {
            entity.setPrompt(entity.getPrompt().trim());
        }
    }
}