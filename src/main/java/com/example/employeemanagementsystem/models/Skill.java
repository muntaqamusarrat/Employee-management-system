package com.example.employeemanagementsystem.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Skill {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String name;

    // Many-to-many relationship with Employee
    @ManyToMany(mappedBy = "skills")
    private Set<Employee> employees = new HashSet<>();

}
