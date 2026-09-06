package com.example.UniversityERPSystem.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Guardian extends BaseClass {

    private String name;
    private String relationship;
    private String phoneNumber;

    @ManyToOne
    private Student student;
}
