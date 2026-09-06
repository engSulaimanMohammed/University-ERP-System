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
public class Department extends BaseClass {

    private String name;
    private String description;

    @JsonIgnore
    @ManyToOne
    private Faculty faculty;

    @OneToMany(mappedBy = "department")
    private List<Program> programs;

    @OneToMany(mappedBy = "department")
    private List<Instructor> instructors;

}
