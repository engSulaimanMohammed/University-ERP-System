package com.example.UniversityERPSystem.services;


import com.example.UniversityERPSystem.repositories.ExamRepository;
import org.springframework.stereotype.Service;

@Service
public class ExamService {

    private final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }




}
