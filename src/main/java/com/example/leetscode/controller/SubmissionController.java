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

    public String getToken(Submission submission) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(judge0UrlSubmission)
                .header("Content-Type", "application/json")
                .header("X-RapidAPI-Key", X_RapidAPI_Key)
                .header("X-RapidAPI-Host", X_RapidAPI_Host)
                .POST(HttpRequest.BodyPublishers.ofString(submission.toString()))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String token = response.body();
        return token;
    }

    public Submission getSubmissionFromJudge0(String token) throws IOException, InterruptedException {
        URI judge0UrlSubmissionToken = URI
                .create(judge0UrlSubmission + "/" + token + "?base64_encoded=true" + "&fields=*");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(judge0UrlSubmissionToken)
                .header("X-RapidAPI-Key", X_RapidAPI_Key)
                .header("X-RapidAPI-Host", X_RapidAPI_Host)
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String jsonString = response.body();

        ObjectMapper objectMapper = new ObjectMapper();

        Submission submission = objectMapper.readValue(jsonString, Submission.class);

        return submission;
    }

    @GetMapping("/")
    public ResponseEntity<List<Submission>> getAllSubmissions() {
        return ResponseEntity.ok(submissionService.getAllSubmissions());
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Submission> addSubmission(@RequestBody Submission submission) {
        // TODO: Need to  change attribute data type of token in Submission model
        // language -> languageId
        // status -> statusId
        String submissionToken = null;
        try {
            submissionToken = getToken(submission);
            submission.setToken(submissionToken);

            Submission submissionFromJudge0 = getSubmissionFromJudge0(submissionToken);

            if (submissionFromJudge0.getStatus().getId() != null) {
                submissionService.updateSubmission(submission.getId(), submissionFromJudge0);
                return ResponseEntity.ok(submission);
            } else {
                submissionService.addSubmission(submission);
                return ResponseEntity.ok(submission);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

}
