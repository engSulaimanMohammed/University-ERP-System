package com.example.UniversityERPSystem.services;



import com.example.UniversityERPSystem.entities.Enrollment;
import com.example.UniversityERPSystem.entities.Exam;
import com.example.UniversityERPSystem.entities.Grade;
import com.example.UniversityERPSystem.repositories.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class GradeService {

    private final GradeRepository gradeRepository;

    public GradeService(GradeRepository gradeRepository) {
        this.gradeRepository = gradeRepository;
    }




    public Grade addGrade(Grade grade, Enrollment enrollment, Exam exam) {
        if (grade == null) {
            throw new IllegalArgumentException("Grade cannot be null");
        }
        grade.setActive(true);
        grade.setCreatedDate(new Date());
        grade.setEnrollment(enrollment);
        grade.setExam(exam);
        return gradeRepository.save(grade);
    }








}

