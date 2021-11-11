/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.utils;

import com.ideas2it.project.model.dto.EmployeeDTO; 
import com.ideas2it.project.model.Employee;

/**
 * Maps between service and control layers for converting DTO objects to 
 * model objects and vice versa
 *
 * @version	1.0
 * @date	14 Oct 2021
 * @author	Sasikumar Raju
 */
public class EmployeeMapper {

    /**
     * Converts Employee to DTO
     *
     * @param employee, Employee to be converted
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
     * Converts DTO to Employee
     *
     * @param employeeDTO, EmployeeDTO to be converted
     * @return employee, Employee class 
     */       
    public static Employee convertDTOToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();

        employee.setId(employeeDTO.getId());        
        employee.setEmail(employeeDTO.getEmail());
        employee.setName(employeeDTO.getName());
        employee.setContact(employeeDTO.getContact());
        employee.setSalary(employeeDTO.getSalary());
        employee.setDob(employeeDTO.getDob());
        return employee;
    }
}








