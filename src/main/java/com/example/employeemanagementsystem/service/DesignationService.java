package com.example.employeemanagementsystem.service;

import com.example.employeemanagementsystem.dto.DesignationDTO;
import com.example.employeemanagementsystem.models.Designation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DesignationService {

    @Autowired
    private DesignationRepository designationRepository;

    public List<Designation> getAllDesignations() {
        return designationRepository.findAll();
    }

    public Designation getDesignationById(Long id) {
        Optional<Designation> designationOptional = designationRepository.findById(id);
        if (designationOptional.isPresent()) {
            return designationOptional.get();
        } else {
            throw new RuntimeException("Designation not found with id: " + id);
        }
    }

    public void addDesignation(DesignationDTO designationDTO) {
        Designation designation = new Designation();
        designation.setId(designationDTO.getId());
        designation.setName(designationDTO.getName());
        designationRepository.save(designation);
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
