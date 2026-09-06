package com.example.UniversityERPSystem.services;



import com.example.UniversityERPSystem.repositories.ClassroomRepository;
import org.springframework.stereotype.Service;

@Service
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }





}
