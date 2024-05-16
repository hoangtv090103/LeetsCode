package com.example.leetscode.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.leetscode.model.Status;
import com.example.leetscode.repository.StatusRepository;

@Service
public class StatusService {

    @Autowired
    private StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public List<Status> getAllStatus() {
        return statusRepository.findAll();
    }

    public void addStatus(Status status) {
        statusRepository.save(status);
    }

    public void addStatuses(List<Status> statuses) {
        statusRepository.saveAll(statuses);
    }

    public Object getStatusById(Long id) {
        return statusRepository.findById(id);
    }

}
