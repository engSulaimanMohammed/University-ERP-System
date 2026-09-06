package com.example.UniversityERPSystem.services;


import com.example.UniversityERPSystem.entities.Course;
import com.example.UniversityERPSystem.entities.Exam;
import com.example.UniversityERPSystem.repositories.ExamRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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



    public List<Exam> getAllExams() {
        List<Exam> exams = examRepository.findAll();
        List<Exam> activeExams = new ArrayList<>();
        for (Exam exam : exams) {
            if (exam.isActive()) {
                activeExams.add(exam);
            }
        }
        return activeExams;
    }



    public Exam getById(Long id) {
        Optional<Exam> exam = examRepository.findById(id);
        if (exam.isPresent() && exam.get().isActive()) {
            return exam.get();
        }
        return null;
    }



    public Exam updateExam(Long id, String title, Date examDate,
                           double totalMarks, Course course) {
        Exam examToUpdate = getById(id);
        if (examToUpdate == null) {
            return null;
        }
        examToUpdate.setTitle(title);
        examToUpdate.setExamDate(examDate);
        examToUpdate.setTotalMarks(totalMarks);
        examToUpdate.setCourse(course);
        examToUpdate.setUpdatedDate(new Date());
        return examRepository.save(examToUpdate);
    }





























}
