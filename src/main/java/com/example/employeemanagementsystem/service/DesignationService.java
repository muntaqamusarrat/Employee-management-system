package com.example.employeemanagementsystem.service;

import com.example.employeemanagementsystem.dto.DesignationDTO;
import com.example.employeemanagementsystem.models.Designation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DesignationService {

    @Autowired
    private DesignationRepository designationRepository;

    // In DesignationService
    public List<Designation> getAllDesignations() {
        return designationRepository.findAll();
    }

    public Designation getDesignationById(Long id) {
        Optional<Designation> designationOptional = designationRepository.findById(id);
        return designationOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Designation not found with id: " + id));
    }


    public void addDesignation(DesignationDTO designationDTO) {
        try {
            Designation designation = new Designation();
            designation.setName(designationDTO.getName());
            designationRepository.save(designation);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to add designation.", ex);
        }
    }



    public void updateDesignation(Long id, DesignationDTO designationDTO) {
        Optional<Designation> designationOptional = designationRepository.findById(id);
        if (designationOptional.isPresent()) {
            Designation designation = designationOptional.get();
            designation.setId(id);
            designation.setName(designationDTO.getName());
            designationRepository.save(designation);
        } else {
            throw new RuntimeException("Designation not found with id: " + id);
        }
    }

    public void deleteDesignation(Long id) {
        designationRepository.deleteById(id);
    }
}
