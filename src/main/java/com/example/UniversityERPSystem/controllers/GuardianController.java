package com.example.UniversityERPSystem.controllers;

import com.example.UniversityERPSystem.entities.Guardian;
import com.example.UniversityERPSystem.services.GuardianService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/guardian")
public class GuardianController {

    private final GuardianService guardianService;

    public GuardianController(GuardianService guardianService) {
        this.guardianService = guardianService;
    }


    @PostMapping("/add")
    public Guardian addGuardian(@RequestBody Guardian guardian) {
        return null;
    }


    @GetMapping("/getAll")
    public List<Guardian> getAllGuardians() {
        return null;
    }







}

