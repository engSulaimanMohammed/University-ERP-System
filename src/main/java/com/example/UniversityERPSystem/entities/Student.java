package com.example.UniversityERPSystem.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Student {


    private String name;
    private String gender;
    private String phoneNumber;
    private String major;

    @ManyToOne
    private Program program;
}
