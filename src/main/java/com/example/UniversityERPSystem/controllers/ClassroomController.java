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
        return classroomService.addClassroom(
                classroom,
                classroom.getDepartment()
        );
    }


    @GetMapping("/getAll")
    public List<Classroom> getAllClassrooms() {
        return classroomService.getAllClassrooms();
    }


    @GetMapping("/getById/{id}")
    public Classroom getById(@PathVariable Long id) {
        return classroomService.getById(id);
    }



    @PutMapping("/update/{id}")
    public Classroom updateClassroom(@PathVariable Long id,
                                     @RequestBody Classroom classroom) {
        return classroomService.updateClassroom(
                id,
                classroom.getRoomNumber(),
                classroom.getFloor(),
                classroom.getCapacity(),
                classroom.getDepartment()
        );
    }

    @DeleteMapping("/delete/{id}")
    public Boolean deleteById(@PathVariable Long id) {
        return classroomService.deleteById(id);
    }
}
