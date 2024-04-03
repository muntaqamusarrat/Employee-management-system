package com.example.employeemanagementsystem.controllers;

import com.example.employeemanagementsystem.dto.DesignationDTO;
import com.example.employeemanagementsystem.models.Designation;
import com.example.employeemanagementsystem.service.DesignationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/designations")
public class DesignationController {

    @Autowired
    private DesignationService designationService;

    @GetMapping
    public ResponseEntity<List<DesignationDTO>> getAllDesignations() {
        List<Designation> designations = designationService.getAllDesignations();
        List<DesignationDTO> designationsDTO = convertToDTOList(designations); // Conversion here
        return ResponseEntity.ok(designationsDTO);
    }

    private List<DesignationDTO> convertToDTOList(List<Designation> designations) {
        List<DesignationDTO> designationDTOList = new ArrayList<>();
        for (Designation designation : designations) {
            DesignationDTO designationDTO = new DesignationDTO();
            designationDTO.setId(designation.getId());
            designationDTO.setName(designation.getName());
            // You can add additional properties if needed
            designationDTOList.add(designationDTO);
        }
        return designationDTOList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DesignationDTO> getDesignationById(@PathVariable Long id) {
        Designation designation = designationService.getDesignationById(id);
        if (designation != null) {
            DesignationDTO designationDTO = convertToDTO(designation); // Call convertToDTO method
            return ResponseEntity.ok(designationDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addDesignation(@RequestBody DesignationDTO designationDTO) {
        designationService.addDesignation(designationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Void> updateDesignation(@PathVariable Long id, @RequestBody DesignationDTO designationDTO) {
        designationService.updateDesignation(id, designationDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteDesignation(@PathVariable Long id) {
        designationService.deleteDesignation(id);
        return ResponseEntity.ok().build();
    }

    public DesignationDTO convertToDTO(Designation designation) {
        if (designation == null) {
            return null;
        }

        DesignationDTO designationDTO = new DesignationDTO();

        designationDTO.setId(designation.getId());
        designationDTO.setName(designation.getName());

        return designationDTO;
    }

}
