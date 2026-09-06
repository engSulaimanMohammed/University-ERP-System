package com.example.UniversityERPSystem.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Instructor {

    private String name;
    private String email;
    private String phoneNumber;
    private String specialization;

    @ManyToOne
    private Department department;


}
