package com.example.UniversityERPSystem.services;


import com.example.UniversityERPSystem.entities.Course;
import com.example.UniversityERPSystem.entities.Instructor;
import com.example.UniversityERPSystem.entities.Program;
import com.example.UniversityERPSystem.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    public Course addCourse(Course course, Program program, Instructor instructor) {
        if (course == null) {
            throw new IllegalArgumentException("Course cannot be null");
        }
        course.setActive(true);
        course.setCreatedDate(new Date());
        course.setProgram(program);
        course.setInstructor(instructor);
        return courseRepository.save(course);
    }



    public List<Course> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        List<Course> activeCourses = new ArrayList<>();
        for (Course course : courses) {
            if (course.isActive()) {
                activeCourses.add(course);
            }
        }
        return activeCourses;
    }















}
