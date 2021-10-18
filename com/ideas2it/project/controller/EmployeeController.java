/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.controller;

import com.ideas2it.project.dto.EmployeeDTO;
import com.ideas2it.project.services.EmployeeServices;
import java.time.LocalDate;
import java.util.List;

/**
 * Manipulates the data between view and service model
 *
 * @version	1.0
 * @date	14 Oct 2021
 * @author	Sasikumar Raju
 */
public class EmployeeController {
    EmployeeServices services = new EmployeeServices();

    /**
     * To check whether the given choice is valid, from EmployeeServices class
     *
     * @param inputChoice, the choice given from EmployeeView
     * @return boolean
     */
    public boolean getChoiceValidated(int inputChoice) {
        return services.getChoiceValidated(inputChoice);
    }

    /**
     * To check whether the given EmployeeId is valid, from EmployeeServices
     *
     * @param employeeId, Employee ID to be validated
     * @return boolean
     */
    public boolean getEmployeeIdValidated(int employeeId) {
        return services.getEmployeeIdValidated(employeeId);     
    }

    /**
     * To check whether the given Employee Name is valid from EmployeeServices
     *
     * @param employeeName, Employee Name to be validated
     * @return boolean
     */
    public boolean getEmployeeNameValidated(String employeeName) {
        return services.getEmployeeNameValidated(employeeName);      
    }
    
    /**
     * To check whether the given Employee Salary is valid, from 
     * EmployeeServices
     * 
     * @param employeeSalary, Employee Salary to be validated
     * @return boolean
     */
    public boolean getEmployeeSalaryValidated(float employeeSalary) {
        return services.getEmployeeSalaryValidated(employeeSalary);     
    }

    /**
     * To check whether the given Employee Email is valid, from EmployeeServices
     *
     * @param employeeEmail, Employee Email to be validated
     * @return boolean
     */
    public boolean getEmployeeEmailValidated(String employeeEmail) {
        return services.getEmployeeEmailValidated(employeeEmail);     
    }   

    /**
     * To check whether the given Employee Contact Number is valid,
     * from EmployeeServices
     *
     * @param employeeContact, Employee Contact to be validated
     * @return boolean
     */
    public boolean getEmployeeContactValidated(long employeeContact) {
        return services.getEmployeeContactValidated(employeeContact);     
    }

    /**
     * To check whether records available from EmployeeServices
     *
     * @return boolean
     */
    public boolean isRecordsAvailable() {
        return services.isRecordsAvailable();
    }

    /**
     * To check whether Employee Id is available from EmployeeServices
     *
     * @param employeeId, Employee ID to be checked
     * @return boolean
     */
    public boolean checkEmployeeId(int employeeId) {
        return services.checkEmployeeId(employeeId);
    }

    /**
     * To check whether Employee Email is duplicate from EmployeeServices
     *
     * @param employeeEmail, Employee Email to be checked
     * @return boolean
     */
    public boolean isEmailDuplicate(String employeeEmail) {
        return services.isEmailDuplicate(employeeEmail);
    }

    /**
     * To check whether Employee Phone number is duplicate from EmployeeServices
     *
     * @param employeeContact, Employee Contact to be checked
     * @return boolean
     */
    public boolean isContactDuplicate(long employeeContact) {
        return services.isContactDuplicate(employeeContact);
    }

    /**
     * To create new Employee
     *
     * @param employeeId, Employee ID given from the new user
     * @param employeeDTO, Details of the user
     */
    public void createEmployee(int employeeId, EmployeeDTO employeeDTO) {
        services.createEmployee(employeeId, employeeDTO);
    }    

    /**
     * View All the employee List from the EmployeeServices
     *
     * @return List<EmployeeDTO>, list of employees
     */
    public List<EmployeeDTO> viewEmployee() {
        return services.viewEmployee();
    }
    
    /**
     * View Employee details of the given ID from the EmployeeServices
     *
     * @param employeeId, Employee ID of the user to be viewed
     * @return EmployeeDTO, Single employee
     */
    public EmployeeDTO viewEmployeeById(int employeeId) {
        return services.viewEmployeeById(employeeId);
    }
    
    
    public void deleteEmployeeById(int employeeId) {
        services.deleteEmployeeById(employeeId);
    }

    /**
     * Deletes All the Records
     */
    public void deleteAllEmployee() {
        services.deleteAllEmployee();
    }
    
    /**
     * Update all Details given through employeeDTO for an Employee
     *
     * @param employeeId, Employee ID to be updated
     * @param employeeDTO, EmployeeDTO object containing all records
     */
    public void updateAllDetails(int employeeId, EmployeeDTO employeeDTO) {
        services.updateAllDetails(employeeId, employeeDTO);
    }

    /**
     * Update Employee Name for an Employee
     *
     * @param employeeId, Employee ID to be updated
     * @param employeeName
     */   
    public void updateEmployeeName(int employeeId, String employeeName) {
        services.updateEmployeeName(employeeId, employeeName);
    }
    
    /**
     * Update Employee Salary for an Employee
     *
     * @param employeeId, Employee ID to be updated
     * @param employeeSalary
     */
    public void updateEmployeeSalary(int employeeId, float employeeSalary) {
        services.updateEmployeeSalary(employeeId, employeeSalary);
    }

    /**
     * Update Employee Email for an Employee
     *
     * @param employeeId, Employee ID to be updated
     * @param employeeEmail
     */
    public void updateEmployeeEmail(int employeeId, String employeeEmail) {
        services.updateEmployeeEmail(employeeId, employeeEmail);
    }

    /**
     * Update Employee DOB for an Employee
     *
     * @param employeeId, Employee ID to be updated
     * @param employeeDob, Date of Birth to be updated
     */
    public void updateEmployeeDob(int employeeId, LocalDate employeeDob) {
        services.updateEmployeeDOB(employeeId, employeeDob);
    }

    /**
     * Update Employee Contact for an Employee
     *
     * @param employeeId, Employee ID to be updated
     * @param employeeContact
     */
    public void updateEmployeeContact(int employeeId, long employeeContact) {
        services.updateEmployeeContact(employeeId, employeeContact);
    }

}








