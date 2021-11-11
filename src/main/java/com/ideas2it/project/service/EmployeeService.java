/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.time.LocalDate;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import com.ideas2it.project.model.dto.EmployeeDTO;

/**
 * Inteface to provide business logic for the Employee Management System
 *
 * @version	1.0
 * @author	Sasikumar Raju
 */
public interface EmployeeService {

    /**
     * To validate the given Choice in correct format using regex
     *
     * @return boolean, true if choice is valid  
     * @param  inputChoice
     */
    public boolean getChoiceValidated(int inputChoice);  
   
    /**
     * To validate the given Employee ID in correct format using regex
     *
     * @return boolean, true if Id is valid 
     * @param  employeeId
     */
    public boolean getEmployeeIdValidated(int employeeId);

    /**
     * To validate the given Employee Name in correct format using regex
     *
     * @return boolean, true if Name is valid 
     * @param  employeeName
     */
    public boolean getEmployeeNameValidated(String employeeName);

    /**
     * To validate the given Employee Salary in correct format using regex
     *
     * @return boolean, true if salary is valid 
     * @param  employeeSalary
     */
    public boolean getEmployeeSalaryValidated(float employeeSalary);

    /**
     * To validate the given Employee Email in correct format using regex
     *
     * @return boolean, true if email is valid 
     * @param  employeeEmail
     */
    public boolean getEmployeeEmailValidated(String employeeEmail);

    /**
     * To validate the given Employee Contact in correct format using regex
     *
     * @return boolean, true if Phone number is valid 
     * @param  employeeContact
     */
    public boolean getEmployeeContactValidated(long employeeContact);

    /**
     * To validate the given Employee Contact in correct format using regex
     *
     * @return boolean, true if date of birth is valid 
     * @param  employeeContact
     */
    public boolean getValidatedDOB(LocalDate dob);

    public boolean getDoorNoValidated(String doorNo);
    
    public boolean getLandMarkValidated(String landMark);

    public boolean getStreetValidated(String street);
    
    public boolean getCityValidated(String city);

    public boolean getPincodeValidated(long pincode);                

    /**
     * To update all details of an Employee
     *
     * @param employeeDTO, EmployeeDTO containing the employee details
     */    
    public boolean updateAllDetails(EmployeeDTO employeeDTO);

    /**
     * Delete all the Records
     * @return boolean, true if no employee records available
     */
    public boolean deleteAllEmployee();

    /**
     * Delete the Records of given Employee Id
     * 
     * @param employeeId, ID of the user
     * @return boolean, true if employee detail is available 
     */
    public boolean deleteEmployeeById(int employeeId);

    /**
     * Check the Records whether it contains given Employee Id
     * 
     * @param employeeId, ID of the user
     * @return boolean, true if employee detail is available
     */
    public boolean containsEmployee(int employeeId);

    /**
     * View the Records of given Employee Id
     * 
     * @param employeeId, ID of the user
     * @return employeeDTO of the Employee through EmployeeMapper class
     */
    public EmployeeDTO viewEmployeeById(int employeeId);

    /**
     * View all the Records
     *
     * @return List<EmployeeDTO>, List of employee using EmployeeMapper class 
     */
    public List<EmployeeDTO> viewEmployee();

    /**
     * Check whether any Records available.
     *  
     * @return boolean, true if no employee records are available 
     */
    public boolean isRecordsAvailable();

    /**
     * Create and store new Employee
     *
     * @param employeeDTO, EmployeeDTO containing Employee details
     * @return boolean, true if null is return from employees
     */
    public boolean createEmployee(EmployeeDTO employeeDTO);
}





