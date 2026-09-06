package com.example.UniversityERPSystem.services;

import com.example.UniversityERPSystem.entities.Guardian;
import com.example.UniversityERPSystem.entities.Student;
import com.example.UniversityERPSystem.exceptions.ResourceNotFoundException;
import com.example.UniversityERPSystem.repositories.GuardianRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class GuardianService {

    private final GuardianRepository guardianRepository;
    private final StudentService studentService;


    // Constructor Injection.
    public GuardianService(GuardianRepository guardianRepository,
                           StudentService studentService) {

        this.guardianRepository = guardianRepository;
        this.studentService = studentService;
    }


    // Validate Guardian fields.
    private void validateGuardianData(String name,
                                      String relationship,
                                      String phoneNumber) {

        // Validate Guardian name.
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Guardian name cannot be blank"
            );
        }

        if (name.length() > 255) {
            throw new IllegalArgumentException(
                    "Guardian name cannot exceed 255 characters"
            );
        }


        // Validate relationship.
        if (relationship == null || relationship.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Guardian relationship cannot be blank"
            );
        }

        if (relationship.length() > 255) {
            throw new IllegalArgumentException(
                    "Guardian relationship cannot exceed 255 characters"
            );
        }


        // Validate phone number.
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Guardian phone number cannot be blank"
            );
        }

        if (phoneNumber.length() > 255) {
            throw new IllegalArgumentException(
                    "Guardian phone number cannot exceed 255 characters"
            );
        }
    }


    // Add a new Guardian.
    public Guardian addGuardian(Guardian guardian,
                                Long studentId) {

        if (guardian == null) {
            throw new IllegalArgumentException(
                    "Guardian cannot be null"
            );
        }

        // Validate Guardian data.
        validateGuardianData(
                guardian.getName(),
                guardian.getRelationship(),
                guardian.getPhoneNumber()
        );

        // Get active Student.
        Student student = studentService.getById(studentId);

        // Set Student relationship.
        guardian.setStudent(student);

        // Set BaseClass fields.
        guardian.setActive(true);
        guardian.setCreatedDate(new Date());

        // Save Guardian.
        return guardianRepository.save(guardian);
    }


    // Get all active Guardians.
    public List<Guardian> getAllGuardians() {

        return guardianRepository.findAll()
                .stream()
                .filter(Guardian::isActive)
                .toList();
    }


    // Get active Guardian by ID.
    public Guardian getById(Long id) {

        // Validate Guardian ID.
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(
                    "Guardian ID must be greater than zero"
            );
        }

        // Find Guardian.
        Guardian guardian = guardianRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Guardian not found with id: " + id
                        )
                );

        // Do not return Soft Deleted Guardian.
        if (!guardian.isActive()) {
            throw new ResourceNotFoundException(
                    "Guardian not found with id: " + id
            );
        }

        return guardian;
    }


    // Update an existing Guardian.
    public Guardian updateGuardian(Long id,
                                   String name,
                                   String relationship,
                                   String phoneNumber,
                                   Long studentId) {

        // Validate new Guardian data.
        validateGuardianData(
                name,
                relationship,
                phoneNumber
        );

        // Get active Guardian.
        Guardian guardianToUpdate = getById(id);

        // Get active Student.
        Student student = studentService.getById(studentId);

        // Update Guardian fields.
        guardianToUpdate.setName(name);
        guardianToUpdate.setRelationship(relationship);
        guardianToUpdate.setPhoneNumber(phoneNumber);

        // Update Student relationship.
        guardianToUpdate.setStudent(student);

        // Update modification date.
        guardianToUpdate.setUpdatedDate(new Date());

        // Save updated Guardian.
        return guardianRepository.save(guardianToUpdate);
    }


    // Soft delete Guardian by ID.
    public Boolean deleteById(Long id) {

        // Get active Guardian.
        Guardian guardianToDelete = getById(id);

        // Soft Delete.
        guardianToDelete.setActive(false);

        // Update modification date.
        guardianToDelete.setUpdatedDate(new Date());

        // Save changes.
        guardianRepository.save(guardianToDelete);

        return true;
    }
}