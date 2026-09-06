package com.example.UniversityERPSystem.dtos;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
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
public class InstructorDTO {

    private Long id;


    @NotBlank(message = "Instructor name cannot be blank")
    @Size(max = 255, message = "Instructor name cannot exceed 255 characters")
    private String name;


    @NotBlank(message = "Instructor email cannot be blank")
    @Email(message = "Instructor email must be valid")
    @Size(max = 255, message = "Instructor email cannot exceed 255 characters")
    private String email;


    // Phone number can be received in requests but will not appear in responses.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Instructor phone number cannot be blank")
    @Size(max = 255, message = "Instructor phone number cannot exceed 255 characters")
    private String phoneNumber;


    @NotBlank(message = "Instructor specialization cannot be blank")
    @Size(max = 255, message = "Instructor specialization cannot exceed 255 characters")
    private String specialization;


    @NotNull(message = "Department ID cannot be null")
    @Positive(message = "Department ID must be greater than zero")
    private Long departmentId;



}