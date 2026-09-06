package com.example.UniversityERPSystem.services;

import com.example.UniversityERPSystem.entities.University;
import com.example.UniversityERPSystem.repositories.UniversityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        university.setActive(true);
        return universityRepository.save(university);
    }


    public List<University> getAllUniversities() {
        List<University> universities = universityRepository.findAll();
        List<University> activeUniversities = new ArrayList<>();
        for (University university : universities) {
            if (university.isActive()) {
                activeUniversities.add(university);
            }
        }
        return activeUniversities;
    }

}