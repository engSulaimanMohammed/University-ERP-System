package com.example.UniversityERPSystem.services;


import com.example.UniversityERPSystem.repositories.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }



}
