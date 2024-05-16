package com.example.leetscode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.leetscode.model.Problem;
import com.example.leetscode.model.TestCase;
import com.example.leetscode.repository.ProblemRepository;

import jakarta.transaction.Transactional;

@Service
public class ProblemService {

    @Autowired
    private ProblemRepository problemRepository;

    public ProblemService(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    @Transactional
    public Problem addProblem(Problem problem) {
        for (TestCase testCase : problem.getTestCases()) {
            testCase.setProblem(problem);
        }
        return problemRepository.save(problem);
    }

    public void updateProblem(Long id, Problem problem) {
        Problem problemToUpdate = problemRepository.findById(id).get();
        problemToUpdate.setTitle(problem.getTitle());
        problemToUpdate.setDescription(problem.getDescription());
        problemToUpdate.setDifficulty(problem.getDifficulty());
        problemToUpdate.setUpdatedAt();
        problemRepository.save(problemToUpdate);
    }

    public List<Problem> getAllProblems() {
        return problemRepository.findAll();
    }


    public void addManyProblems(List<Problem> problems) {
        problemRepository.saveAll(problems);
    }

}
