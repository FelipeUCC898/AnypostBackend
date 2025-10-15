package com.anypost.mapper;

import com.anypost.domain.model.Publication;
import com.anypost.domain.model.User;
import com.anypost.dto.publication.CreatePublicationRequest;
import com.anypost.dto.publication.PublicationResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-15T12:25:10-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class PublicationMapperImpl implements PublicationMapper {

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
        if ( user != null ) {
            publication.setUser( user );
            publication.setUpdatedAt( user.getUpdatedAt() );
        }

        setAudit( publication );

        return publication;
    }

    @Override
    public PublicationResponse toResponse(Publication publication) {
        if ( publication == null ) {
            return null;
        }

        PublicationResponse publicationResponse = new PublicationResponse();

        publicationResponse.setId( publication.getId() );
        publicationResponse.setTitle( publication.getTitle() );
        publicationResponse.setDescription( publication.getDescription() );
        publicationResponse.setMediaType( publication.getMediaType() );
        publicationResponse.setMediaUrl( publication.getMediaUrl() );
        publicationResponse.setCreatedAt( publication.getCreatedAt() );

        return publicationResponse;
    }
}
