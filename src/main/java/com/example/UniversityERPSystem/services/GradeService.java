package com.example.UniversityERPSystem.services;



import com.example.UniversityERPSystem.repositories.GradeRepository;
import org.springframework.stereotype.Service;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;

    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }




}

