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




}
