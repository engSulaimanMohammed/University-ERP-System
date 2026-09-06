package com.example.UniversityERPSystem.dtos;


import com.example.UniversityERPSystem.entities.Course;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CourseDTO {

    private Long id;


    @NotBlank(message = "Course title cannot be blank")
    @Size(max = 255, message = "Course title cannot exceed 255 characters")
    private String title;


    @NotBlank(message = "Course code cannot be blank")
    @Size(max = 255, message = "Course code cannot exceed 255 characters")
    private String courseCode;


    @Positive(message = "Credit hours must be greater than zero")
    private int creditHours;


    @NotNull(message = "Program ID cannot be null")
    @Positive(message = "Program ID must be greater than zero")
    private Long programId;


    // Instructor is optional because a course may have no assigned instructor.
    @Positive(message = "Instructor ID must be greater than zero")
    private Long instructorId;



    // Convert one Course Entity to CourseDTO.
    public static CourseDTO convertToDTO(Course course) {

        return CourseDTO.builder()
                .id(course.getId())
                .title(course.getTitle())
                .courseCode(course.getCourseCode())
                .creditHours(course.getCreditHours())
                .programId(
                        course.getProgram() != null
                                ? course.getProgram().getId()
                                : null
                )
                .instructorId(
                        course.getInstructor() != null
                                ? course.getInstructor().getId()
                                : null
                )
                .build();
    }
}