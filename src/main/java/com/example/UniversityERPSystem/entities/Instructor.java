package com.example.UniversityERPSystem.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Instructor extends BaseClass {

    private String name;
    private String email;
    private String phoneNumber;
    private String specialization;

    @ManyToOne
    private Department department;

    @OneToMany(mappedBy = "instructor")
    private List<Course> courses;

}
