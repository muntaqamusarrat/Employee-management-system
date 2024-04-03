package com.example.employeemanagementsystem.controllers;

import com.example.employeemanagementsystem.dto.EmployeeDTO;
import com.example.employeemanagementsystem.dto.ProjectDTO;
import com.example.employeemanagementsystem.dto.SkillDTO;
import com.example.employeemanagementsystem.models.*;
import com.example.employeemanagementsystem.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DesignationService designationService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private SkillService skillService;

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable("id") Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        return convertToDTO(employee);
    }

    @PostMapping
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setPhone(employeeDTO.getPhone());
        employee.setSalary(employeeDTO.getSalary());
        employee.setStartDate(employeeDTO.getStartDate());
        Department department = departmentService.getDepartmentById(employeeDTO.getDepartmentId());
        Designation designation = designationService.getDesignationById(employeeDTO.getDesignationId());

        employee.setDepartment(department);
        employee.setDesignation(designation.getName());

        employee.setProjects(employeeDTO.getProjectIds().stream()
                .map(id -> projectService.getProjectById(id))
                .map(projectDTO -> {
                    Project project = new Project();
                    project.setId(projectDTO.getId());

                    return project;
                })
                .collect(Collectors.toSet()));
        employee.setSkills(employeeDTO.getSkillIds().stream()
                .map(id -> {
                    SkillDTO skillDTO = skillService.getSkillById(id);
                    Skill skill = new Skill();

                    skill.setId(skillDTO.getId());
                    skill.setName(skillDTO.getName());

                    return skill;
                })
                .collect(Collectors.toSet()));

        employeeRepository.save(employee);
        return convertToDTO(employee);
    }

    @PutMapping("/{id}")
    public EmployeeDTO updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid employee Id:" + id));
        employee.setName(employeeDTO.getName());
        employee.setPhone(employeeDTO.getPhone());
        employee.setSalary(employeeDTO.getSalary());
        employee.setStartDate(employeeDTO.getStartDate());
        employee.setDepartment(departmentService.getDepartmentById(employeeDTO.getDepartmentId()));


        if (employeeDTO.getDesignationId() != null) {
            Designation designation = designationService.getDesignationById(employeeDTO.getDesignationId());
            employee.setDesignation(designation.getName());
        }

        employee.setProjects(mapProjectIdsToProjects(employeeDTO.getProjectIds()));
        employee.setSkills(mapSkillIdsToSkills(employeeDTO.getSkillIds()));

        employeeRepository.save(employee);
        return convertToDTO(employee);
    }

    private Set<Project> mapProjectIdsToProjects(Set<Long> projectIds) {
        return projectIds.stream()
                .map(projectId -> {
                    ProjectDTO projectDTO = projectService.getProjectById(projectId);
                    Project project = new Project();

                    project.setId(projectDTO.getId());
                    project.setName(projectDTO.getName());

                    return project;
                })
                .collect(Collectors.toSet());
    }


    private Set<Skill> mapSkillIdsToSkills(Set<Long> skillIds) {
        return skillIds.stream()
                .map(skillId -> {
                    SkillDTO skillDTO = skillService.getSkillById(skillId);
                    Skill skill = new Skill();

                    skill.setId(skillDTO.getId());
                    skill.setName(skillDTO.getName());

                    return skill;
                })
                .collect(Collectors.toSet());
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable("id") Long id) {
        employeeRepository.deleteById(id);
        return "Employee with id " + id + " has been deleted successfully.";
    }


    private EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setPhone(employee.getPhone());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setStartDate(employee.getStartDate());
        employeeDTO.setDepartmentId(employee.getDepartment().getId());
        employeeDTO.setProjectIds(employee.getProjects().stream().map(Project::getId).collect(Collectors.toSet()));
        employeeDTO.setSkillIds(employee.getSkills().stream().map(Skill::getId).collect(Collectors.toSet()));
        return employeeDTO;
    }
}
