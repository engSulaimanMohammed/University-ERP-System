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
        return guardianService.addGuardian(
                guardian,
                guardian.getStudent()
        );
    }


    @GetMapping("/getAll")
    public List<Guardian> getAllGuardians() {
        return guardianService.getAllGuardians();
    }


    @GetMapping("/getById/{id}")
    public Guardian getById(@PathVariable Long id) {
        return guardianService.getById(id);
    }


    @PutMapping("/update/{id}")
    public Guardian updateGuardian(@PathVariable Long id,
                                   @RequestBody Guardian guardian) {
        return guardianService.updateGuardian(
                id,
                guardian.getName(),
                guardian.getRelationship(),
                guardian.getPhoneNumber(),
                guardian.getStudent()
        );
    }


    @DeleteMapping("/delete/{id}")
    public Boolean deleteById(@PathVariable Long id) {
        return guardianService.deleteById(id);
    }
}

