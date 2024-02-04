package com.cursosapi.springsecurity.persistence.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Category {
    @Id
    private Long id;
    private String name;
    private CategoryStatus categoryStatus;
    public static enum CategoryStatus{
        ENABLE,DIABLED;
    }
}
