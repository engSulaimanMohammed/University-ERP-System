package com.example.UniversityERPSystem.entities;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import java.util.Date;

@MappedSuperclass
public class BaseClass {

    @Id
    @GeneratedValue

    private Long id;
    private boolean isActive;
    private Date createdDate;
    private Date updatedDate;
}
