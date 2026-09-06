package com.example.UniversityERPSystem.dtos;


import com.example.UniversityERPSystem.entities.Student;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class StudentDTO {

    private Long id;


    @NotBlank(message = "Student name cannot be blank")
    @Size(max = 255, message = "Student name cannot exceed 255 characters")
    private String name;


    @NotBlank(message = "Student gender cannot be blank")
    @Size(max = 255, message = "Student gender cannot exceed 255 characters")
    private String gender;


    // Phone number can be received in requests but will not appear in responses.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Student phone number cannot be blank")
    @Size(max = 255, message = "Student phone number cannot exceed 255 characters")
    private String phoneNumber;


    @NotBlank(message = "Student major cannot be blank")
    @Size(max = 255, message = "Student major cannot exceed 255 characters")
    private String major;


    @NotNull(message = "Program ID cannot be null")
    @Positive(message = "Program ID must be greater than zero")
    private Long programId;


    // Convert one Student Entity to StudentDTO.
    public static StudentDTO convertToDTO(Student student) {

        return StudentDTO.builder()
                .id(student.getId())
                .name(student.getName())
                .gender(student.getGender())
                .major(student.getMajor())
                .programId(
                        student.getProgram() != null
                                ? student.getProgram().getId()
                                : null
                )
                .build();
    }




}