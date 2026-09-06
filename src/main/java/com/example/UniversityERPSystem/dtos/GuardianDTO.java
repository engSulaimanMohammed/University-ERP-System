package com.example.UniversityERPSystem.dtos;


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
public class GuardianDTO {

    private Long id;


    @NotBlank(message = "Guardian name cannot be blank")
    @Size(max = 255, message = "Guardian name cannot exceed 255 characters")
    private String name;


    @NotBlank(message = "Guardian relationship cannot be blank")
    @Size(max = 255, message = "Guardian relationship cannot exceed 255 characters")
    private String relationship;


    // Phone number can be received in requests but will not appear in responses.
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Guardian phone number cannot be blank")
    @Size(max = 255, message = "Guardian phone number cannot exceed 255 characters")
    private String phoneNumber;


    @NotNull(message = "Student ID cannot be null")
    @Positive(message = "Student ID must be greater than zero")
    private Long studentId;


}