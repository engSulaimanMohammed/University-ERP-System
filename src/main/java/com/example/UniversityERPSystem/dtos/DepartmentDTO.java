package com.example.UniversityERPSystem.dtos;
import com.example.UniversityERPSystem.entities.Department;
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
public class DepartmentDTO {

    private Long id;


    @NotBlank(message = "Department name cannot be blank")
    @Size(max = 255, message = "Department name cannot exceed 255 characters")
    private String name;


    @NotBlank(message = "Department description cannot be blank")
    @Size(max = 255, message = "Department description cannot exceed 255 characters")
    private String description;


    @NotNull(message = "Faculty ID cannot be null")
    @Positive(message = "Faculty ID must be greater than zero")
    private Long facultyId;


    // Convert one Department Entity to DepartmentDTO.
    public static DepartmentDTO convertToDTO(Department department) {
        return DepartmentDTO.builder().id(department.getId())
                .name(department.getName()).description(department.getDescription())
                .facultyId(department.getFaculty() != null
                                ? department.getFaculty().getId() : null).build();
    }


}