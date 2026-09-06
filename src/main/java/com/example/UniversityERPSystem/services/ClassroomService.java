package com.example.UniversityERPSystem.services;



import com.example.UniversityERPSystem.entities.Classroom;
import com.example.UniversityERPSystem.entities.Department;
import com.example.UniversityERPSystem.repositories.ClassroomRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }



    public Classroom addClassroom(Classroom classroom, Department department) {
        if (classroom == null) {
            throw new IllegalArgumentException("Classroom cannot be null");
        }
        classroom.setActive(true);
        classroom.setCreatedDate(new Date());
        classroom.setDepartment(department);
        return classroomRepository.save(classroom);
    }



    public List<Classroom> getAllClassrooms() {
        List<Classroom> classrooms = classroomRepository.findAll();
        List<Classroom> activeClassrooms = new ArrayList<>();
        for (Classroom classroom : classrooms) {
            if (classroom.isActive()) {
                activeClassrooms.add(classroom);
            }
        }
        return activeClassrooms;
    }



    public Classroom getById(Long id) {
        Optional<Classroom> classroom = classroomRepository.findById(id);
        if (classroom.isPresent() && classroom.get().isActive()) {
            return classroom.get();
        }
        return null;
    }



    public Classroom updateClassroom(Long id, String roomNumber, int floor,
                                     int capacity, Department department) {
        Classroom classroomToUpdate = getById(id);
        if (classroomToUpdate == null) {
            return null;
        }
        classroomToUpdate.setRoomNumber(roomNumber);
        classroomToUpdate.setFloor(floor);
        classroomToUpdate.setCapacity(capacity);
        classroomToUpdate.setDepartment(department);
        classroomToUpdate.setUpdatedDate(new Date());
        return classroomRepository.save(classroomToUpdate);
    }


    public Boolean deleteById(Long id) {
        Classroom classroomToDelete = getById(id);
        if (classroomToDelete == null) {
            return false;
        }
        classroomToDelete.setActive(false);
        classroomToDelete.setUpdatedDate(new Date());
        classroomRepository.save(classroomToDelete);
        return true;
    }
}
