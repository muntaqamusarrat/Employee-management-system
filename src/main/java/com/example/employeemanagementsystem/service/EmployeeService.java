package com.example.employeemanagementsystem.service;

import com.example.employeemanagementsystem.dto.EmployeeDTO;
import com.example.employeemanagementsystem.models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<EmployeeDTO> getAllEmployeeDTOs() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeDTO> employeeDTOs = new ArrayList<>();
        for (Employee employee : employees) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setName(employee.getName());
            employeeDTO.setDepartmentName(employee.getDepartmentName());
            employeeDTO.setPhone(employee.getPhone());
            employeeDTO.setSalary(employee.getSalary());
            employeeDTO.setStartDate(employee.getStartDate());

            employeeDTOs.add(employeeDTO);
        }
        return employeeDTOs;
    }

    public EmployeeDTO getEmployeeDTOById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setName(employee.getName());
            // Set other properties manually as needed
            return employeeDTO;
        } else {
            throw new RuntimeException("Employee not found with id: " + id);
        }
    }

    public void saveEmployeeFromDTO(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setName(employeeDTO.getName());
        // Set other properties manually as needed
        employeeRepository.save(employee);
    }

    public void updateEmployeeFromDTO(EmployeeDTO employeeDTO) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeDTO.getId());
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setId(employeeDTO.getId());
            employee.setName(employeeDTO.getName());
            // Set other properties manually as needed
            employeeRepository.save(employee);
        } else {
            throw new RuntimeException("Employee not found with id: " + employeeDTO.getId());
        }
    }

    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }
}
