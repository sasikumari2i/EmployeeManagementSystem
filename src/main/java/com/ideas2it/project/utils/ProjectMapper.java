/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.utils;

import java.util.HashSet;
import java.util.Set;

import com.ideas2it.project.model.Employee;
import com.ideas2it.project.model.Project;
import com.ideas2it.project.model.dto.EmployeeDTO;
import com.ideas2it.project.model.dto.ProjectDTO;

/**
 * Maps between service and control layers for converting DTO objects to 
 * model objects and vice versa
 *
 * @version	1.0
 * @date	14 Oct 2021
 * @author	Sasikumar Raju
 */
public class ProjectMapper{
    //private static EmployeeMapper employeeMapper = new EmployeeMapper();
    
    /**
     * This method maps the project object to projectDto object
     *
     * @param project
     *
     * @return projectDto
     */
    public static ProjectDTO convertProjectToDTO(Project project) {
        Set<EmployeeDTO> employeeDTOSet = new HashSet<>();
        ProjectDTO projectDTO = new ProjectDTO();

        projectDTO.setId(project.getId());        
        projectDTO.setName(project.getName());
        projectDTO.setStartDate(project.getStartDate());
        projectDTO.setEndDate(project.getEndDate());
        projectDTO.setDomain(project.getDomain());
        projectDTO.setStatus(project.getStatus());
                
        if (null != project.getEmployees()) {
            for (Employee employee : project.getEmployees()) {
                employeeDTOSet.add(convertEmployeeToDTO(employee));
            }
            projectDTO.setEmployees(employeeDTOSet);
        }
        return projectDTO;
    }
    
    /**
     * This method maps the projectDto object to project object
     *
     * @param projectDto
     *
     * @return project
     */
    public static Project convertDTOToProject(ProjectDTO projectDTO) {
        Set<Employee> employeeSet = new HashSet<>();
        Project project = new Project();

        project.setId(projectDTO.getId());        
        project.setName(projectDTO.getName());
        project.setStartDate(projectDTO.getStartDate());
        project.setEndDate(projectDTO.getEndDate());
        project.setDomain(projectDTO.getDomain());
        project.setStatus(projectDTO.getStatus());

        if (null != projectDTO.getEmployees()) {
            for (EmployeeDTO employeeDTO : projectDTO.getEmployees()) {
                employeeSet.add(convertDTOToEmployee(employeeDTO));
            }
            project.setEmployees(employeeSet);
        }
        return project;
    }
    
    /**
     *  This method converts the employee object to employeeDto object
     * 
     *  @param employee
     * 
     *  @return employeeDto 
     */   
    public static EmployeeDTO convertEmployeeToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();   
        employeeDTO.setId(employee.getId());        
        employeeDTO.setName(employee.getName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setDob(employee.getDob());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setContact(employee.getContact());
        return employeeDTO;
    }
    
    /**
     *  This method converts the employeeDto object to employee object
     * 
     *  @param employee
     * 
     *  @return employeeDto 
     */
    public static Employee convertDTOToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();     
        employee.setId(employeeDTO.getId());        
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setDob(employeeDTO.getDob());
        employee.setSalary(employeeDTO.getSalary());
        employee.setContact(employeeDTO.getContact());
        return employee;
    }
}
   

