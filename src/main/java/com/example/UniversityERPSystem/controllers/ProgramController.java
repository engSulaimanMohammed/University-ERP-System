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
        return null;
    }


    @GetMapping("/getAll")
    public List<Program> getAllPrograms() {
        // Call getAllPrograms from ProgramService
        return null;
    }


    @GetMapping("/getById/{id}")
    public Program getById(@PathVariable Long id) {
        // Call getById from ProgramService
        return null;
    }


    @PutMapping("/update/{id}")
    public Program updateProgram(@PathVariable Long id,
                                 @RequestBody Program program) {

        // Call updateProgram from ProgramService
        return null;
    }



    @DeleteMapping("/delete/{id}")
    public Boolean deleteById(@PathVariable Long id) {
        // Call deleteById from ProgramService
        return null;
    }
}