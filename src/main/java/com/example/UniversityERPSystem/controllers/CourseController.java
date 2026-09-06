package com.example.UniversityERPSystem.controllers;

import com.example.UniversityERPSystem.entities.Course;
import com.example.UniversityERPSystem.services.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @PostMapping("/add")
    public Course addCourse(@RequestBody Course course) {
        // Call addCourse from CourseService
        // Course also needs Program and Instructor
        return courseService.addCourse(
                course,
                course.getProgram(),
                course.getInstructor()
        );
    }


    @GetMapping("/getAll")
    public List<Course> getAllCourses() {
        // Call getAllCourses from CourseService
        return courseService.getAllCourses();
    }


    @GetMapping("/getById/{id}")
    public Course getById(@PathVariable Long id) {
        // Call getById from CourseService
        return courseService.getById(id);
    }


    @PutMapping("/update/{id}")
    public Course updateCourse(@PathVariable Long id,
                               @RequestBody Course course) {
        return courseService.updateCourse(
                id,
                course.getTitle(),
                course.getCourseCode(),
                course.getCreditHours(),
                course.getProgram(),
                course.getInstructor()
        );    }


    @DeleteMapping("/delete/{id}")
    public Boolean deleteById(@PathVariable Long id) {
        // Call deleteById from CourseService
        return courseService.deleteById(id);
    }
}
