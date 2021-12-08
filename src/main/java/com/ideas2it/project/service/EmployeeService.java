/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ideas2it.project.dao.daoImpl.EmployeeDAOImpl;
import com.ideas2it.project.model.Address;
import com.ideas2it.project.model.dto.EmployeeDTO;
import com.ideas2it.project.model.dto.AddressDTO;
import com.ideas2it.project.model.dto.ProjectDTO;
import com.ideas2it.project.model.Employee;
import com.ideas2it.project.utils.EmployeeMapper;



/**
 * Interface to perform business logic for the employee management system
 *
 * @version	1.0
 * @author	Sasikumar 
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

    /**
     * To validate the given Door number in correct format using regex
     *
     * @return boolean, true if door number is valid 
     * @param doorNo, door number given by the user
     */
    public boolean getDoorNoValidated(String doorNo);

    /**
     * To validate the given city,street,landmark in correct format using regex
     *
     * @return boolean, true if each are valid 
     * @param  address, common for city,street,landmark
     */
    public boolean getAddressValidated(String address);

    /**
     * To validate the given Pincode in correct format using regex
     *
     * @return boolean, true if pincode is valid 
     * @param  pincode pincode given by the user
     */
    public boolean getPincodeValidated(long pincode);

    /**
     * To update all details of an Employee
     *
     * @param addressDTO, AddressDTO containing the employee details
     * @return boolean, true if records are updated 
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
     * @return boolean, true if employee detail is deleted 
     */
    public boolean deleteEmployeeById(int employeeId);

    /**
     * Deletes the Address given from the user
     * 
     * @param employeeDTO
     * @return boolean, true if employee address is deleted 
     */
    public boolean deleteAddress(EmployeeDTO employeeDTO);

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
     * To check the if project details available for the ID
     * 
     * @param projectId, ID of the Project
     * @return boolean, true if Project is available 
     */
    public boolean containsProject(int projectId);

    /**
     * View All the project details
     *
     * @return List<ProjectDTO>, list of project details
     */
    public List<ProjectDTO> viewAllProject();

    /**
     * View project details of the given project Id
     *
     * @return ProjectDTO, retrived Project
     */
    public ProjectDTO viewProjectById(int projectId);

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
     * @return boolean, true if null is return from projects
     */
    public boolean createEmployee(EmployeeDTO employeeDTO);
}





