package com.example.UniversityERPSystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Grade extends BaseClass{

    private double score;
    private String letterGrade;

    @JsonIgnore
    @ManyToOne
    private Enrollment enrollment;

    @ManyToOne
    private  Exam exam;


}
