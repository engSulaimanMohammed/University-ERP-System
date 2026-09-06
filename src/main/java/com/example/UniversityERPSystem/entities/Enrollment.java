package com.example.UniversityERPSystem.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity
public class Enrollment extends BaseClass  {

    private Date enrollmentDate;
    private String status;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;
}
