package com.example.UniversityERPSystem.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Exam extends BaseClass {

    private String title;
    private Date examDate;
    private double totalMarks;

    @JsonIgnore
    @ManyToOne
    private Course course;
}
