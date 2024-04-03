package com.example.employeemanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProjectDTO {
    private Long id;
    private String name;
    private List<Long> departmentIds;
}
