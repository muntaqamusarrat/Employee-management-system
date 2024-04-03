package com.example.employeemanagementsystem.service;

import com.example.employeemanagementsystem.models.Department;
import com.example.employeemanagementsystem.models.Designation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DesignationRepository extends JpaRepository<Designation, Long> {
}
