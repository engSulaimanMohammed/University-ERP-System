package com.example.UniversityERPSystem.services;



import com.example.UniversityERPSystem.entities.Enrollment;
import com.example.UniversityERPSystem.entities.Exam;
import com.example.UniversityERPSystem.entities.Grade;
import com.example.UniversityERPSystem.repositories.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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




    public List<Grade> getAllGrades() {
        List<Grade> grades = gradeRepository.findAll();
        List<Grade> activeGrades = new ArrayList<>();
        for (Grade grade : grades) {
            if (grade.isActive()) {
                activeGrades.add(grade);
            }
        }
        return activeGrades;
    }




    public Grade getById(Long id) {
        Optional<Grade> grade = gradeRepository.findById(id);
        if (grade.isPresent() && grade.get().isActive()) {
            return grade.get();
        }
        return null;
    }




    public Grade updateGrade(Long id, double score,
                             String letterGrade, Enrollment enrollment, Exam exam) {
        Grade gradeToUpdate = getById(id);
        if (gradeToUpdate == null) {
            return null;
        }
        gradeToUpdate.setScore(score);
        gradeToUpdate.setLetterGrade(letterGrade);
        gradeToUpdate.setEnrollment(enrollment);
        gradeToUpdate.setExam(exam);
        gradeToUpdate.setUpdatedDate(new Date());
        return gradeRepository.save(gradeToUpdate);
    }



    public Boolean deleteById(Long id) {
        Grade gradeToDelete = getById(id);
        if (gradeToDelete == null) {
            return false;
        }
        gradeToDelete.setActive(false);
        gradeToDelete.setUpdatedDate(new Date());
        gradeRepository.save(gradeToDelete);
        return true;
    }
}

