package com.example.employeemanagementsystem.controllers;

import com.example.employeemanagementsystem.dto.EmployeeDTO;
import com.example.employeemanagementsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployeeDTOs();
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable("id") Long id) {
        return employeeService.getEmployeeDTOById(id);
    }

    @PostMapping
    public void addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.saveEmployeeFromDTO(employeeDTO);
    }

    @PutMapping("/{id}")
    public void updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeDTO employeeDTO) {
        employeeDTO.setId(id);
        employeeService.updateEmployeeFromDTO(employeeDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployeeById(id);
    }
}
