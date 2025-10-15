package com.anypost.mapper;

import com.anypost.domain.model.Suggestion;
import com.anypost.domain.model.User;
import com.anypost.dto.suggestion.CreateSuggestionRequest;
import com.anypost.dto.suggestion.SuggestionResponse;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-15T12:14:25-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class SuggestionMapperImpl implements SuggestionMapper {

    @Override
    public SuggestionResponse toResponse(Suggestion entity) {
        if ( entity == null ) {
            return null;
        }

        SuggestionResponse suggestionResponse = new SuggestionResponse();

        suggestionResponse.setUserId( entityUserId( entity ) );
        suggestionResponse.setId( entity.getId() );
        suggestionResponse.setPrompt( entity.getPrompt() );
        suggestionResponse.setResultMediaUrl( entity.getResultMediaUrl() );
        suggestionResponse.setResultType( entity.getResultType() );
        suggestionResponse.setCreatedAt( entity.getCreatedAt() );

        return suggestionResponse;
    }

    @Override
    public Suggestion fromCreate(CreateSuggestionRequest req, User user) {
        if ( req == null && user == null ) {
            return null;
        }

        Suggestion suggestion = new Suggestion();

        if ( req != null ) {
            suggestion.setPrompt( req.getPrompt() );
        }
        suggestion.setUser( user );

        normalize( suggestion );

        return suggestion;
    }

    private UUID entityUserId(Suggestion suggestion) {
        if ( suggestion == null ) {
            return null;
        }
        User user = suggestion.getUser();
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
