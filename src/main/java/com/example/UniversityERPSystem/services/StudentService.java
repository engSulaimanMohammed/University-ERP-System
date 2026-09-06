package com.example.UniversityERPSystem.services;

import com.example.UniversityERPSystem.entities.Program;
import com.example.UniversityERPSystem.entities.Student;
import com.example.UniversityERPSystem.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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



    public List<Student> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<Student> activeStudents = new ArrayList<>();
        for (Student student : students) {
            if (student.isActive()) {
                activeStudents.add(student);
            }
        }
        return activeStudents;
    }



















}