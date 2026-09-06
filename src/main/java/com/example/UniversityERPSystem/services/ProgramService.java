package com.example.UniversityERPSystem.services;
import com.example.UniversityERPSystem.entities.Department;
import com.example.UniversityERPSystem.entities.Program;
import com.example.UniversityERPSystem.repositories.ProgramRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


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


    public Program getById(Long id) {
        Optional<Program> program = programRepository.findById(id);
        if (program.isPresent() && program.get().isActive()) {
            return program.get();
        }
        return null;
    }




    public Program updateProgram(Long id, String name, String degreeLevel,
                                 int durationYears, Department department) {
        Program programToUpdate = getById(id);
        if (programToUpdate == null) {
            return null;
        }
        programToUpdate.setName(name);
        programToUpdate.setDegreeLevel(degreeLevel);
        programToUpdate.setDurationYears(durationYears);
        programToUpdate.setDepartment(department);
        programToUpdate.setUpdatedDate(new Date());
        return programRepository.save(programToUpdate);
    }


    public Boolean deleteById(Long id) {
        Program programToDelete = getById(id);
        if (programToDelete == null) {
            return false;
        }
        programToDelete.setActive(false);
        programToDelete.setUpdatedDate(new Date());
        programRepository.save(programToDelete);
        return true;
    }
}
