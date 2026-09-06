package com.example.UniversityERPSystem.services;
import com.example.UniversityERPSystem.repositories.ProgramRepository;
import org.springframework.stereotype.Service;



@Service
public class ProgramService {

    private final ProgramRepository programRepository;

    public ProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }



}
