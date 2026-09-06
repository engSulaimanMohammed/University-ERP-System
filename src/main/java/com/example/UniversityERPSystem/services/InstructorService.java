package com.example.UniversityERPSystem.services;


import com.example.UniversityERPSystem.entities.Department;
import com.example.UniversityERPSystem.entities.Instructor;
import com.example.UniversityERPSystem.repositories.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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




    public List<Instructor> getAllInstructors() {
        List<Instructor> instructors = instructorRepository.findAll();
        List<Instructor> activeInstructors = new ArrayList<>();
        for (Instructor instructor : instructors) {
            if (instructor.isActive()) {
                activeInstructors.add(instructor);
            }
        }
        return activeInstructors;
    }



    public Instructor getById(Long id) {
        Optional<Instructor> instructor = instructorRepository.findById(id);
        if (instructor.isPresent() && instructor.get().isActive()) {
            return instructor.get();
        }
        return null;
    }



    public Instructor updateInstructor(Long id, String name, String email,
                                       String phoneNumber, String specialization, Department department) {
        Instructor instructorToUpdate = getById(id);
        if (instructorToUpdate == null) {
            return null;
        }
        instructorToUpdate.setName(name);
        instructorToUpdate.setEmail(email);
        instructorToUpdate.setPhoneNumber(phoneNumber);
        instructorToUpdate.setSpecialization(specialization);
        instructorToUpdate.setDepartment(department);
        instructorToUpdate.setUpdatedDate(new Date());
        return instructorRepository.save(instructorToUpdate);
    }



    public Boolean deleteById(Long id) {
        Instructor instructorToDelete = getById(id);
        if (instructorToDelete == null) {
            return false;
        }
        instructorToDelete.setActive(false);
        instructorToDelete.setUpdatedDate(new Date());
        instructorRepository.save(instructorToDelete);
        return true;
    }
}
