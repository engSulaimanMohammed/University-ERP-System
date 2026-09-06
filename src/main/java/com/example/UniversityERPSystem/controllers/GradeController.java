package com.example.UniversityERPSystem.controllers;

import com.example.UniversityERPSystem.entities.Grade;
import com.example.UniversityERPSystem.services.GradeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grade")
public class GradeController {

    private final GradeService gradeService;

    public GradeController(GradeService gradeService) {
        this.gradeService = gradeService;
    }


    @PostMapping("/add")
    public Grade addGrade(@RequestBody Grade grade) {
        return gradeService.addGrade(
                grade,
                grade.getEnrollment(),
                grade.getExam()
        );
    }


    @GetMapping("/getAll")
    public List<Grade> getAllGrades() {
        return gradeService.getAllGrades();
    }


    @GetMapping("/getById/{id}")
    public Grade getById(@PathVariable Long id) {
        return gradeService.getById(id);
    }



    @PutMapping("/update/{id}")
    public Grade updateGrade(@PathVariable Long id,
                             @RequestBody Grade grade) {
        return gradeService.updateGrade(
                id,
                grade.getScore(),
                grade.getLetterGrade(),
                grade.getEnrollment(),
                grade.getExam()
        );    }


    @DeleteMapping("/delete/{id}")
    public Boolean deleteById(@PathVariable Long id) {
        return gradeService.deleteById(id);
    }
}