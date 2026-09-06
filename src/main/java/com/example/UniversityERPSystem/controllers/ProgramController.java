package com.example.UniversityERPSystem.controllers;

import com.example.UniversityERPSystem.entities.Program;
import com.example.UniversityERPSystem.services.ProgramService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/program")
public class ProgramController {

    private final ProgramService programService;

    public ProgramController(ProgramService programService) {
        this.programService = programService;
    }


    @PostMapping("/add")
    public Program addProgram(@RequestBody Program program) {
        // Call addProgram from ProgramService
        return programService.addProgram(
                program,
                program.getDepartment()
        );
    }


    @GetMapping("/getAll")
    public List<Program> getAllPrograms() {
        // Call getAllPrograms from ProgramService
        return programService.getAllPrograms();
    }


    @GetMapping("/getById/{id}")
    public Program getById(@PathVariable Long id) {
        // Call getById from ProgramService
        return programService.getById(id);
    }


    @PutMapping("/update/{id}")
    public Program updateProgram(@PathVariable Long id,
                                 @RequestBody Program program) {

        // Call updateProgram from ProgramService
        return programService.updateProgram(
                id,
                program.getName(),
                program.getDegreeLevel(),
                program.getDurationYears(),
                program.getDepartment()
        );    }



    @DeleteMapping("/delete/{id}")
    public Boolean deleteById(@PathVariable Long id) {
        // Call deleteById from ProgramService
        return programService.deleteById(id);
    }
}