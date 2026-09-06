package com.example.UniversityERPSystem.services;

import com.example.UniversityERPSystem.entities.Faculty;
import com.example.UniversityERPSystem.entities.University;
import com.example.UniversityERPSystem.repositories.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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


    public List<Faculty> getAllFaculties() {
        List<Faculty> faculties = facultyRepository.findAll();
        List<Faculty> activeFaculties = new ArrayList<>();
        for (Faculty faculty : faculties) {
            if (faculty.isActive()) {
                activeFaculties.add(faculty);
            }
        }
        return activeFaculties;
    }



    public Faculty getById(Long id) {
        Optional<Faculty> faculty = facultyRepository.findById(id);
        if (faculty.isPresent() && faculty.get().isActive()) {
            return faculty.get();
        }
        return null;
    }


    public Faculty updateFaculty(Long id, String name, String description, University university) {
        Faculty facultyToUpdate = getById(id);
        if (facultyToUpdate == null) {
            return null;
        }
        facultyToUpdate.setName(name);
        facultyToUpdate.setDescription(description);
        facultyToUpdate.setUniversity(university);
        facultyToUpdate.setUpdatedDate(new Date());
        return facultyRepository.save(facultyToUpdate);
    }














}
