package com.example.UniversityERPSystem.services;

import com.example.UniversityERPSystem.entities.Classroom;
import com.example.UniversityERPSystem.entities.Department;
import com.example.UniversityERPSystem.exceptions.ResourceNotFoundException;
import com.example.UniversityERPSystem.repositories.ClassroomRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final DepartmentService departmentService;


    // Constructor Injection.
    public ClassroomService(ClassroomRepository classroomRepository,
                            DepartmentService departmentService) {

        this.classroomRepository = classroomRepository;
        this.departmentService = departmentService;
    }


    // Validate Classroom fields.
    private void validateClassroomData(String roomNumber,
                                       int floor,
                                       int capacity) {

        // Validate Room Number.
        if (roomNumber == null || roomNumber.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Room number cannot be blank"
            );
        }

        if (roomNumber.length() > 255) {
            throw new IllegalArgumentException(
                    "Room number cannot exceed 255 characters"
            );
        }


        // Floor can be zero for ground floor.
        if (floor < 0) {
            throw new IllegalArgumentException(
                    "Floor cannot be negative"
            );
        }


        // Capacity must be greater than zero.
        if (capacity <= 0) {
            throw new IllegalArgumentException(
                    "Classroom capacity must be greater than zero"
            );
        }
    }


    // Add a new Classroom.
    public Classroom addClassroom(Classroom classroom,
                                  Long departmentId) {

        if (classroom == null) {
            throw new IllegalArgumentException(
                    "Classroom cannot be null"
            );
        }

        // Validate Classroom fields.
        validateClassroomData(
                classroom.getRoomNumber(),
                classroom.getFloor(),
                classroom.getCapacity()
        );

        // Get active Department.
        Department department =
                departmentService.getById(departmentId);

        // Set Department relationship.
        classroom.setDepartment(department);

        // Set BaseClass fields.
        classroom.setActive(true);
        classroom.setCreatedDate(new Date());

        // Save Classroom.
        return classroomRepository.save(classroom);
    }


    // Get all active Classrooms.
    public List<Classroom> getAllClassrooms() {

        return classroomRepository.findAll()
                .stream()
                .filter(Classroom::isActive)
                .toList();
    }


    // Get active Classroom by ID.
    public Classroom getById(Long id) {

        // Validate Classroom ID.
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(
                    "Classroom ID must be greater than zero"
            );
        }

        // Find Classroom.
        Classroom classroom = classroomRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Classroom not found with id: " + id
                        )
                );

        // Do not return Soft Deleted Classroom.
        if (!classroom.isActive()) {
            throw new ResourceNotFoundException(
                    "Classroom not found with id: " + id
            );
        }

        return classroom;
    }


    // Update an existing Classroom.
    public Classroom updateClassroom(Long id,
                                     String roomNumber,
                                     int floor,
                                     int capacity,
                                     Long departmentId) {

        // Validate new Classroom data.
        validateClassroomData(
                roomNumber,
                floor,
                capacity
        );

        // Get active Classroom.
        Classroom classroomToUpdate = getById(id);

        // Get active Department.
        Department department =
                departmentService.getById(departmentId);

        // Update Classroom fields.
        classroomToUpdate.setRoomNumber(roomNumber);
        classroomToUpdate.setFloor(floor);
        classroomToUpdate.setCapacity(capacity);

        // Update relationship.
        classroomToUpdate.setDepartment(department);

        // Update modification date.
        classroomToUpdate.setUpdatedDate(new Date());

        // Save updated Classroom.
        return classroomRepository.save(classroomToUpdate);
    }


    // Soft delete Classroom by ID.
    public Boolean deleteById(Long id) {

        // Get active Classroom.
        Classroom classroomToDelete = getById(id);

        // Soft Delete.
        classroomToDelete.setActive(false);

        // Update modification date.
        classroomToDelete.setUpdatedDate(new Date());

        // Save changes.
        classroomRepository.save(classroomToDelete);

        return true;
    }
}