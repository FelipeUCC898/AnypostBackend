package com.anypost.mapper;

import com.anypost.domain.model.Publication;
import com.anypost.domain.model.User;
import com.anypost.dto.publication.CreatePublicationRequest;
import com.anypost.dto.publication.PublicationResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PublicationMapper {

    // Entity -> DTO
    @Mapping(target = "userId", source = "user.id")
    PublicationResponse toResponse(Publication entity);

    // Create DTO + User -> Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "mediaUrl", ignore = true)        // la maneja n8n o se agrega luego
    @Mapping(target = "createdAt", ignore = true)       // auditoría JPA
    @Mapping(target = "updatedAt", ignore = true)       // auditoría JPA
    Publication fromCreate(CreatePublicationRequest req, User user);

    // Hook opcional por si quieres normalizar/trim
    @AfterMapping
    default void normalize(@MappingTarget Publication entity) {
        if (entity.getTitle() != null) {
            entity.setTitle(entity.getTitle().trim());
        }
        if (entity.getDescription() != null) {
            entity.setDescription(entity.getDescription().trim());
        }
    }
}