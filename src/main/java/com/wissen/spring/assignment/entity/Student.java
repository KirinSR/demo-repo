package com.wissen.spring.assignment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Student {
    @Id
    private Long rollNo;
    private String name;
    private String standard;
    private String school;
    private Double percentage;
}
