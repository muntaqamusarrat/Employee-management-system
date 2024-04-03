package com.example.employeemanagementsystem.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
public class Department {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    private String name;

    // One-to-many relationship with Designation
    @Setter
    @Getter
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Designation> designations;

    // Many-to-many relationship with Project
    @ManyToMany
    @JoinTable(
            name = "department_project",
            joinColumns = @JoinColumn(name = "department_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private List<Project> projects;

}
