package com.example.leetscode.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.leetscode.model.Status;
import com.example.leetscode.service.StatusService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/v1/statuses")
public class StatusController {
    public final StatusService statusService;

    @Value("${judge0-url-status}")
    private URI judge0UrlStatus;

    @Value("${X-RapidAPI-Key}")
    private String X_RapidAPI_Key;

    @Value("${X-RapidAPI-Host}")
    private String X_RapidAPI_Host;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    public List<Status> getStatusFromJudge0() throws Exception {

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(judge0UrlStatus)
                .header("X-RapidAPI-Key", X_RapidAPI_Key)
                .header("X-RapidAPI-Host", X_RapidAPI_Host)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String jsonString = response.body();

        Status[] statuses = objectMapper.readValue(jsonString, Status[].class);

        return List.of(statuses);
    }

    @GetMapping("/")
    public ResponseEntity<List<Status>> getAllStatus() {
        return ResponseEntity.ok(statusService.getAllStatus());
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> addStatus(@RequestBody Status status) {
        try {
            if (status.getId() == null) {
                List<Status> statuses = getStatusFromJudge0();
                statusService.addStatuses(statuses);

                return ResponseEntity.ok("Statuses added successfully");

            } else if (statusService.getStatusById(status.getId()) != null) {
                return ResponseEntity.badRequest().body("Error: Status already exists");
            } else {
                statusService.addStatus(status);
                return ResponseEntity.ok("Status added successfully");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
