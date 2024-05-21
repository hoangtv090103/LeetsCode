package com.example.leetscode.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Base64;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.leetscode.model.Submission;
import com.example.leetscode.service.SubmissionService;

@RestController
@RequestMapping("/api/v1/submissions")
public class SubmissionController {

    private final SubmissionService submissionService;
    private final HttpClient client;
    private final ObjectMapper objectMapper;

    @Value("${judge0-url-submission}")
    private URI judge0UrlSubmission;

    @Value("${X-RapidAPI-Key}")
    private String X_RapidAPI_Key;

    @Value("${X-RapidAPI-Host}")
    private String X_RapidAPI_Host;

    public String decodeBase64(String str) {
        if (str == null) {
            return "";
        }
        return new String(Base64.getDecoder().decode(str));
    }

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
        this.client = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public CompletableFuture<String> getSubmissionToken(Object submission) {
        try {
            URI judge0UrlSubmission = URI.create(this.judge0UrlSubmission + "?base64_encoded=true");

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(judge0UrlSubmission)
                    .header("Content-Type", "application/json")
                    .header("X-RapidAPI-Key", X_RapidAPI_Key)
                    .header("X-RapidAPI-Host", X_RapidAPI_Host)
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(submission)))
                    .build();

            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenApply(res -> {
                        try {
                            Object obj = objectMapper.readValue(res, Object.class);
                            // Get the token from the Object obj
                            return (String) ((Map<String, Object>) obj).get("token");
                        } catch (IOException e) {
                            throw new RuntimeException("Error processing response JSON", e);
                        }
                    });
        } catch (Exception e) {
            CompletableFuture<String> future = new CompletableFuture<>();
            future.completeExceptionally(e);
            return future;
        }
    }

    @GetMapping("/{id}")
    public CompletableFuture<ResponseEntity<? extends Object>> getAllSubmissions(@PathVariable String id) {
        try {
            String token;
            if (id.matches("\\d+")) {
                Long idLong = Long.parseLong(id);
                token = submissionService.getSubmissionById(idLong).getToken();
            } else {
                token = id;
            }

            URI judge0UrlSubmission = URI
                    .create(this.judge0UrlSubmission.toString() + "/" + token + "?base64_encoded=false&fields=*");

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(judge0UrlSubmission)
                    .header("Content-Type", "application/json")
                    .header("X-RapidAPI-Key", X_RapidAPI_Key)
                    .header("X-RapidAPI-Host", X_RapidAPI_Host)
                    .GET()
                    .build();

            return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenApply(res -> {
                        try {
                            Object obj = objectMapper.readValue(res, Object.class);
                            return ResponseEntity.ok(obj);
                        } catch (IOException e) {
                            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                    .body("Error processing response JSON");
                        }
                    })
                    .exceptionally(e -> {
                        System.err.println("Error while making HTTP request: " + e.getMessage());
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body("Error while making HTTP request");
                    });
        } catch (NumberFormatException e) {
            return CompletableFuture
                    .completedFuture(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid ID format"));
        }
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addSubmission(@RequestBody Object submission) {
        try {
            CompletableFuture<String> token = getSubmissionToken(submission);
            token.thenAccept(tokenStr ->
                submissionService.addSubmission(new Submission(null, null, tokenStr))
            );
            
            return ResponseEntity.status(HttpStatus.CREATED).body(token.get());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

class Token {
    private String token;

    public Token() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}