package com.example.UniversityERPSystem.services;

import com.example.UniversityERPSystem.entities.University;
import com.example.UniversityERPSystem.repositories.UniversityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        university.setCreatedDate(new Date());
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


    public University getById(Long id) {
        Optional<University> university = universityRepository.findById(id);
        if (university.isPresent() && university.get().isActive()) {
            return university.get();
        }
        return null;
    }


    public University updateUniversity(Long id, String name, String location) {
        University universityToUpdate = getById(id);
        if (universityToUpdate == null) {
            return null;
        }
        universityToUpdate.setName(name);
        universityToUpdate.setLocation(location);
        universityToUpdate.setUpdatedDate(new Date());
        return universityRepository.save(universityToUpdate);
    }


    public Boolean deleteById(Long id) {
        University universityToDelete = getById(id);
        if (universityToDelete == null) {
            return false;
        }
        universityToDelete.setActive(false);
        universityToDelete.setUpdatedDate(new Date());
        universityRepository.save(universityToDelete);
        return true;
    }


    // Validate University fields.
    private void validateUniversityData(String name, String location) {

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("University name cannot be blank");
        }

        if (name.length() > 255) {
            throw new IllegalArgumentException(
                    "University name cannot exceed 255 characters"
            );
        }

        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException("University location cannot be blank");
        }

        if (location.length() > 255) {
            throw new IllegalArgumentException(
                    "University location cannot exceed 255 characters"
            );
        }
    }
}