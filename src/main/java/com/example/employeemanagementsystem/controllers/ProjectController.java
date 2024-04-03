//package com.example.employeemanagementsystem.controllers;
//
//import com.example.employeemanagementsystem.dto.ProjectDTO;
//import com.example.employeemanagementsystem.models.Department;
//import com.example.employeemanagementsystem.models.Project;
//import com.example.employeemanagementsystem.service.ProjectService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Controller
//@RequestMapping("/projects")
//public class ProjectController {
//
//    @Autowired
//    private ProjectService projectService;
//
//    @GetMapping
//    public String getAllProjects(Model model) {
//        List<ProjectDTO> projectsDTO = new ArrayList<>();
//        List<Project> projects = projectService.getAllProjects();
//        for (Project project : projects) {
//            ProjectDTO projectDTO = new ProjectDTO();
//            projectDTO.setId(project.getId());
//            projectDTO.setName(project.getName());
//            // Assuming departmentIds is accessible from Project entity
//            List<Long> departmentIds = new ArrayList<>();
//            for (Department department : project.getDepartments()) {
//                departmentIds.add(department.getId());
//            }
//            projectDTO.setDepartmentIds(departmentIds);
//            projectsDTO.add(projectDTO);
//        }
//        model.addAttribute("projects", projectsDTO);
//        return "projects/index"; // Assuming the view directory is 'projects' and file is 'index.html'
//    }
//
//    @GetMapping("/new")
//    public String showProjectForm(Model model) {
//        model.addAttribute("projectDTO", new ProjectDTO());
//        return "projects/project-form"; // Assuming the view directory is 'projects' and file is 'project-form.html'
//    }
//
//    @PostMapping
//    public String saveProject(@ModelAttribute("projectDTO") ProjectDTO projectDTO) {
//        projectService.addProject(projectDTO);
//        return "redirect:/projects";
//    }
//
//    @GetMapping("/{id}/edit")
//    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
//        ProjectDTO projectDTO = projectService.getProjectDTOById(id);
//        model.addAttribute("projectDTO", projectDTO);
//        return "projects/project-form"; // Assuming the view directory is 'projects' and file is 'project-form.html'
//    }
//
//    @PostMapping("/{id}/edit")
//    public String updateProject(@PathVariable("id") Long id, @ModelAttribute("projectDTO") ProjectDTO projectDTO) {
//        projectDTO.setId(id);
//        projectService.updateProject(projectDTO);
//        return "redirect:/projects";
//    }
//
//    @GetMapping("/{id}/delete")
//    public String deleteProject(@PathVariable("id") Long id) {
//        projectService.deleteProject(id);
//        return "redirect:/projects";
//    }
//}
