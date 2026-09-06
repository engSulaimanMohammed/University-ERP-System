package com.example.UniversityERPSystem.services;



import com.example.UniversityERPSystem.repositories.GuardianRepository;
import org.springframework.stereotype.Service;

@Service
public class GuardianService {

    private final GuardianRepository guardianRepository;

    public GuardianService(GuardianRepository guardianRepository) {
        this.guardianRepository = guardianRepository;
    }
}
