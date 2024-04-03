package com.example.employeemanagementsystem.controllers;

import com.example.employeemanagementsystem.dto.DesignationDTO;
import com.example.employeemanagementsystem.models.Designation;
import com.example.employeemanagementsystem.service.DesignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/designations")
public class DesignationController {

    @Autowired
    private DesignationService designationService;

    @GetMapping
    public String getAllDesignations(Model model) {
        List<DesignationDTO> designationsDTO = new ArrayList<>();
        List<Designation> designations = designationService.getAllDesignations();
        for (Designation designation : designations) {
            DesignationDTO designationDTO = new DesignationDTO();
            designationDTO.setId(designation.getId());
            designationDTO.setName(designation.getName());

            designationDTO.setDepartmentId(designation.getDepartment().getId());
            designationsDTO.add(designationDTO);
        }
        model.addAttribute("designations", designationsDTO);
        return "Designation/index";
    }

    @GetMapping("/{id}")
    public String getDesignationById(@PathVariable Long id, Model model) {
        Designation designation = designationService.getDesignationById(id);
        DesignationDTO designationDTO = new DesignationDTO();
        designationDTO.setId(designation.getId());
        designationDTO.setName(designation.getName());

        designationDTO.setDepartmentId(designation.getDepartment().getId());
        model.addAttribute("designation", designationDTO);
        return "Designation/show";
    }

    @GetMapping("/add")
    public String addDesignationForm(Model model) {
        model.addAttribute("designationDTO", new DesignationDTO());
        return "Designation/designation-form";
    }

    @PostMapping("/add")
    public String addDesignation(@ModelAttribute("designationDTO") DesignationDTO designationDTO) {
        designationService.addDesignation(designationDTO);
        return "redirect:/designations";
    }

    @GetMapping("/{id}/update")
    public String updateDesignationForm(@PathVariable Long id, Model model) {
        Designation designation = designationService.getDesignationById(id);
        DesignationDTO designationDTO = new DesignationDTO();
        designationDTO.setId(designation.getId());
        designationDTO.setName(designation.getName());

        designationDTO.setDepartmentId(designation.getDepartment().getId());
        model.addAttribute("designationDTO", designationDTO);
        return "Designation/update-designation";
    }

    @PostMapping("/{id}/update")
    public String updateDesignation(@PathVariable Long id, @ModelAttribute("designationDTO") DesignationDTO designationDTO) {
        designationService.updateDesignation(id, designationDTO);
        return "redirect:/designations";
    }

    @GetMapping("/{id}/delete")
    public String deleteDesignation(@PathVariable Long id) {
        designationService.deleteDesignation(id);
        return "redirect:/designations";
    }
}
