/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.mapper;

import com.ideas2it.project.dto.EmployeeDTO; 
import com.ideas2it.project.model.Employee;

/**
 * To Map between service and control layers for converting DTO objects to 
 * model objects and vice versa
 *
 * @version	1.0
 * @date	14 Oct 2021
 * @author	Sasikumar Raju
 */
public class EmployeeMapper {
 
    /**
     * To convert Employee object to DTO by creating new DTO instance
     *
     * @param employee, Employee object to be converted
     * @return employeeDTO, DTO object
     */   
    public static EmployeeDTO convertEmployeeToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        
        employeeDTO.setId(employee.getId());        
        employeeDTO.setName(employee.getName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setContact(employee.getContact());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setDob(employee.getDob());
        return employeeDTO;
    }

    /**
     * To convert DTO to Employee object by creating new Employee instance
     *
     * @param employeeDTO, EmployeeDTO instance to be converted
     * @return employee, Employee class 
     */       
    public static Employee convertDTOToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        
        employee.setId(employeeDTO.getId());        
        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setContact(employeeDTO.getContact());
        employee.setSalary(employeeDTO.getSalary());
        employee.setDob(employeeDTO.getDob());
        return employee;
    }
    
    /**
     * To convert DTO to Employee object for the given details
     *
     * @param employee, Employee instance to be converted
     * @param employeeDTO, EmployeeDTO
     * @return employee, Employee class
     */
    public static Employee updateDTOToEmployee(Employee employee, 
                                               EmployeeDTO employeeDTO) {

        employee.setName(employeeDTO.getName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setContact(employeeDTO.getContact());
        employee.setSalary(employeeDTO.getSalary());
        employee.setDob(employeeDTO.getDob());
        return employee;
    }
}








