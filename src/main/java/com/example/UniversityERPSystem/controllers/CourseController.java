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
        return null;
    }


    @GetMapping("/getAll")
    public List<Course> getAllCourses() {
        // Call getAllCourses from CourseService
        return null;
    }













}
