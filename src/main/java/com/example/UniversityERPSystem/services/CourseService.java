package com.example.UniversityERPSystem.services;


import com.example.UniversityERPSystem.entities.Course;
import com.example.UniversityERPSystem.entities.Instructor;
import com.example.UniversityERPSystem.entities.Program;
import com.example.UniversityERPSystem.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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




    public Course getById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent() && course.get().isActive()) {
            return course.get();
        }
        return null;
    }




    public Course updateCourse(Long id, String title, String courseCode,
                               int creditHours, Program program, Instructor instructor) {
        Course courseToUpdate = getById(id);
        if (courseToUpdate == null) {
            return null;
        }
        courseToUpdate.setTitle(title);
        courseToUpdate.setCourseCode(courseCode);
        courseToUpdate.setCreditHours(creditHours);
        courseToUpdate.setProgram(program);
        courseToUpdate.setInstructor(instructor);
        courseToUpdate.setUpdatedDate(new Date());
        return courseRepository.save(courseToUpdate);
    }


















}
