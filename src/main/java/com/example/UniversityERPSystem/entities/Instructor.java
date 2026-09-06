package com.example.UniversityERPSystem.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @ManyToOne
    private Department department;

    @OneToMany(mappedBy = "instructor")
    private List<Course> courses;

}
