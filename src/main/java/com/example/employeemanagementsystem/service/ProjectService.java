package com.example.employeemanagementsystem.service;

import com.example.employeemanagementsystem.dto.ProjectDTO;
import com.example.employeemanagementsystem.models.Department;
import com.example.employeemanagementsystem.models.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public ProjectDTO createProject(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setName(projectDTO.getName());

        List<Department> departments = new ArrayList<>();
        for (Long departmentId : projectDTO.getDepartmentIds()) {
            Department department = new Department();
            department.setId(departmentId);
            departments.add(department);
        }

        project.setDepartments(departments);

        project = projectRepository.save(project);

        ProjectDTO createdProjectDTO = new ProjectDTO();
        createdProjectDTO.setId(project.getId());
        createdProjectDTO.setName(project.getName());
        createdProjectDTO.setDepartmentIds(new ArrayList<>(project.getDepartments().size()));
        for (Department department : project.getDepartments()) {
            createdProjectDTO.getDepartmentIds().add(department.getId());
        }

        return createdProjectDTO;
    }

    public ProjectDTO getProjectById(Long id) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            ProjectDTO projectDTO = mapProjectToDTO(project);
            return projectDTO;
        } else {
            return null;
        }
    }

    private ProjectDTO mapProjectToDTO(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setName(project.getName());
        projectDTO.setDepartmentIds(mapDepartmentsToIds(project.getDepartments()));
        return projectDTO;
    }

    private List<Long> mapDepartmentsToIds(List<Department> departments) {
        List<Long> departmentIds = new ArrayList<>(departments.size());
        for (Department department : departments) {
            departmentIds.add(department.getId());
        }
        return departmentIds;
    }


    public List<ProjectDTO> getAllProjects() {
        List<Project> projects = projectRepository.findAll();
        List<ProjectDTO> projectDTOs = new ArrayList<>();
        for (Project project : projects) {
            ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.setId(project.getId());
            projectDTO.setName(project.getName());

            List<Long> departmentIds = new ArrayList<>(project.getDepartments().size());
            for (Department department : project.getDepartments()) {
                departmentIds.add(department.getId());
            }
            projectDTO.setDepartmentIds(departmentIds);

            projectDTOs.add(projectDTO);
        }
        return projectDTOs;
    }

    public ProjectDTO updateProject(Long id, ProjectDTO projectDTO) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        if (projectOptional.isPresent()) {
            Project project = projectOptional.get();
            project.setName(projectDTO.getName());

            List<Department> departments = new ArrayList<>();
            for (Long departmentId : projectDTO.getDepartmentIds()) {
                Department department = new Department();
                department.setId(departmentId);
                departments.add(department);
            }

            project.setDepartments(departments);

            project = projectRepository.save(project);

            ProjectDTO updatedProjectDTO = new ProjectDTO();
            updatedProjectDTO.setId(project.getId());
            updatedProjectDTO.setName(project.getName());

            List<Long> departmentIds = new ArrayList<>(project.getDepartments().size());
            for (Department department : project.getDepartments()) {
                departmentIds.add(department.getId());
            }
            updatedProjectDTO.setDepartmentIds(departmentIds);

            return updatedProjectDTO;
        } else {
            return null;
        }
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
