/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.ideas2it.project.logger.EmployeeManagementLogger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ideas2it.project.model.dto.AddressDTO;
import com.ideas2it.project.model.dto.EmployeeDTO;
import com.ideas2it.project.service.serviceImpl.ProjectServiceImpl;
import com.ideas2it.project.model.dto.ProjectDTO;
import com.ideas2it.project.exception.CustomException;

/**
 * Manipulates the data between view and service layer
 *
 * @version	1.0
 * @author	Sasikumar Raju
 */
public class ProjectController {
    private ProjectServiceImpl projectService = new ProjectServiceImpl();

    /**
     * To check whether the given choice is valid, from EmployeeService class
     *
     * @param inputChoice, the choice given from EmployeeView
     * @return boolean
     */
    public boolean getChoiceValidated(int inputChoice) {
        return projectService.getChoiceValidated(inputChoice);
    }

    /**
     * To check whether the given EmployeeId is valid, from EmployeeService
     *
     * @param employeeId, Employee ID to be validated
     * @return boolean
     */
    public boolean getProjectIdValidated(int projectId) {
        return projectService.getProjectIdValidated(projectId);     
    }

    /**
     * To check whether the given Employee Name is valid from EmployeeService
     *
     * @param employeeName, Employee Name to be validated
     * @return boolean
     */
    public boolean getProjectNameValidated(String projectName) {
        return projectService.getProjectNameValidated(projectName);      
    }
    
    /**
     * To check whether the given Employee Salary is valid, from 
     * EmployeeService
     * 
     * @param employeeSalary, Employee Salary to be validated
     * @return boolean
     */
    public boolean getProjectDomainValidated(String domain) {
        return projectService.getProjectDomainValidated(domain);     
    }

    /**
     * To check whether the given Employee Email is valid, from EmployeeService
     *
     * @param employeeEmail, Employee Email to be validated
     * @return boolean
     */
    public boolean getValidatedStartDate(LocalDate startDate) {
        return projectService.getValidatedStartDate(startDate);     
    }   

    /**
     * To check whether the given Employee Contact Number is valid,
     * from EmployeeService
     *
     * @param employeeContact, Employee Contact to be validated
     * @return boolean
     */
    public boolean getValidatedEndDate(LocalDate endDate) {
        return projectService.getValidatedEndDate(endDate);     
    }

    /**
     * To check whether records available from EmployeeService
     *
     * @return boolean
     */
    public boolean isRecordsAvailable() {
        boolean isAvailable = false;
        try {
            isAvailable = projectService.isRecordsAvailable();    
        } catch(CustomException e) {
            EmployeeManagementLogger.logger.error(e);
        }
        return isAvailable;
    }

    /**
     * To check whether Employee Id is available from EmployeeService
     *
     * @param employeeId, Employee ID to be checked
     * @return boolean
     */
    public boolean containsProject(int projectId) {
        boolean isAvailable = false;
        try {
            isAvailable = projectService.containsProject(projectId);
        } catch(CustomException e) {
            EmployeeManagementLogger.logger.error(e);
        }
        return isAvailable;
    }

    /**
     * To create new Employee
     *
     * @param boolean, true if Employee created
     */
    public boolean createProject(ProjectDTO projectDTO) {
        boolean isCreated = false;
        try {
            isCreated = projectService.createProject(projectDTO);
        } catch(CustomException e) {
            EmployeeManagementLogger.logger.error(e);
        }
        return isCreated;
    }    

    /**
     * View All the employee List from the EmployeeService
     *
     * @return List<EmployeeDTO>, list of employees
     */
    public List<ProjectDTO> viewProject() {
        List<ProjectDTO> projectDTOList = new ArrayList<>();
        try {
            projectDTOList = projectService.viewProject();
        } catch(CustomException e) {
            EmployeeManagementLogger.logger.error(e);
        }
        return projectDTOList;
    }

    /**
     * View All the project List from the EmployeeService
     *
     * @return List<ProjectDTO>, list of projects
     */
    public List<EmployeeDTO> viewAllEmployee() {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        try {
            employeeDTOList = projectService.viewAllEmployee();
        } catch(CustomException e) {
            EmployeeManagementLogger.logger.error(e);
        }
        return employeeDTOList;
    }

    /**
     * To check whether Employee contains the project using Employee Service
     *
     * @param projectId, Project ID to be checked
     * @return boolean
     */
    public boolean containsEmployee(int employeeId) {
        boolean isAvailable = false;
        try {
            isAvailable = projectService.containsEmployee(employeeId);
        } catch(CustomException e) {
            EmployeeManagementLogger.logger.error(e);
        }
        return isAvailable;
    } 

    /**
     * View Project details of the given ID from the EmployeeService
     *
     * @param projectId, Project ID of the user to be viewed
     * @return ProjectDTO, Single project
     */
    public ProjectDTO viewProjectById(int projectId) {
        ProjectDTO projectDTO = new ProjectDTO();
        try {
            projectDTO = projectService.viewProjectById(projectId);
        } catch(CustomException e) {
            EmployeeManagementLogger.logger.error(e);
        }
        return projectDTO;
    }   

    /**
     * View Employee details of the given ID from the EmployeeService
     *
     * @param employeeId, Employee ID of the user to be viewed
     * @return EmployeeDTO, Single employee
     */
    public EmployeeDTO viewEmployeeById(int employeeId) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        try {
            employeeDTO = projectService.viewEmployeeById(employeeId);
        } catch(CustomException e) {
            EmployeeManagementLogger.logger.error(e);
        }
        return employeeDTO;
    }
    
    /**
     * Delete the Records of given Employee Id
     * 
     * @param employeeId, ID of the user
     * @return boolean, true if employee detail is deleted 
     */
    public boolean deleteProjectById(int projectId) {
        boolean isDeleted = false;
        try {
            isDeleted = projectService.deleteProjectById(projectId);
        } catch(CustomException e) {
            EmployeeManagementLogger.logger.error(e);
        }
        return isDeleted;
    }

    /**
     * Deletes All the Records
     */
    public boolean deleteAllProject() {
        boolean isDeleted = false;
        try {
            isDeleted = projectService.deleteAllProject(); 
        } catch(CustomException e) {
            EmployeeManagementLogger.logger.error(e);
        }
        return isDeleted; 
    }
    
    /**
     * Update all Details given through employeeDTO for an Employee
     *
     * @param employeeDTO, EmployeeDTO object containing all records
     */
    public boolean updateAllDetails(ProjectDTO projectDTO) {
        boolean isUpdated = false;
        try {
            isUpdated = projectService.updateAllDetails(projectDTO);
        } catch(CustomException e) {
            EmployeeManagementLogger.logger.error(e);
        }
        return isUpdated;
    }
}








