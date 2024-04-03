package com.example.employeemanagementsystem.controllers;

import com.example.employeemanagementsystem.dto.EmployeeDTO;
import com.example.employeemanagementsystem.models.Employee;
import com.example.employeemanagementsystem.service.DepartmentService;
import com.example.employeemanagementsystem.service.DesignationService;
import com.example.employeemanagementsystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DesignationService designationService;

    @GetMapping
    public String getAllEmployees(Model model) {
        List<EmployeeDTO> employees = employeeService.getAllEmployeeDTOs();
        model.addAttribute("employees", employees);
        return "Employee/employee-list";
    }

    @GetMapping("/new")
    public String showEmployeeForm(Model model) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        model.addAttribute("employee", employeeDTO);
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("designations", designationService.getAllDesignations());
        return "Employee/employee-form";
    }

    @PostMapping
    public String saveEmployee(@ModelAttribute("employee") EmployeeDTO employeeDTO) {
        employeeService.saveEmployeeFromDTO(employeeDTO);
        return "redirect:/employees";
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        EmployeeDTO employeeDTO = employeeService.getEmployeeDTOById(id);
        model.addAttribute("employee", employeeDTO);
        model.addAttribute("departments", departmentService.getAllDepartments());
        model.addAttribute("designations", designationService.getAllDesignations());
        return "Employee/employee-form";
    }

    @PostMapping("/edit/{id}")
    public String updateEmployee(@PathVariable("id") Long id, @ModelAttribute("employee") EmployeeDTO employeeDTO) {
        employeeDTO.setId(id);
        employeeService.updateEmployeeFromDTO(employeeDTO);
        return "redirect:/employees";
    }

    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployeeById(id);
        return "redirect:/employees";
    }
}
