package com.example.UniversityERPSystem.services;

import com.example.UniversityERPSystem.repositories.FacultyRepository;
import org.springframework.stereotype.Service;

@Service
public class FacultyService {


    private final FacultyRepository facultyRepository;
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


}
