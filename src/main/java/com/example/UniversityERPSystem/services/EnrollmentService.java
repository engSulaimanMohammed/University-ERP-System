package com.example.UniversityERPSystem.services;

import com.example.UniversityERPSystem.entities.Course;
import com.example.UniversityERPSystem.entities.Enrollment;
import com.example.UniversityERPSystem.entities.Student;
import com.example.UniversityERPSystem.repositories.EnrollmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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



    public List<Enrollment> getAllEnrollments() {
        List<Enrollment> enrollments = enrollmentRepository.findAll();
        List<Enrollment> activeEnrollments = new ArrayList<>();
        for (Enrollment enrollment : enrollments) {
            if (enrollment.isActive()) {
                activeEnrollments.add(enrollment);
            }
        }
        return activeEnrollments;
    }



    public Enrollment getById(Long id) {
        Optional<Enrollment> enrollment = enrollmentRepository.findById(id);
        if (enrollment.isPresent() && enrollment.get().isActive()) {
            return enrollment.get();
        }
        return null;
    }




    public Enrollment updateEnrollment(Long id, Date enrollmentDate,
                                       String status, Student student, Course course) {
        Enrollment enrollmentToUpdate = getById(id);
        if (enrollmentToUpdate == null) {
            return null;
        }
        enrollmentToUpdate.setEnrollmentDate(enrollmentDate);
        enrollmentToUpdate.setStatus(status);
        enrollmentToUpdate.setStudent(student);
        enrollmentToUpdate.setCourse(course);
        enrollmentToUpdate.setUpdatedDate(new Date());
        return enrollmentRepository.save(enrollmentToUpdate);
    }





    public Boolean deleteById(Long id) {
        Enrollment enrollmentToDelete = getById(id);
        if (enrollmentToDelete == null) {
            return false;
        }
        enrollmentToDelete.setActive(false);
        enrollmentToDelete.setUpdatedDate(new Date());
        enrollmentRepository.save(enrollmentToDelete);
        return true;
    }

























}
