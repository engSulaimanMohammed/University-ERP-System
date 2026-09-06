package com.example.UniversityERPSystem.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Course extends BaseClass {

    private String title;
    private String courseCode;
    private int creditHours;

    @ManyToOne
    private Program program;


    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments;
}
