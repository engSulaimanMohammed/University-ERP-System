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
public class Student extends BaseClass {


    private String name;
    private String gender;
    private String phoneNumber;
    private String major;

    @JsonIgnore
    @ManyToOne
    private Program program;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;

    @OneToMany(mappedBy = "student")
    private List<Guardian> guardians;
}
