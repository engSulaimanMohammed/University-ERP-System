package com.example.UniversityERPSystem.services;



import com.example.UniversityERPSystem.entities.Guardian;
import com.example.UniversityERPSystem.entities.Student;
import com.example.UniversityERPSystem.repositories.GuardianRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GuardianService {

    private final GuardianRepository guardianRepository;

    public GuardianService(GuardianRepository guardianRepository) {
        this.guardianRepository = guardianRepository;
    }



    public Guardian addGuardian(Guardian guardian, Student student) {
        if (guardian == null) {
            throw new IllegalArgumentException("Guardian cannot be null");
        }
        guardian.setActive(true);
        guardian.setCreatedDate(new Date());
        guardian.setStudent(student);
        return guardianRepository.save(guardian);
    }









}
