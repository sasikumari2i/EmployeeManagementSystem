/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.controller;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.project.model.dto.EmployeeDTO;
import com.ideas2it.project.service.serviceImpl.EmployeeServiceImpl;

/**
 * Manipulates the data between view and service layer
 *
 * @version	1.0
 * @author	Sasikumar Raju
 */
public class EmployeeController {
    private EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    /**
     * To check whether the given choice is valid, from EmployeeService class
     *
     * @param inputChoice, the choice given from EmployeeView
     * @return boolean
     */
    public boolean getChoiceValidated(int inputChoice) {
        return employeeService.getChoiceValidated(inputChoice);
    }

    /**
     * To check whether the given EmployeeId is valid, from EmployeeService
     *
     * @param employeeId, Employee ID to be validated
     * @return boolean
     */
    public boolean getEmployeeIdValidated(int employeeId) {
        return employeeService.getEmployeeIdValidated(employeeId);     
    }

    /**
     * To check whether the given Employee Name is valid from EmployeeService
     *
     * @param employeeName, Employee Name to be validated
     * @return boolean
     */
    public boolean getEmployeeNameValidated(String employeeName) {
        return employeeService.getEmployeeNameValidated(employeeName);      
    }
    
    /**
     * To check whether the given Employee Salary is valid, from 
     * EmployeeService
     * 
     * @param employeeSalary, Employee Salary to be validated
     * @return boolean
     */
    public boolean getEmployeeSalaryValidated(float employeeSalary) {
        return employeeService.getEmployeeSalaryValidated(employeeSalary);     
    }

    /**
     * To check whether the given Employee Email is valid, from EmployeeService
     *
     * @param employeeEmail, Employee Email to be validated
     * @return boolean
     */
    public boolean getEmployeeEmailValidated(String employeeEmail) {
        return employeeService.getEmployeeEmailValidated(employeeEmail);     
    }   

    /**
     * To check whether the given Employee Contact Number is valid,
     * from EmployeeService
     *
     * @param employeeContact, Employee Contact to be validated
     * @return boolean
     */
    public boolean getEmployeeContactValidated(long employeeContact) {
        return employeeService.getEmployeeContactValidated(employeeContact);     
    }

    /**
     * To check whether the given Employee Date of Birth is valid,
     * from EmployeeService
     *
     * @param dateString, Employee dob as String to be validated
     * @return boolean
     */
    public boolean getValidatedDOB(LocalDate dob) {
        return employeeService.getValidatedDOB(dob);     
    }

    /**
     * To check whether records available from EmployeeService
     *
     * @return boolean
     */
    public boolean isRecordsAvailable() {
        return employeeService.isRecordsAvailable();
    }

    /**
     * To check whether Employee Id is available from EmployeeService
     *
     * @param employeeId, Employee ID to be checked
     * @return boolean
     */
    public boolean containsEmployee(int employeeId) {
        return employeeService.containsEmployee(employeeId);
    }

    /**
     * To create new Employee
     *
     * @param employeeDTO, Details of the user
     */
    public boolean createEmployee(EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO);
    }    

    /**
     * View All the employee List from the EmployeeService
     *
     * @return List<EmployeeDTO>, list of employees
     */
    public List<EmployeeDTO> viewEmployee() {
        return employeeService.viewEmployee();
    }
    
    /**
     * View Employee details of the given ID from the EmployeeService
     *
     * @param employeeId, Employee ID of the user to be viewed
     * @return EmployeeDTO, Single employee
     */
    public EmployeeDTO viewEmployeeById(int employeeId) {
        return employeeService.viewEmployeeById(employeeId);
    }
    
    /**
     * Delete the Records of given Employee Id
     * 
     * @param employeeId, ID of the user
     * @return boolean, true if employee detail is deleted 
     */
    public boolean deleteEmployeeById(int employeeId) {
        return employeeService.deleteEmployeeById(employeeId);
    }

    /**
     * Deletes All the Records
     */
    public boolean deleteAllEmployee() {
        return employeeService.deleteAllEmployee(); 
    }
    
    /**
     * Update all Details given through employeeDTO for an Employee
     *
     * @param employeeDTO, EmployeeDTO object containing all records
     */
    public boolean updateAllDetails(EmployeeDTO employeeDTO) {
        return employeeService.updateAllDetails(employeeDTO);
    }
}








