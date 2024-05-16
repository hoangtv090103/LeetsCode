package com.example.leetscode.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.leetscode.model.Problem;
import com.example.leetscode.service.ProblemService;

@RestController
@RequestMapping("/api/v1/problems")
public class ProblemController {
    @Autowired
    private final ProblemService problemService;

    public ProblemController(ProblemService problemService) {
        this.problemService = problemService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Problem>> getAllProblems() {
        return ResponseEntity.ok(problemService.getAllProblems());
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public String addProblem(@RequestBody Problem problem) {
        problemService.addProblem(problem);
        return "Problem added successfully";
    }

    @PostMapping("/addMany")
    @ResponseStatus(HttpStatus.CREATED)
    public void addManyProblems(@RequestBody List<Problem> problems) {
        problemService.addManyProblems(problems);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Problem> updateProblem(@PathVariable Long id, @RequestBody Problem problem) {
        problemService.updateProblem(id, problem);
        return ResponseEntity.ok(problem);
    }
}