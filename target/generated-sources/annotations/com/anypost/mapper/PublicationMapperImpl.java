package com.anypost.mapper;

import com.anypost.domain.model.Publication;
import com.anypost.domain.model.User;
import com.anypost.dto.publication.CreatePublicationRequest;
import com.anypost.dto.publication.PublicationResponse;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-15T12:14:25-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class PublicationMapperImpl implements PublicationMapper {

    @Override
    public PublicationResponse toResponse(Publication entity) {
        if ( entity == null ) {
            return null;
        }

        PublicationResponse publicationResponse = new PublicationResponse();

        publicationResponse.setUserId( entityUserId( entity ) );
        publicationResponse.setId( entity.getId() );
        publicationResponse.setTitle( entity.getTitle() );
        publicationResponse.setDescription( entity.getDescription() );
        publicationResponse.setMediaType( entity.getMediaType() );
        publicationResponse.setMediaUrl( entity.getMediaUrl() );
        publicationResponse.setCreatedAt( entity.getCreatedAt() );

        return publicationResponse;
    }

    @Override
    public Publication fromCreate(CreatePublicationRequest req, User user) {
        if ( req == null && user == null ) {
            return null;
        }

        Publication publication = new Publication();

        if ( req != null ) {
            publication.setTitle( req.getTitle() );
            publication.setDescription( req.getDescription() );
            publication.setMediaType( req.getMediaType() );
        }
        publication.setUser( user );

        normalize( publication );

        return publication;
    }

    private UUID entityUserId(Publication publication) {
        if ( publication == null ) {
            return null;
        }
        User user = publication.getUser();
        if ( user == null ) {
            return null;
        }
        UUID id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
