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
public class Course extends BaseClass {

    private String title;
    private String courseCode;
    private int creditHours;

    @JsonIgnore
    @ManyToOne
    private Program program;

    @JsonIgnore
    @ManyToOne
    private Instructor instructor;

    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments;

    @OneToMany(mappedBy = "course")
    private List<Exam> exams;

}
