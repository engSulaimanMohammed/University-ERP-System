package com.example.UniversityERPSystem.controllers;

import com.example.UniversityERPSystem.entities.Classroom;
import com.example.UniversityERPSystem.services.ClassroomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classroom")
public class ClassroomController {

    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }


    @PostMapping("/add")
    public Classroom addClassroom(@RequestBody Classroom classroom) {
        return null;
    }


}
