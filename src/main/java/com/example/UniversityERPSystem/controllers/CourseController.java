package com.example.UniversityERPSystem.controllers;

import com.example.UniversityERPSystem.dtos.AssignInstructorDTO;
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

        Course course = new Course();

        course.setTitle(courseDTO.getTitle());
        course.setCourseCode(courseDTO.getCourseCode());
        course.setCreditHours(courseDTO.getCreditHours());

        Course savedCourse = courseService.addCourse(
                course,
                courseDTO.getProgramId(),
                courseDTO.getInstructorId()
        );

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
    public CourseDTO getById(
            @PathVariable Long id) {

        return CourseDTO.convertToDTO(
                courseService.getById(id)
        );
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

        return CourseDTO.convertToDTO(updatedCourse);
    }


    // Assign an Instructor to a Course.
    @PutMapping("/assignInstructor")
    public CourseDTO assignInstructor(
            @Valid @RequestBody AssignInstructorDTO assignInstructorDTO) {

        Course updatedCourse =
                courseService.assignInstructor(
                        assignInstructorDTO.getCourseId(),
                        assignInstructorDTO.getInstructorId()
                );

        return CourseDTO.convertToDTO(updatedCourse);
    }


    // Get active Courses inside a Program.
    @GetMapping("/byProgram/{programId}")
    public List<CourseDTO> getCoursesByProgram(
            @PathVariable Long programId) {

        return CourseDTO.convertToDTO(
                courseService.getCoursesByProgram(programId)
        );
    }


    // Get Courses taught by an Instructor.
    @GetMapping("/byInstructor/{instructorId}")
    public List<CourseDTO> getCoursesByInstructor(
            @PathVariable Long instructorId) {

        return CourseDTO.convertToDTO(
                courseService.getCoursesByInstructor(instructorId)
        );
    }


    // Get Courses without an Instructor.
    @GetMapping("/withoutInstructor")
    public List<CourseDTO> getCoursesWithoutInstructor() {

        return CourseDTO.convertToDTO(
                courseService.getCoursesWithoutInstructor()
        );
    }


    // Soft delete Course.
    @DeleteMapping("/delete/{id}")
    public CourseDTO deleteById(
            @PathVariable Long id) {

        CourseDTO courseDTO =
                CourseDTO.convertToDTO(
                        courseService.getById(id)
                );

        courseService.deleteById(id);

        return courseDTO;
    }
}