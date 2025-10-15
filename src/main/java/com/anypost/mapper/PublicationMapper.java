package com.anypost.mapper;

import com.anypost.domain.enums.MediaType;
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
    @Mapping(target = "title", source = "title")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "mediaType", source = "mediaType")
    @Mapping(target = "mediaUrl", ignore = true)
    @Mapping(target = "user", ignore = true)      // lo seteamos en @AfterMapping
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Publication fromCreate(String title, String description, MediaType mediaType, User user);

    PublicationResponse toResponse(Publication publication);

    @AfterMapping
    default void after(@MappingTarget Publication publication, String title, String description, MediaType mediaType, User user) {
        if (user != null) publication.setUser(user);
        if (publication.getCreatedAt() == null) publication.setCreatedAt(Instant.now());
    }
}