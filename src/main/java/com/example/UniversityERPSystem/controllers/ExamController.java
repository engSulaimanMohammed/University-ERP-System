package com.example.UniversityERPSystem.controllers;

import com.example.UniversityERPSystem.entities.Exam;
import com.example.UniversityERPSystem.services.ExamService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam")
public class ExamController {

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }


    @PostMapping("/add")
    public Exam addExam(@RequestBody Exam exam) {
        return null;
    }



    @GetMapping("/getAll")
    public List<Exam> getAllExams() {
        return null;
    }









}
