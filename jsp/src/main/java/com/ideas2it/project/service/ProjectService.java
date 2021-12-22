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
import java.util.Set;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ideas2it.project.dao.daoImpl.ProjectDAOImpl;
import com.ideas2it.project.model.Address;
import com.ideas2it.project.model.dto.EmployeeDTO;
import com.ideas2it.project.model.dto.AddressDTO;
import com.ideas2it.project.model.Employee;
import com.ideas2it.project.service.EmployeeService;
import com.ideas2it.project.utils.EmployeeMapper;
import com.ideas2it.project.utils.ProjectMapper;
import com.ideas2it.project.model.dto.ProjectDTO;
import com.ideas2it.project.model.Project;
import com.ideas2it.project.service.serviceImpl.EmployeeServiceImpl;
import com.ideas2it.project.service.ProjectService;
import com.ideas2it.project.exception.CustomException;


/**
 * Performs the business logic for the Employee Management System
 *
 * @version	1.0
 * @author	Sasikumar Raju
 */
public interface ProjectService {

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
    public boolean getProjectIdValidated(int projectId);

    /**
     * To validate the given Employee Name in correct format using regex
     *
     * @return boolean, true if Name is valid 
     * @param  employeeName
     */
    public boolean getProjectNameValidated(String projectName);

    /**
     * To validate the given Employee Name in correct format using regex
     *
     * @return boolean, true if Name is valid 
     * @param  employeeName
     */
    public boolean getProjectDomainValidated(String domain);

    /**
     * To validate the given Employee Contact in correct format using regex
     *
     * @return boolean, true if date of birth is valid 
     * @param  employeeContact
     */
    public boolean getValidatedStartDate(LocalDate startDate);

    /**
     * To validate the given Employee Contact in correct format using regex
     *
     * @return boolean, true if date of birth is valid 
     * @param  employeeContact
     */
    public boolean getValidatedEndDate(LocalDate endDate);    

    /**
     * To update all details of an Employee
     *
     * @param addressDTO, AddressDTO containing the employee details
     * @return boolean, true if records are updated 
     */    
    public boolean updateAllDetails(ProjectDTO projectDTO) throws CustomException;
   
    /**
     * Delete all the Records
     * @return boolean, true if no employee records available
     */
    public boolean deleteAllProject() throws CustomException;

    /**
     * Delete the Records of given Employee Id
     * 
     * @param employeeId, ID of the user
     * @return boolean, true if employee detail is deleted 
     */
    public boolean deleteProjectById(int projectId) throws CustomException;

    /**
     * Check the Records whether it contains given Employee Id
     * 
     * @param employeeId, ID of the user
     * @return boolean, true if employee detail is available
     */
    public boolean containsProject(int projectId) throws CustomException;

    /**
     * View the Records of given Employee Id
     * 
     * @param employeeId, ID of the user
     * @return employeeDTO of the Employee through EmployeeMapper class
     */
    public ProjectDTO viewProjectById(int projectId) throws CustomException;

    /**
     * View all the Records
     *
     * @return List<EmployeeDTO>, List of employee using EmployeeMapper class 
     */
    public List<ProjectDTO> viewProject() throws CustomException;

    /**
     * To check the if project details available for the ID
     * 
     * @param projectId, ID of the Project
     * @return boolean, true if Project is available 
     */
    public boolean containsEmployee(int employeeId) throws CustomException;

    /**
     * View All the project details
     *
     * @return List<ProjectDTO>, list of project details
     */
    public List<EmployeeDTO> viewAllEmployee() throws CustomException;

    /**
     * View project details of the given project Id
     *
     * @return ProjectDTO, retrived Project
     */
    public EmployeeDTO viewEmployeeById(int employeeId) throws CustomException;

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
    public boolean createProject(ProjectDTO projectDTO) throws CustomException;
}





