package com.example.UniversityERPSystem.dtos;

import com.example.UniversityERPSystem.entities.Classroom;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomDTO {

    private Long id;


    @NotBlank(message = "Room number cannot be blank")
    @Size(max = 255, message = "Room number cannot exceed 255 characters")
    private String roomNumber;


    @PositiveOrZero(message = "Floor cannot be negative")
    private int floor;


    @Positive(message = "Classroom capacity must be greater than zero")
    private int capacity;


    @NotNull(message = "Department ID cannot be null")
    @Positive(message = "Department ID must be greater than zero")
    private Long departmentId;

    // Convert one Classroom Entity to ClassroomDTO.
    public static ClassroomDTO convertToDTO(Classroom classroom) {

        return ClassroomDTO.builder()
                .id(classroom.getId())
                .roomNumber(classroom.getRoomNumber())
                .floor(classroom.getFloor())
                .capacity(classroom.getCapacity())
                .departmentId(
                        classroom.getDepartment() != null
                                ? classroom.getDepartment().getId()
                                : null
                )
                .build();
    }
}