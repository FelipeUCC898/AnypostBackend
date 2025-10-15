package com.anypost.dto.suggestion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateSuggestionRequest {

    @NotBlank
    @Size(max = 2000)
    private String prompt;

    public CreateSuggestionRequest() { }

    public String getPrompt() { return prompt; }
    public void setPrompt(String prompt) { this.prompt = prompt; }
}