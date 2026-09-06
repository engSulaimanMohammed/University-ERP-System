package com.example.UniversityERPSystem.services;



import com.example.UniversityERPSystem.entities.Classroom;
import com.example.UniversityERPSystem.entities.Department;
import com.example.UniversityERPSystem.repositories.ClassroomRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

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









}
