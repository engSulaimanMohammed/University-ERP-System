package com.example.UniversityERPSystem.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Program extends BaseClass {

    private String name;
    private String degreeLevel;
    private int durationYears;

    @JsonIgnore
    @ManyToOne
    private Department department;


    @OneToMany(mappedBy = "program")
    private List<Course> courses;

    @OneToMany(mappedBy = "program")
    private List<Student> students;

}
