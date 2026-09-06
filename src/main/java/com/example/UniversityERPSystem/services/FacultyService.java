package com.example.UniversityERPSystem.services;

import com.example.UniversityERPSystem.entities.Faculty;
import com.example.UniversityERPSystem.entities.University;
import com.example.UniversityERPSystem.repositories.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class FacultyService {


    private final FacultyRepository facultyRepository;
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    public Faculty addFaculty(Faculty faculty, University university) {
        if (faculty == null) {
            throw new IllegalArgumentException("Faculty cannot be null");
        }
        faculty.setActive(true);
        faculty.setCreatedDate(new Date());
        faculty.setUniversity(university);
        return facultyRepository.save(faculty);
    }




}
