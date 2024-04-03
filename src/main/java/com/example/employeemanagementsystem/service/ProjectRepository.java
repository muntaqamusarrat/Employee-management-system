package com.example.employeemanagementsystem.service;

import com.example.employeemanagementsystem.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
