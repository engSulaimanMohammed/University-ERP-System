package com.example.UniversityERPSystem.services;

import com.example.UniversityERPSystem.entities.Program;
import com.example.UniversityERPSystem.entities.Student;
import com.example.UniversityERPSystem.exceptions.ResourceNotFoundException;
import com.example.UniversityERPSystem.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final ProgramService programService;


    // Constructor Injection.
    public StudentService(StudentRepository studentRepository,
                          ProgramService programService) {

        this.studentRepository = studentRepository;
        this.programService = programService;
    }


    // Validate Student fields.
    private void validateStudentData(String name,
                                     String gender,
                                     String phoneNumber,
                                     String major) {

        // Validate Student name.
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Student name cannot be blank"
            );
        }

        if (name.length() > 255) {
            throw new IllegalArgumentException(
                    "Student name cannot exceed 255 characters"
            );
        }


        // Validate Gender.
        if (gender == null || gender.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Student gender cannot be blank"
            );
        }

        if (gender.length() > 255) {
            throw new IllegalArgumentException(
                    "Student gender cannot exceed 255 characters"
            );
        }


        // Validate Phone Number.
        if (phoneNumber == null || phoneNumber.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Student phone number cannot be blank"
            );
        }

        if (phoneNumber.length() > 255) {
            throw new IllegalArgumentException(
                    "Student phone number cannot exceed 255 characters"
            );
        }


        // Validate Major.
        if (major == null || major.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Student major cannot be blank"
            );
        }

        if (major.length() > 255) {
            throw new IllegalArgumentException(
                    "Student major cannot exceed 255 characters"
            );
        }
    }


    // Add a new Student.
    public Student addStudent(Student student,
                              Long programId) {

        if (student == null) {
            throw new IllegalArgumentException(
                    "Student cannot be null"
            );
        }

        // Validate Student data.
        validateStudentData(
                student.getName(),
                student.getGender(),
                student.getPhoneNumber(),
                student.getMajor()
        );

        // Get active Program.
        Program program = programService.getById(programId);

        // Set Program relationship.
        student.setProgram(program);

        // Set BaseClass fields.
        student.setActive(true);
        student.setCreatedDate(new Date());

        // Save Student.
        return studentRepository.save(student);
    }


    // Get all active Students.
    public List<Student> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .filter(Student::isActive)
                .toList();
    }


    // Get active Student by ID.
    public Student getById(Long id) {

        // Validate Student ID.
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(
                    "Student ID must be greater than zero"
            );
        }

        // Find Student.
        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id: " + id
                        )
                );

        // Do not return Soft Deleted Student.
        if (!student.isActive()) {
            throw new ResourceNotFoundException(
                    "Student not found with id: " + id
            );
        }

        return student;
    }


    // Update an existing Student.
    public Student updateStudent(Long id,
                                 String name,
                                 String gender,
                                 String phoneNumber,
                                 String major,
                                 Long programId) {

        // Validate new Student data.
        validateStudentData(
                name,
                gender,
                phoneNumber,
                major
        );

        // Get active Student.
        Student studentToUpdate = getById(id);

        // Get active Program.
        Program program = programService.getById(programId);

        // Update Student fields.
        studentToUpdate.setName(name);
        studentToUpdate.setGender(gender);
        studentToUpdate.setPhoneNumber(phoneNumber);
        studentToUpdate.setMajor(major);

        // Update Program relationship.
        studentToUpdate.setProgram(program);

        // Update modification date.
        studentToUpdate.setUpdatedDate(new Date());

        // Save updated Student.
        return studentRepository.save(studentToUpdate);
    }


    // Soft delete Student by ID.
    public Boolean deleteById(Long id) {

        // Get active Student.
        Student studentToDelete = getById(id);

        // Soft Delete.
        studentToDelete.setActive(false);

        // Update modification date.
        studentToDelete.setUpdatedDate(new Date());

        // Save changes.
        studentRepository.save(studentToDelete);

        return true;
    }
}