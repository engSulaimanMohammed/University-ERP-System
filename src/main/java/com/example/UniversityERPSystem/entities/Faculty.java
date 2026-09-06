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
public class Faculty extends BaseClass {

    private String name;
    private String description;

    @JsonIgnore
    @ManyToOne
    private University university;

    @OneToMany(mappedBy = "faculty")
    private List<Department> departments;
}
