package com.example.UniversityERPSystem.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Program {

    private String name;
    private String degreeLevel;
    private int durationYears;

    @ManyToOne
    private Department department;
}
