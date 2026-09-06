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
        return examService.addExam(
                exam,
                exam.getCourse()
        );
    }



    @GetMapping("/getAll")
    public List<Exam> getAllExams() {
        return examService.getAllExams();
    }



    @GetMapping("/getById/{id}")
    public Exam getById(@PathVariable Long id) {
        return examService.getById(id);
    }



    @PutMapping("/update/{id}")
    public Exam updateExam(@PathVariable Long id,
                           @RequestBody Exam exam) {
        return examService.updateExam(
                id,
                exam.getTitle(),
                exam.getExamDate(),
                exam.getTotalMarks(),
                exam.getCourse()
        );
    }



    @DeleteMapping("/delete/{id}")
    public Boolean deleteById(@PathVariable Long id) {
        return examService.deleteById(id);
    }
}
