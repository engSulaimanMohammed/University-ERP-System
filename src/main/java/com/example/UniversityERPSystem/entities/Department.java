package com.example.UniversityERPSystem.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Department extends BaseClass {

    private String name;
    private String description;

    @ManyToOne
    private Faculty faculty;

    @OneToMany(mappedBy = "department")
    private List<Program> programs;

    @OneToMany(mappedBy = "department")
    private List<Instructor> instructors;

}
