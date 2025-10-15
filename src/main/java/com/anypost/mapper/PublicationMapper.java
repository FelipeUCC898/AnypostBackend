// src/main/java/com/anypost/mapper/PublicationMapper.java
package com.anypost.mapper;

import com.anypost.domain.model.Publication;
import com.anypost.domain.model.User;
import com.anypost.dto.publication.PublicationResponse;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface PublicationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "title", source = "req.title")
    @Mapping(target = "description", source = "req.description")
    @Mapping(target = "mediaType", source = "req.mediaType")
    @Mapping(target = "mediaUrl", ignore = true)
    @Mapping(target = "user", source = "user")
    @Mapping(target = "createdAt", ignore = true)
    Publication fromCreate(com.anypost.dto.publication.CreatePublicationRequest req, User user);

    PublicationResponse toResponse(Publication publication);

    @AfterMapping
    default void setAudit(@MappingTarget Publication publication) {
        if (publication.getCreatedAt() == null) {
            publication.setCreatedAt(Instant.now());
        }
    }
}