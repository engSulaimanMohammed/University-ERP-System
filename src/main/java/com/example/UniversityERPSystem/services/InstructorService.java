package com.example.UniversityERPSystem.services;


import com.example.UniversityERPSystem.entities.Department;
import com.example.UniversityERPSystem.entities.Instructor;
import com.example.UniversityERPSystem.repositories.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;

    public InstructorService(InstructorRepository instructorRepository) {
        this.instructorRepository = instructorRepository;
    }



    public Instructor addInstructor(Instructor instructor, Department department) {
        if (instructor == null) {
            throw new IllegalArgumentException("Instructor cannot be null");
        }
        instructor.setActive(true);
        instructor.setCreatedDate(new Date());
        instructor.setDepartment(department);
        return instructorRepository.save(instructor);
    }







}
