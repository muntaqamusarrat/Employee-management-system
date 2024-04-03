package com.example.employeemanagementsystem.service;

import com.example.employeemanagementsystem.dto.DepartmentDTO;
import com.example.employeemanagementsystem.models.Department;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (departmentOptional.isPresent()) {
            return departmentOptional.get();
        } else {
            throw new RuntimeException("Department not found with id: " + id);
        }
    }

    public void addDepartment(DepartmentDTO departmentDTO) {
        Department department = new Department();
        department.setId(departmentDTO.getId());
        department.setName(departmentDTO.getName());
        departmentRepository.save(department);
    }

    public void updateDepartment(Long id, DepartmentDTO departmentDTO) {
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (departmentOptional.isPresent()) {
            Department department = departmentOptional.get();
            department.setId(id);
            department.setName(departmentDTO.getName());
            // Set other properties manually as needed
            departmentRepository.save(department);
        } else {
            throw new RuntimeException("Department not found with id: " + id);
        }
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
