package com.example.UniversityERPSystem.controllers;

import com.example.UniversityERPSystem.dtos.CourseDTO;
import com.example.UniversityERPSystem.entities.Course;
import com.example.UniversityERPSystem.services.CourseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;


    // Constructor Injection.
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    // Add a new Course.
    @PostMapping("/add")
    public CourseDTO addCourse(
            @Valid @RequestBody CourseDTO courseDTO) {

        // Create Course Entity from DTO.
        Course course = new Course();

        course.setTitle(courseDTO.getTitle());
        course.setCourseCode(courseDTO.getCourseCode());
        course.setCreditHours(courseDTO.getCreditHours());

        // Save Course using Program ID and optional Instructor ID.
        Course savedCourse = courseService.addCourse(
                course,
                courseDTO.getProgramId(),
                courseDTO.getInstructorId()
        );

        // Return DTO instead of raw Entity.
        return CourseDTO.convertToDTO(savedCourse);
    }


    // Get all active Courses.
    @GetMapping("/getAll")
    public List<CourseDTO> getAllCourses() {

        return CourseDTO.convertToDTO(
                courseService.getAllCourses()
        );
    }


    // Get active Course by ID.
    @GetMapping("/getById/{id}")
    public CourseDTO getById(@PathVariable Long id) {

        Course course = courseService.getById(id);

        return CourseDTO.convertToDTO(course);
    }


    // Update an existing Course.
    @PutMapping("/update/{id}")
    public CourseDTO updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseDTO courseDTO) {

        Course updatedCourse = courseService.updateCourse(
                id,
                courseDTO.getTitle(),
                courseDTO.getCourseCode(),
                courseDTO.getCreditHours(),
                courseDTO.getProgramId(),
                courseDTO.getInstructorId()
        );

        // Return updated Course as DTO.
        return CourseDTO.convertToDTO(updatedCourse);
    }


    // Assign an Instructor to a Course.
    @PutMapping("/assignInstructor/{courseId}/{instructorId}")
    public CourseDTO assignInstructor(
            @PathVariable Long courseId,
            @PathVariable Long instructorId) {

        Course updatedCourse = courseService.assignInstructor(
                courseId,
                instructorId
        );

        return CourseDTO.convertToDTO(updatedCourse);
    }


    // Get all active Courses inside a specific Program.
    @GetMapping("/byProgram/{programId}")
    public List<CourseDTO> getCoursesByProgram(
            @PathVariable Long programId) {

        return CourseDTO.convertToDTO(
                courseService.getCoursesByProgram(programId)
        );
    }


    // Get all active Courses taught by a specific Instructor.
    @GetMapping("/byInstructor/{instructorId}")
    public List<CourseDTO> getCoursesByInstructor(
            @PathVariable Long instructorId) {

        return CourseDTO.convertToDTO(
                courseService.getCoursesByInstructor(instructorId)
        );
    }


    // Get all active Courses without an assigned Instructor.
    @GetMapping("/withoutInstructor")
    public List<CourseDTO> getCoursesWithoutInstructor() {

        return CourseDTO.convertToDTO(
                courseService.getCoursesWithoutInstructor()
        );
    }


    // Soft delete Course by ID.
    @DeleteMapping("/delete/{id}")
    public CourseDTO deleteById(@PathVariable Long id) {

        // Get Course before Soft Delete.
        CourseDTO courseDTO = CourseDTO.convertToDTO(
                courseService.getById(id)
        );

        // Perform Soft Delete.
        courseService.deleteById(id);

        // Return DTO instead of Boolean or raw Entity.
        return courseDTO;
    }
}