package com.example.UniversityERPSystem.services;


import com.example.UniversityERPSystem.entities.Course;
import com.example.UniversityERPSystem.entities.Exam;
import com.example.UniversityERPSystem.repositories.ExamRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ExamService {

    private final ExamRepository examRepository;

    public ExamService(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }




    public Exam addExam(Exam exam, Course course) {
        if (exam == null) {
            throw new IllegalArgumentException("Exam cannot be null");
        }
        exam.setActive(true);
        exam.setCreatedDate(new Date());
        exam.setCourse(course);
        return examRepository.save(exam);
    }








}
