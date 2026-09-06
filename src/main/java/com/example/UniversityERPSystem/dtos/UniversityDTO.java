package com.example.UniversityERPSystem.dtos;

import com.example.UniversityERPSystem.entities.University;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UniversityDTO {


    private Long id;


    @NotBlank(message = "University name cannot be blank")
    @Size(max = 255, message = "University name cannot exceed 255 characters")
    private String name;


    @NotBlank(message = "University location cannot be blank")
    @Size(max = 255, message = "University location cannot exceed 255 characters")
    private String location;


    public static UniversityDTO convertToDTO(University university) {
        return UniversityDTO.builder()
                .id(university.getId())
                .name(university.getName())
                .location(university.getLocation())
                .build();
    }


    public static List<UniversityDTO> convertToDTO(List<University> universities) {
        return universities.stream()
                .map(UniversityDTO::convertToDTO)
                .toList();
    }
}