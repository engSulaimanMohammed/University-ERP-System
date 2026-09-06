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

















}
