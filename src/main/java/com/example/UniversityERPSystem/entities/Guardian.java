package com.example.UniversityERPSystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Guardian extends BaseClass {

    private String name;
    private String relationship;
    private String phoneNumber;

    @JsonIgnore
    @ManyToOne
    private Student student;
}
