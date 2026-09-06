package com.example.UniversityERPSystem.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.Date;
import java.util.List;

@Entity
public class Enrollment extends BaseClass  {

    private Date enrollmentDate;
    private String status;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "enrollment")
    private List<Grade> grades;
}
