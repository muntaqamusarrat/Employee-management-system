package com.example.employeemanagementsystem.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class EmployeeDTO {
    private Long id;
    private String name;
    private String departmentName;
    private String phone;
    private double salary;
    private Date startDate;
    private String designation;
    private Set<Long> projectIds;
    private Set<Long> skillIds;
}
