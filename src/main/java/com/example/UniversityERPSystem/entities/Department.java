package com.example.UniversityERPSystem.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Department extends BaseClass {

    private String name;
    private String description;

    @ManyToOne
    private Faculty faculty;
}
