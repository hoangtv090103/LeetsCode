package com.example.leetscode.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.KeyStore.Entry;
import java.util.Base64;
import java.util.List;
import java.util.Map;

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

    public String getSubmissionToken(Object submission) throws IOException, InterruptedException {
        String token = "";

        URI judge0UrlSubmission = URI.create(this.judge0UrlSubmission.toString() + "?base64_encoded=true");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(judge0UrlSubmission)
                .header("Content-Type", "application/json")
                .header("X-RapidAPI-Key", X_RapidAPI_Key)
                .header("X-RapidAPI-Host", X_RapidAPI_Host)
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(submission)))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String res = response.body();

        Object obj = objectMapper.readValue(res, Object.class);

        // Get the token from the Object obj
        for (Map.Entry<String, Object> entry : ((java.util.Map<String, Object>) obj).entrySet()) {
            if (entry.getKey().equals("token")) {
                token = (String) entry.getValue();
            }
        }

        return token;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getAllSubmissions(@PathVariable String id) throws IOException, InterruptedException {
        String token = "";
        if (id.matches("\\d+")) {
            Long idLong = Long.parseLong(id);
            token = submissionService.getSubmissionById(idLong).getToken();
        } else {
            token = id;
        }

        URI judge0UrlSubmission = URI
                .create(this.judge0UrlSubmission.toString() + "/" + token + "?base64_encoded=false&fields=*");

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(judge0UrlSubmission)
                .header("Content-Type", "application/json")
                .header("X-RapidAPI-Key", X_RapidAPI_Key)
                .header("X-RapidAPI-Host", X_RapidAPI_Host)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String res = response.body();

        // Convert the response to json
        Object obj = objectMapper.readValue(res, Object.class);

        return ResponseEntity.ok(obj);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addSubmission(@RequestBody Object submission) {
        try {
            String token = getSubmissionToken(submission);
            submissionService.addSubmission(new Submission(null, null, token));

            return ResponseEntity.ok(token);
        } catch (IOException | InterruptedException e) {
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