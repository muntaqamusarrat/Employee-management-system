package com.example.employeemanagementsystem.controllers;

import com.example.employeemanagementsystem.dto.DepartmentDTO;
import com.example.employeemanagementsystem.models.Department;
import com.example.employeemanagementsystem.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public String getAllDepartments(Model model) {
        List<DepartmentDTO> departmentsDTO = new ArrayList<>();
        List<Department> departments = departmentService.getAllDepartments();
        for (Department department : departments) {
            DepartmentDTO departmentDTO = new DepartmentDTO();
            departmentDTO.setId(department.getId());
            departmentDTO.setName(department.getName());
            departmentsDTO.add(departmentDTO);
        }
        model.addAttribute("departments", departmentsDTO);
//        return "departments";
        return "Department/index";
    }

    @GetMapping("/{id}")
    public String getDepartmentById(@PathVariable Long id, Model model) {
        Department department = departmentService.getDepartmentById(id);
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(department.getId());
        departmentDTO.setName(department.getName());
        model.addAttribute("department", departmentDTO);
        return "department";
    }

    @GetMapping("/add")
    public String addDepartmentForm(Model model) {
        model.addAttribute("departmentDTO", new DepartmentDTO());
        return "Department/add-department";
    }

    @PostMapping("/add")
    public String addDepartment(@ModelAttribute("departmentDTO") DepartmentDTO departmentDTO) {
        departmentService.addDepartment(departmentDTO);
        return "redirect:/departments";
    }

    @GetMapping("/{id}/update")
    public String updateDepartmentForm(@PathVariable Long id, Model model) {
        Department department = departmentService.getDepartmentById(id);
        DepartmentDTO departmentDTO = new DepartmentDTO();
        departmentDTO.setId(department.getId());
        departmentDTO.setName(department.getName());
        model.addAttribute("departmentDTO", departmentDTO);
        return "Department/update-department";
    }

    @PostMapping("/{id}/update")
    public String updateDepartment(@PathVariable Long id, @ModelAttribute("departmentDTO") DepartmentDTO departmentDTO) {
        departmentService.updateDepartment(id, departmentDTO);
        return "redirect:/departments";
    }

    @GetMapping("/{id}/delete")
    public String deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return "redirect:/departments";
    }
}
