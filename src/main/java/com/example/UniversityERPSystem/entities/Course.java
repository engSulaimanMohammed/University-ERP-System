package com.example.UniversityERPSystem.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Course {

    private String title;
    private String courseCode;
    private int creditHours;

    @ManyToOne
    private Program program;


}
