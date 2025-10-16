package com.anypost.mapper;

import com.anypost.domain.model.Publication;
import com.anypost.domain.model.User;
import com.anypost.dto.publication.PublicationResponse;
import com.anypost.dto.publication.CreatePublicationRequest;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface PublicationMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "mediaUrl", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    Publication fromCreate(CreatePublicationRequest dto, User user);

    @Mapping(target = "userId", source = "user.id")
    PublicationResponse toResponse(Publication publication);

    @AfterMapping
    default void afterMapping(
            @MappingTarget Publication publication,
            CreatePublicationRequest dto,
            User user
    ) {
        if (user != null) {
            publication.setUser(user);
        }
        if (publication.getCreatedAt() == null) {
            publication.setCreatedAt(Instant.now());
        }
    }
}