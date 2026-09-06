package com.example.UniversityERPSystem.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Student extends BaseClass {


    private String name;
    private String gender;
    private String phoneNumber;
    private String major;

    @ManyToOne
    private Program program;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;

    @OneToMany(mappedBy = "student")
    private List<Guardian> guardians;
}
