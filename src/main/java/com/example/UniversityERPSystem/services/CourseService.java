package com.example.UniversityERPSystem.services;

import com.example.UniversityERPSystem.entities.Course;
import com.example.UniversityERPSystem.entities.Instructor;
import com.example.UniversityERPSystem.entities.Program;
import com.example.UniversityERPSystem.exceptions.ResourceNotFoundException;
import com.example.UniversityERPSystem.repositories.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final ProgramService programService;
    private final InstructorService instructorService;


    // Constructor Injection.
    public CourseService(CourseRepository courseRepository,
                         ProgramService programService,
                         InstructorService instructorService) {

        this.courseRepository = courseRepository;
        this.programService = programService;
        this.instructorService = instructorService;
    }


    // Validate Course fields.
    private void validateCourseData(String title,
                                    String courseCode,
                                    int creditHours) {

        // Validate Course title.
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Course title cannot be blank"
            );
        }

        if (title.length() > 255) {
            throw new IllegalArgumentException(
                    "Course title cannot exceed 255 characters"
            );
        }


        // Validate Course code.
        if (courseCode == null || courseCode.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Course code cannot be blank"
            );
        }

        if (courseCode.length() > 255) {
            throw new IllegalArgumentException(
                    "Course code cannot exceed 255 characters"
            );
        }


        // Validate Credit Hours.
        if (creditHours <= 0) {
            throw new IllegalArgumentException(
                    "Credit hours must be greater than zero"
            );
        }
    }


    // Validate Instructor ID when Instructor is provided.
    private void validateInstructorId(Long instructorId) {

        if (instructorId != null && instructorId <= 0) {
            throw new IllegalArgumentException(
                    "Instructor ID must be greater than zero"
            );
        }
    }


    // Add a new Course.
    public Course addCourse(Course course,
                            Long programId,
                            Long instructorId) {

        if (course == null) {
            throw new IllegalArgumentException(
                    "Course cannot be null"
            );
        }

        // Validate Course fields.
        validateCourseData(
                course.getTitle(),
                course.getCourseCode(),
                course.getCreditHours()
        );

        // Get active Program.
        Program program = programService.getById(programId);

        // Set Program.
        course.setProgram(program);


        // Instructor is optional.
        validateInstructorId(instructorId);

        if (instructorId != null) {

            Instructor instructor =
                    instructorService.getById(instructorId);

            course.setInstructor(instructor);

        } else {

            course.setInstructor(null);
        }


        // Set BaseClass fields.
        course.setActive(true);
        course.setCreatedDate(new Date());

        // Save Course.
        return courseRepository.save(course);
    }


    // Get all active Courses.
    public List<Course> getAllCourses() {

        return courseRepository.findAll()
                .stream()
                .filter(Course::isActive)
                .toList();
    }


    // Get active Course by ID.
    public Course getById(Long id) {

        // Validate Course ID.
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(
                    "Course ID must be greater than zero"
            );
        }

        // Find Course.
        Course course = courseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Course not found with id: " + id
                        )
                );

        // Do not return Soft Deleted Course.
        if (!course.isActive()) {
            throw new ResourceNotFoundException(
                    "Course not found with id: " + id
            );
        }

        return course;
    }


    // Update an existing Course.
    public Course updateCourse(Long id,
                               String title,
                               String courseCode,
                               int creditHours,
                               Long programId,
                               Long instructorId) {

        // Validate Course fields.
        validateCourseData(
                title,
                courseCode,
                creditHours
        );

        // Get active Course.
        Course courseToUpdate = getById(id);

        // Get active Program.
        Program program = programService.getById(programId);

        // Validate optional Instructor ID.
        validateInstructorId(instructorId);


        // Update Course fields.
        courseToUpdate.setTitle(title);
        courseToUpdate.setCourseCode(courseCode);
        courseToUpdate.setCreditHours(creditHours);

        // Update Program relationship.
        courseToUpdate.setProgram(program);


        // Update Instructor relationship.
        if (instructorId != null) {

            Instructor instructor =
                    instructorService.getById(instructorId);

            courseToUpdate.setInstructor(instructor);

        } else {

            courseToUpdate.setInstructor(null);
        }


        // Update modification date.
        courseToUpdate.setUpdatedDate(new Date());

        // Save updated Course.
        return courseRepository.save(courseToUpdate);
    }


    // Assign an Instructor to a Course.
    public Course assignInstructor(Long courseId,
                                   Long instructorId) {

        // Get active Course.
        Course course = getById(courseId);

        // Validate Instructor ID.
        if (instructorId == null || instructorId <= 0) {
            throw new IllegalArgumentException(
                    "Instructor ID must be greater than zero"
            );
        }

        // Get active Instructor.
        Instructor instructor =
                instructorService.getById(instructorId);

        // Assign Instructor.
        course.setInstructor(instructor);

        // Update modification date.
        course.setUpdatedDate(new Date());

        return courseRepository.save(course);
    }


    // Get all active Courses in a specific Program.
    public List<Course> getCoursesByProgram(Long programId) {

        // Make sure Program exists and is active.
        programService.getById(programId);

        return courseRepository.findActiveCoursesByProgram(programId);
    }


    // Get all active Courses taught by a specific Instructor.
    public List<Course> getCoursesByInstructor(Long instructorId) {

        // Make sure Instructor exists and is active.
        instructorService.getById(instructorId);

        return courseRepository.findActiveCoursesByInstructor(
                instructorId
        );
    }


    // Get all active Courses without an assigned Instructor.
    public List<Course> getCoursesWithoutInstructor() {

        return courseRepository.findActiveCoursesWithoutInstructor();
    }


    // Soft delete Course by ID.
    public Boolean deleteById(Long id) {

        // Get active Course.
        Course courseToDelete = getById(id);

        // Soft Delete.
        courseToDelete.setActive(false);

        // Update modification date.
        courseToDelete.setUpdatedDate(new Date());

        // Save changes.
        courseRepository.save(courseToDelete);

        return true;
    }
}