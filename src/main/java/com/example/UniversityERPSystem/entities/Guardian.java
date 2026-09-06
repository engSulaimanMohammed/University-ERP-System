package com.example.UniversityERPSystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Guardian extends BaseClass {

    private String name;
    private String relationship;
    private String phoneNumber;

    @JsonIgnore
    @ManyToOne
    private Student student;
}
