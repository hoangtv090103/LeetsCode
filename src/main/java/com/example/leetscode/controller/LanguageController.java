package com.example.leetscode.controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/v1/languages")
public class LanguageController {
    @Value("${judge0-url-language}")
    private URI judge0UrlLanguage;

    @Value("${X-RapidAPI-Key}")
    private String X_RapidAPI_Key;

    @Value("${X-RapidAPI-Host}")
    private String X_RapidAPI_Host;

    private final HttpClient client;
    private final ObjectMapper objectMapper;

    public LanguageController() {
        this.client = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    @GetMapping("/")
    @CrossOrigin(origins = "http://localhost:3000")
    public CompletableFuture<ResponseEntity<? extends Object>> getLanguages() throws Exception {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(judge0UrlLanguage)
                    .header("Content-Type", "application/json")
                    .header("X-RapidAPI-Key", X_RapidAPI_Key)
                    .header("X-RapidAPI-Host", X_RapidAPI_Host)
                    .GET()
                    .build();

            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenApply(res -> {
                        try {
                            @SuppressWarnings("unchecked")
                            List<Object> languages = objectMapper.readValue(res, List.class);
                            return ResponseEntity.ok(languages);
                        } catch (Exception e) {
                            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
                        }
                    });
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("Error: " + e.getMessage()));
        }
    }

    // Get by id
    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<? extends Object>> getLanguageById(@PathVariable Long id) {
        try {
            URI judge0UrlLanguage = URI.create(this.judge0UrlLanguage + "/" + id);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(judge0UrlLanguage)
                    .header("Content-Type", "application/json")
                    .header("X-RapidAPI-Key", X_RapidAPI_Key)
                    .header("X-RapidAPI-Host", X_RapidAPI_Host)
                    .GET()
                    .build();

            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenApply(res -> {
                        try {
                            Object language = objectMapper.readValue(res, Object.class);
                            return ResponseEntity.ok(language);
                        } catch (Exception e) {
                            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
                        }
                    });
        } catch (Exception e) {
            return CompletableFuture.completedFuture(ResponseEntity.badRequest().body("Error: " + e.getMessage()));
        }
    }
}
