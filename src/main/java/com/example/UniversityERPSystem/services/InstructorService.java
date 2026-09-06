package com.example.UniversityERPSystem.services;

import com.example.UniversityERPSystem.entities.Department;
import com.example.UniversityERPSystem.entities.Instructor;
import com.example.UniversityERPSystem.exceptions.ResourceNotFoundException;
import com.example.UniversityERPSystem.repositories.CourseRepository;
import com.example.UniversityERPSystem.repositories.InstructorRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InstructorService {

    private final InstructorRepository instructorRepository;
    private final DepartmentService departmentService;
    private final CourseRepository courseRepository;


    // Constructor Injection.
    public InstructorService(InstructorRepository instructorRepository,
                             DepartmentService departmentService,
                             CourseRepository courseRepository) {

        this.instructorRepository = instructorRepository;
        this.departmentService = departmentService;
        this.courseRepository = courseRepository;
    }


    // Validate Instructor fields.
    private void validateInstructorData(String name,
                                        String email,
                                        String phoneNumber,
                                        String specialization) {

        // Validate Instructor name.
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Instructor name cannot be blank"
            );
        }

        if (name.length() > 255) {
            throw new IllegalArgumentException(
                    "Instructor name cannot exceed 255 characters"
            );
        }


        // Validate Instructor email.
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Instructor email cannot be blank"
            );
        }

        if (email.length() > 255) {
            throw new IllegalArgumentException(
                    "Instructor email cannot exceed 255 characters"
            );
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new IllegalArgumentException(
                    "Instructor email must be valid"
            );
        }


        // Validate Instructor phone number.
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Instructor phone number cannot be blank"
            );
        }

        if (phoneNumber.length() > 255) {
            throw new IllegalArgumentException(
                    "Instructor phone number cannot exceed 255 characters"
            );
        }


        // Validate specialization.
        if (specialization == null || specialization.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Instructor specialization cannot be blank"
            );
        }

        if (specialization.length() > 255) {
            throw new IllegalArgumentException(
                    "Instructor specialization cannot exceed 255 characters"
            );
        }
    }


    // Add a new Instructor.
    public Instructor addInstructor(Instructor instructor,
                                    Long departmentId) {

        if (instructor == null) {
            throw new IllegalArgumentException(
                    "Instructor cannot be null"
            );
        }

        // Validate Instructor data.
        validateInstructorData(
                instructor.getName(),
                instructor.getEmail(),
                instructor.getPhoneNumber(),
                instructor.getSpecialization()
        );

        // Get active Department.
        Department department =
                departmentService.getById(departmentId);

        // Set relationship.
        instructor.setDepartment(department);

        // Set BaseClass fields.
        instructor.setActive(true);
        instructor.setCreatedDate(new Date());

        // Save Instructor.
        return instructorRepository.save(instructor);
    }


    // Get all active Instructors.
    public List<Instructor> getAllInstructors() {

        return instructorRepository.findAll()
                .stream()
                .filter(Instructor::isActive)
                .toList();
    }


    // Get active Instructor by ID.
    public Instructor getById(Long id) {

        // Validate ID.
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(
                    "Instructor ID must be greater than zero"
            );
        }

        // Find Instructor.
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Instructor not found with id: " + id
                        )
                );

        // Do not return soft-deleted Instructor.
        if (!instructor.isActive()) {
            throw new ResourceNotFoundException(
                    "Instructor not found with id: " + id
            );
        }

        return instructor;
    }


    // Update an existing Instructor.
    public Instructor updateInstructor(Long id,
                                       String name,
                                       String email,
                                       String phoneNumber,
                                       String specialization,
                                       Long departmentId) {

        // Validate new Instructor data.
        validateInstructorData(
                name,
                email,
                phoneNumber,
                specialization
        );

        // Get active Instructor.
        Instructor instructorToUpdate = getById(id);

        // Get active Department.
        Department department =
                departmentService.getById(departmentId);

        // Update fields.
        instructorToUpdate.setName(name);
        instructorToUpdate.setEmail(email);
        instructorToUpdate.setPhoneNumber(phoneNumber);
        instructorToUpdate.setSpecialization(specialization);

        // Update relationship.
        instructorToUpdate.setDepartment(department);

        // Update modification date.
        instructorToUpdate.setUpdatedDate(new Date());

        // Save updated Instructor.
        return instructorRepository.save(instructorToUpdate);
    }


    // Get total number of active Courses taught by Instructor.
    public int getTotalCoursesTaught(Long instructorId) {

        // Make sure Instructor exists and is active.
        getById(instructorId);

        return courseRepository
                .findActiveCoursesByInstructor(instructorId)
                .size();
    }


    // Soft delete Instructor by ID.
    public Boolean deleteById(Long id) {

        // Get active Instructor.
        Instructor instructorToDelete = getById(id);

        // Soft Delete.
        instructorToDelete.setActive(false);

        // Update modification date.
        instructorToDelete.setUpdatedDate(new Date());

        // Save changes.
        instructorRepository.save(instructorToDelete);

        return true;
    }
}