package com.example.employeemanagementsystem.service;

import com.example.employeemanagementsystem.models.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}
