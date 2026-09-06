package com.example.UniversityERPSystem.services;

import com.example.UniversityERPSystem.entities.University;
import com.example.UniversityERPSystem.repositories.UniversityRepository;
import org.springframework.stereotype.Service;

@Service
public class UniversityService {

    private final UniversityRepository universityRepository;

    public UniversityService(UniversityRepository universityRepository) {
        this.universityRepository = universityRepository;
    }

    public University addUniversity(University university) {
        if (university == null) {
            throw new IllegalArgumentException("University cannot be null");
        }
        return universityRepository.save(university);
    }
}