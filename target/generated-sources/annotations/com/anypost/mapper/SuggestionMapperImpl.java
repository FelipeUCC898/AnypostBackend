package com.anypost.mapper;

import com.anypost.domain.model.Suggestion;
import com.anypost.domain.model.User;
import com.anypost.dto.suggestion.CreateSuggestionRequest;
import com.anypost.dto.suggestion.SuggestionResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-10-15T12:25:10-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class SuggestionMapperImpl implements SuggestionMapper {

    @Override
    public Suggestion fromCreate(CreateSuggestionRequest req, User user) {
        if ( req == null && user == null ) {
            return null;
        }

        Suggestion suggestion = new Suggestion();

        if ( req != null ) {
            suggestion.setPrompt( req.getPrompt() );
        }
        if ( user != null ) {
            suggestion.setUser( user );
            suggestion.setUpdatedAt( user.getUpdatedAt() );
        }

        setAudit( suggestion );

        return suggestion;
    }

    @Override
    public SuggestionResponse toResponse(Suggestion suggestion) {
        if ( suggestion == null ) {
            return null;
        }

        SuggestionResponse suggestionResponse = new SuggestionResponse();

        suggestionResponse.setId( suggestion.getId() );
        suggestionResponse.setPrompt( suggestion.getPrompt() );
        suggestionResponse.setResultMediaUrl( suggestion.getResultMediaUrl() );
        suggestionResponse.setResultType( suggestion.getResultType() );
        suggestionResponse.setCreatedAt( suggestion.getCreatedAt() );

        return suggestionResponse;
    }
}
