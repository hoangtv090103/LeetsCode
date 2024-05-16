package com.example.leetscode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.leetscode.model.Submission;
import com.example.leetscode.repository.SubmissionRepository;

@Service
public class SubmissionService {
    
    @Autowired
    private SubmissionRepository submissionRepository;

    public SubmissionService(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }
    
    public void addSubmission(Submission submission) {
        submissionRepository.save(submission);
    }

    public List<Submission> getAllSubmissions() {
        return submissionRepository.findAll();
    }

    public void updateSubmission(Long id, Submission submission) {
        Submission submissionToUpdate = submissionRepository.findById(id).get();
        submissionToUpdate.setLanguage(submission.getLanguage());
        submissionToUpdate.setStatus(submission.getStatus());
        submissionToUpdate.setSourceCode(submission.getSourceCode());
        submissionToUpdate.setUpdatedAt();
        submissionRepository.save(submissionToUpdate);
    }
}
