package com.example.leetscode.controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.example.leetscode.model.Submission;
import com.example.leetscode.service.SubmissionService;

import java.util.Base64;

class Token {
    private String token;

    public Token() {
    }

    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

@RestController
@RequestMapping("/api/v1/submissions")
public class SubmissionController {

    public final SubmissionService submissionService;

    @Value("${judge0-url-submission}")
    private URI judge0UrlSubmission;

    @Value("${X-RapidAPI-Key}")
    private String X_RapidAPI_Key;

    @Value("${X-RapidAPI-Host}")
    private String X_RapidAPI_Host;

    HttpClient client = HttpClient.newHttpClient();

    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    public Submission getSubmissionFromJudge0(Submission submission) throws IOException, InterruptedException {
        String sourceCode = submission.getSourceCode();

        Long languageId = submission.getLanguageId();
        String stdin = submission.getStdin();

        // String base64SourceCode =
        // java.util.Base64.getEncoder().encodeToString(sourceCode.getBytes());
        // String base64Stdin =
        // java.util.Base64.getEncoder().encodeToString(stdin.getBytes());

        String submissionString = "{ \"source_code\": \"" + sourceCode + "\", \"language_id\": " + languageId
                + ", \"stdin\": \"" + stdin + "\" }";

        URI judge0UrlSubmission = URI.create(this.judge0UrlSubmission.toString() + "?base64_encoded=true&fields=*");

        HttpRequest request = HttpRequest.newBuilder()
                .uri(judge0UrlSubmission)
                .header("Content-Type", "application/json")
                .header("X-RapidAPI-Key", X_RapidAPI_Key)
                .header("X-RapidAPI-Host", X_RapidAPI_Host)
                .POST(HttpRequest.BodyPublishers.ofString(submissionString))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String res = response.body();

        ObjectMapper objectMapper = new ObjectMapper();
        Token tokenObject = objectMapper.readValue(res, Token.class);
        String token = tokenObject.getToken();

        URI judge0UrlSubmissionToken = URI
                .create(this.judge0UrlSubmission + "/" + token + "?base64_encoded=false"
                        + "&fields=stdout,stderr,status_id,language_id");

        request = HttpRequest.newBuilder()
                .uri(judge0UrlSubmissionToken)
                .header("X-RapidAPI-Key", X_RapidAPI_Key)
                .header("X-RapidAPI-Host", X_RapidAPI_Host)
                .build();

        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String jsonString = response.body();
        jsonString = jsonString.replace("source_code", "sourceCode");
        jsonString = jsonString.replace("status_id", "statusId");
        jsonString = jsonString.replace("language_id", "languageId");
        objectMapper = new ObjectMapper();

        Submission newSubmission = objectMapper.readValue(jsonString, Submission.class);
        newSubmission.setSourceCode(new String(Base64.getDecoder().decode(sourceCode)));
        newSubmission.setToken(token);
        return newSubmission;
    }

    @GetMapping("/")
    public ResponseEntity<List<Submission>> getAllSubmissions() {
        return ResponseEntity.ok(submissionService.getAllSubmissions());
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Submission> addSubmission(@RequestBody Submission submission) {
        // TODO: Need to change attribute data type of token in Submission model
        // language -> languageId
        // status -> statusId
        try {
            Submission submissionFromJudge0 = getSubmissionFromJudge0(submission);
            submissionService.addSubmission(submissionFromJudge0);
            return ResponseEntity.ok(submissionFromJudge0);
        } catch (IOException | InterruptedException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
}
