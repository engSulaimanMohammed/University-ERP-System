package com.example.UniversityERPSystem.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class Grade extends BaseClass{

    private double score;
    private String letterGrade;

    @ManyToOne
    private Enrollment enrollment;

    @ManyToOne
    private  Exam exam;


}
