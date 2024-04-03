package com.example.employeemanagementsystem.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Employee {
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    private String name;
//    private String jobTitle;

//    @Setter
//    @Getter
//    private String departmentName;

    @Getter
    @Setter
    private String phone;

    @Setter
    @Getter
    private double salary;

    @Setter
    @Getter
    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Setter
    @Getter
    @Transient
    private String designation;

    @Setter
    @Getter
    @ManyToMany
    @JoinTable(
            name = "employee_project",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private Set<Project> projects = new HashSet<>();

    @Setter
    @Getter
    @ManyToMany
    @JoinTable(
            name = "employee_skill",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skills = new HashSet<>();
}
