package com.example.UniversityERPSystem.services;
import com.example.UniversityERPSystem.entities.Department;
import com.example.UniversityERPSystem.entities.Program;
import com.example.UniversityERPSystem.repositories.ProgramRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class ProgramService {

    private final ProgramRepository programRepository;

    public ProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }



    public Program addProgram(Program program, Department department) {
        if (program == null) {
            throw new IllegalArgumentException("Program cannot be null");
        }
        program.setActive(true);
        program.setCreatedDate(new Date());
        program.setDepartment(department);
        return programRepository.save(program);
    }



    public List<Program> getAllPrograms() {
        List<Program> programs = programRepository.findAll();
        List<Program> activePrograms = new ArrayList<>();
        for (Program program : programs) {
            if (program.isActive()) {
                activePrograms.add(program);
            }
        }
        return activePrograms;
    }












}
