/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.service;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.project.exception.CustomException;
import com.ideas2it.project.model.dto.AddressDTO;
import com.ideas2it.project.model.dto.EmployeeDTO;
import com.ideas2it.project.model.dto.ProjectDTO;

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
    public boolean getEmployeeEmailValidated(String employeeEmail) throws CustomException;

    /**
     * To validate the given Employee Contact in correct format using regex
     *
     * @return boolean, true if Phone number is valid 
     * @param  employeeContact
     */
    public boolean getEmployeeContactValidated(long employeeContact) throws CustomException;

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
     * To validate whether date of birth, contact number and email id is unique to update Employee
     *
     * @param employeeDTO, EmployeeDTO returned for a specific employee from Database for the Employee Id
     * @param employee, user given employee details to update
     * @return String, a string if there is duplicate and null if there is no duplicates available 
     */
    public String employeeUniqueUpdate(EmployeeDTO employee, EmployeeDTO employeeDTO) throws CustomException;

    /**
     * To update all details of an Employee
     *
     * @param addressDTO, AddressDTO containing the employee details
     * @return boolean, true if records are updated 
     */    
    public boolean updateAllDetails(EmployeeDTO employeeDTO) throws CustomException;
    
    /**
     * To validate whether date of birth, contact number and email id is unique to create Employee
     *
     * @param employeeDTO, EmployeeDTO containing the employee details
     * @return String, a string if there is duplicate and null if there is no duplicates available 
     */
    public String employeeUniqueCreate(EmployeeDTO employeeDTO) throws CustomException;
   
    /**
     * Delete all the Records
     * @return boolean, true if no employee records available
     */
    public boolean deleteAllEmployee() throws CustomException;

    /**
     * Delete the Records of given Employee Id
     * 
     * @param employeeId, ID of the user
     * @return boolean, true if employee detail is deleted 
     */
    public boolean deleteEmployeeById(int employeeId) throws CustomException;

    /**
     * Deletes the Address given from the user
     * 
     * @param employeeDTO
     * @return boolean, true if employee address is deleted 
     */
    public boolean deleteAddress(EmployeeDTO employeeDTO) throws CustomException;
    
    /**
	 * Adds the Address given from the user
	 * 
	 * @param employeeDTO, EmployeeDTO for adding address
	 * @param addressDTO, Address to be added for the employee
	 * @return boolean, true if employee address is deleted
	 */
    public List<AddressDTO> addAddress(EmployeeDTO employeeDTO, AddressDTO addressDTO);

    /**
     * Check the Records whether it contains given Employee Id
     * 
     * @param employeeId, ID of the user
     * @return boolean, true if employee detail is available
     */
    public boolean containsEmployee(int employeeId) throws CustomException;

    /**
     * View the Records of given Employee Id
     * 
     * @param employeeId, ID of the user
     * @return employeeDTO of the Employee through EmployeeMapper class
     */
    public EmployeeDTO viewEmployeeById(int employeeId) throws CustomException;

    /**
     * View all the Records
     *
     * @return List<EmployeeDTO>, List of employee using EmployeeMapper class 
     */
    public List<EmployeeDTO> viewEmployee() throws CustomException;

    /**
     * To check the if project details available for the ID
     * 
     * @param projectId, ID of the Project
     * @return boolean, true if Project is available 
     */
    public boolean containsProject(int projectId) throws CustomException;

    /**
     * View All the project details
     *
     * @return List<ProjectDTO>, list of project details
     */
    public List<ProjectDTO> viewAllProject() throws CustomException;

    /**
     * View project details of the given project Id
     *
     * @return ProjectDTO, retrived Project
     */
    public ProjectDTO viewProjectById(int projectId) throws CustomException;

    /**
     * Check whether any Records available.
     *  
     * @return boolean, true if no employee records are available 
     */
    public boolean isRecordsAvailable() throws CustomException;

    /**
     * Create and store new Employee
     *
     * @param employeeDTO, EmployeeDTO containing Employee details
     * @return boolean, true if null is return from projects
     */
    public boolean createEmployee(EmployeeDTO employeeDTO) throws CustomException;
}





