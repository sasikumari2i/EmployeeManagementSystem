/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.service;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.project.exception.CustomException;
import com.ideas2it.project.model.dto.EmployeeDTO;
import com.ideas2it.project.model.dto.ProjectDTO;


/**
 * Performs the business logic for the Project Management System
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
     * To validate the given Project ID in correct format using regex
     *
     * @return boolean, true if Id is valid 
     * @param  projectId
     */
    public boolean getProjectIdValidated(int projectId);

    /**
     * To validate the given Project Name in correct format using regex
     *
     * @return boolean, true if Name is valid 
     * @param  projectName
     */
    public boolean getProjectNameValidated(String projectName);

    /**
     * To validate the given domain Name in correct format using regex
     *
     * @return boolean, true if Name is valid 
     * @param  domain
     */
    public boolean getProjectDomainValidated(String domain);

    /**
     * To validate the given startDate of the project is valid
     *
     * @return boolean, true if startDate is valid 
     * @param  startDate
     */
    public boolean getValidatedStartDate(LocalDate startDate);

    /**
     * To validate the given endDate of the project is valid
     *
     * @return boolean, true if endDate is valid 
     * @param  endDate
     */
    public boolean getValidatedEndDate(LocalDate endDate);    

    /**
     * To update all details of an Project
     *
     * @param projectDTO, ProjectDTO containing the Project details
     * @return boolean, true if records are updated 
     */    
    public boolean updateAllDetails(ProjectDTO projectDTO) throws CustomException;
   
    /**
     * Delete all the Records
     * @return boolean, true if no project records available
     */
    public boolean deleteAllProject() throws CustomException;

    /**
     * Delete the Records of given Project Id
     * 
     * @param projectId, ID of the project
     * @return boolean, true if project detail is deleted 
     */
    public boolean deleteProjectById(int projectId) throws CustomException;

    /**
     * Check the Records whether it contains given Project Id
     * 
     * @param projectId, ID of the Project
     * @return boolean, true if project detail is available
     */
    public boolean containsProject(int projectId) throws CustomException;

    /**
     * View the Records of given Project Id
     * 
     * @param projectId, ID of the Project
     * @return ProjectDTO of the Project 
     */
    public ProjectDTO viewProjectById(int projectId) throws CustomException;

    /**
     * View all the Records
     *
     * @return List<ProjectDTO>, List of project 
     */
    public List<ProjectDTO> viewProject() throws CustomException;

    /**
     * To check the if employee details available for the ID
     * 
     * @param employeeId, ID of the Employee
     * @return boolean, true if Employee is available 
     */
    public boolean containsEmployee(int employeeId) throws CustomException;

    /**
     * View All the employee details
     *
     * @return List<EmployeeDTO>, list of employee details
     */
    public List<EmployeeDTO> viewAllEmployee() throws CustomException;

    /**
     * View employee details of the given employeeId
     *
     * @return EmployeeDTO, retrieved Project
     */
    public EmployeeDTO viewEmployeeById(int employeeId) throws CustomException;

    /**
     * Check whether any Records available.
     *  
     * @return boolean, true if no employee records are available 
     */
    public boolean isRecordsAvailable() throws CustomException;

    /**
     * Create and store new Project
     *
     * @param ProjectDTO, ProjectDTO containing Employee details
     * @return boolean, true if null is return from projects
     */
    public boolean createProject(ProjectDTO projectDTO) throws CustomException;
}





