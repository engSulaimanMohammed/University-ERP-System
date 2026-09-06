package com.example.UniversityERPSystem.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Faculty extends BaseClass {

    private String name;
    private String description;

    @ManyToOne
    private University university;
}
