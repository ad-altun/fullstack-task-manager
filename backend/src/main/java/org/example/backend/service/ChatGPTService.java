package org.example.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

// ToDo: add spell check functionality

@Service
public class ChatGPTService {

    private final RestClient restClient;

    public ChatGPTService(RestClient.Builder restClientBuilder,
                          @Value("${API_KEY}") String apiKey) {
        this.restClient = restClientBuilder
                .baseUrl("https://api.openai.com/v1/chat/completions")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
    }
}