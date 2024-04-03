package com.example.employeemanagementsystem.service;

import com.example.employeemanagementsystem.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
