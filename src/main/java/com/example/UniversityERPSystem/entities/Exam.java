package com.example.UniversityERPSystem.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity
public class Exam extends BaseClass {

    private String title;
    private Date examDate;
    private double totalMarks;

    @ManyToOne
    private Course course;
}
