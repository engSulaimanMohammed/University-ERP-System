package com.example.UniversityERPSystem.services;

import com.example.UniversityERPSystem.entities.Course;
import com.example.UniversityERPSystem.entities.Enrollment;
import com.example.UniversityERPSystem.entities.Student;
import com.example.UniversityERPSystem.repositories.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }



    public Enrollment addEnrollment(Enrollment enrollment, Student student, Course course) {
        if (enrollment == null) {
            throw new IllegalArgumentException("Enrollment cannot be null");
        }
        enrollment.setActive(true);
        enrollment.setCreatedDate(new Date());
        enrollment.setStudent(student);
        enrollment.setCourse(course);
        return enrollmentRepository.save(enrollment);
    }












}
