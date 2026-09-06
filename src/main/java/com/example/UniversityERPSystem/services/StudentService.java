package com.example.UniversityERPSystem.services;

import com.example.UniversityERPSystem.entities.Program;
import com.example.UniversityERPSystem.entities.Student;
import com.example.UniversityERPSystem.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }



    public Student addStudent(Student student, Program program) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        student.setActive(true);
        student.setCreatedDate(new Date());
        student.setProgram(program);
        return studentRepository.save(student);
    }











}