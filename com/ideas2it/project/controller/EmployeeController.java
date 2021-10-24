/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.controller;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.project.dto.EmployeeDTO;
import com.ideas2it.project.service.EmployeeService;

/**
 * Manipulates the data between view and service layer
 *
 * @version	1.0
 * @author	Sasikumar Raju
 */
public class EmployeeController {
    private EmployeeService employeeService = new EmployeeService();

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
     * To check whether Employee Email is duplicate from EmployeeService
     *
     * @param employeeEmail, Employee Email to be checked
     * @return boolean
     */
    public boolean isEmailDuplicate(String employeeEmail) {
        return employeeService.isEmailDuplicate(employeeEmail);
    }

    /**
     * To check whether Employee Phone number is duplicate from EmployeeService
     *
     * @param employeeContact, Employee Contact to be checked
     * @return boolean
     */
    public boolean isContactDuplicate(long employeeContact) {
        return employeeService.isContactDuplicate(employeeContact);
    }

    /**
     * To create new Employee
     *
     * @param employeeId, Employee ID given from the new user
     * @param employeeDTO, Details of the user
     */
    public void createEmployee(EmployeeDTO employeeDTO) {
        employeeService.createEmployee(employeeDTO);
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
     * @param employeeId, Employee ID to be updated
     * @param employeeDTO, EmployeeDTO object containing all records
     */
    public void updateAllDetails(int employeeId, EmployeeDTO employeeDTO) {
        employeeService.updateAllDetails(employeeId, employeeDTO);
    }
}








