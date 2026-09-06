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






}
