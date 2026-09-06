package com.example.UniversityERPSystem.services;

import com.example.UniversityERPSystem.entities.Program;
import com.example.UniversityERPSystem.entities.Student;
import com.example.UniversityERPSystem.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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





    public Student getById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent() && student.get().isActive()) {
            return student.get();
        }
        return null;
    }




    public Student updateStudent(Long id, String name, String gender,
                                 String phoneNumber, String major, Program program) {
        Student studentToUpdate = getById(id);
        if (studentToUpdate == null) {
            return null;
        }
        studentToUpdate.setName(name);
        studentToUpdate.setGender(gender);
        studentToUpdate.setPhoneNumber(phoneNumber);
        studentToUpdate.setMajor(major);
        studentToUpdate.setProgram(program);
        studentToUpdate.setUpdatedDate(new Date());
        return studentRepository.save(studentToUpdate);
    }




    public Boolean deleteById(Long id) {
        Student studentToDelete = getById(id);
        if (studentToDelete == null) {
            return false;
        }
        studentToDelete.setActive(false);
        studentToDelete.setUpdatedDate(new Date());
        studentRepository.save(studentToDelete);
        return true;
    }

















}