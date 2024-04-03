package com.example.employeemanagementsystem.service;

import com.example.employeemanagementsystem.dto.SkillDTO;
import com.example.employeemanagementsystem.models.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SkillService {

    @Autowired
    private SkillRepository skillRepository;

    public List<SkillDTO> getAllSkills() {
        List<Skill> skills = skillRepository.findAll();
        return skills.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public SkillDTO getSkillById(Long id) {
        Optional<Skill> skillOptional = skillRepository.findById(id);
        if (skillOptional.isPresent()) {
            Skill skill = skillOptional.get();
            return convertToDTO(skill);
        } else {
            throw new RuntimeException("Skill not found with id: " + id);
        }
    }

    public SkillDTO createSkill(SkillDTO skillDTO) {
        Skill skill = new Skill();
        skill.setName(skillDTO.getName());
        skill = skillRepository.save(skill);
        skillDTO.setId(skill.getId());
        return skillDTO;
    }

    public SkillDTO updateSkill(Long id, SkillDTO skillDTO) {
        Optional<Skill> skillOptional = skillRepository.findById(id);
        if (skillOptional.isPresent()) {
            Skill skill = skillOptional.get();
            skill.setName(skillDTO.getName());
            skill = skillRepository.save(skill);
            skillDTO.setId(skill.getId());
            return skillDTO;
        } else {
            throw new RuntimeException("Skill not found with id: " + id);
        }
    }

    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }

    private SkillDTO convertToDTO(Skill skill) {
        SkillDTO skillDTO = new SkillDTO();
        skillDTO.setId(skill.getId());
        skillDTO.setName(skill.getName());
        return skillDTO;
    }
}
