//package com.example.employeemanagementsystem.service;
//
//import com.example.employeemanagementsystem.dto.ProjectDTO;
//import com.example.employeemanagementsystem.models.Department;
//import com.example.employeemanagementsystem.models.Project;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@Transactional
//public class ProjectService {
//
//    @Autowired
//    private ProjectRepository projectRepository;
//
//    public List<Project> getAllProjects() {
//        return projectRepository.findAll();
//    }
//
//    import java.util.Optional;
//
//    public ProjectDTO getProjectDTOById(Long id) {
//        Optional<Project> projectOptional = projectRepository.findById(id);
//        if (projectOptional.isPresent()) {
//            Project project = projectOptional.get();
//            ProjectDTO projectDTO = new ProjectDTO();
//            projectDTO.setId(project.getId());
//            projectDTO.setName(project.getName());
//            // Assuming departmentIds is accessible from Project entity
//            List<Long> departmentIds = new ArrayList<>();
//            for (Department department : project.getDepartments()) {
//                departmentIds.add(department.getId());
//            }
//            projectDTO.setDepartmentIds(departmentIds);
//            return projectDTO;
//        } else {
//            throw new RuntimeException("Project not found with id: " + id);
//        }
//    }
//
//
//    public void addProject(ProjectDTO projectDTO) {
//        Project project = new Project();
//        project.setId(projectDTO.getId());
//        project.setName(projectDTO.getName());
//        // Set departments or other fields if needed
//        projectRepository.save(project);
//    }
//
//    public void updateProject(Long id, ProjectDTO projectDTO) {
//        Optional<Project> projectOptional = projectRepository.findById(id);
//        if (projectOptional.isPresent()) {
//            Project project = projectOptional.get();
//            project.setId(id);
//            project.setName(projectDTO.getName());
//            // Update departments or other fields if needed
//            projectRepository.save(project);
//        } else {
//            throw new RuntimeException("Project not found with id: " + id);
//        }
//    }
//
//    public void deleteProject(Long id) {
//        projectRepository.deleteById(id);
//    }
//}
