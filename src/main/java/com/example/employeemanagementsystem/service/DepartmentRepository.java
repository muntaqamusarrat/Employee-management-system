package com.example.employeemanagementsystem.service;

import com.example.employeemanagementsystem.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
