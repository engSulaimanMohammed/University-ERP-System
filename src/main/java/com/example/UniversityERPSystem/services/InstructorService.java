package com.example.UniversityERPSystem.services;


import com.example.UniversityERPSystem.repositories.InstructorRepository;
import org.springframework.stereotype.Service;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }



}
