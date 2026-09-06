package com.example.UniversityERPSystem.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class University extends BaseClass {

    private String name;
    private String location;

    @OneToMany(mappedBy = "university")
    private List<Faculty> faculties;
}