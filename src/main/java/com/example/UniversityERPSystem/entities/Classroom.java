package com.example.UniversityERPSystem.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Classroom extends BaseClass {

    private String roomNumber;
    private int floor;
    private int capacity;

    @ManyToOne
    private Department department;
}
