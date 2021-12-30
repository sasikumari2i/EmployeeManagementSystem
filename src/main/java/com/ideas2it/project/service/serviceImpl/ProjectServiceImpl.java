/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.service.serviceImpl;

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

import com.ideas2it.project.dao.ProjectDAO;
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
 * @author	Sasikumar 
 */
public class ProjectServiceImpl implements ProjectService {

    private ProjectDAO projectDAO;
    private EmployeeService employeeService;
    
    
    public void setEmployeeService(EmployeeService employeeService) {
    	this.employeeService = employeeService;
    }
    
    public void setProjectDAO(ProjectDAO projectDAO) {
    	this.projectDAO = projectDAO;
    }
    
    /**
     * To validate the given Choice in correct format using regex
     *
     * @return boolean, true if choice is valid  
     * @param  inputChoice
     */
    public boolean getChoiceValidated(int inputChoice) {
        String stringChoice = String.valueOf(inputChoice);
        String stringPattern = "^[1-9]{1}";      
        return (stringChoice.matches(stringPattern));
    } 
   
    /**
     * To validate the given Employee ID in correct format using regex
     *
     * @return boolean, true if Id is valid 
     * @param  employeeId
     */
    public boolean getProjectIdValidated(int projectId) {
        String stringProjectId = String.valueOf(projectId);
        String pattern = "^[0-9]{1,4}";
        return (stringProjectId.matches(pattern)) && (0 < projectId);
    }

    /**
     * To validate the given Employee Name in correct format using regex
     *
     * @return boolean, true if Name is valid 
     * @param  employeeName
     */
    public boolean getProjectNameValidated(String projectName) {
        String pattern = "[A-Za-z]+([ ][a-zA-Z]+)*";
        return (projectName.matches(pattern));
    }

    /**
     * To validate the given Employee Name in correct format using regex
     *
     * @return boolean, true if Name is valid 
     * @param  employeeName
     */
    public boolean getProjectDomainValidated(String domain) {
        String pattern = "[A-Za-z]+([ ][a-zA-Z]+)*";
        return (domain.matches(pattern));
    }

    /**
     * To validate the given Employee Contact in correct format using regex
     *
     * @return boolean, true if date of birth is valid 
     * @param  employeeContact
     */
    public boolean getValidatedStartDate(LocalDate startDate) {
        Period period = Period.between(startDate, LocalDate.now());
        return ((period.getYears() < 60) && (period.getYears() > 18));
    }

    /**
     * To validate the given Employee Contact in correct format using regex
     *
     * @return boolean, true if date of birth is valid 
     * @param  employeeContact
     */
    public boolean getValidatedEndDate(LocalDate endDate) {
        Period period = Period.between(endDate, LocalDate.now());
        return ((period.getYears() < 60) && (period.getYears() > 18));
    }    

    /**
     * To update all details of an Employee
     *
     * @param addressDTO, AddressDTO containing the employee details
     * @return boolean, true if records are updated 
     */    
    public boolean updateAllDetails(ProjectDTO projectDTO) throws CustomException {
        Project project = ProjectMapper.convertDTOToProject(projectDTO);
        return (null != projectDAO.updateProject(project));
    }
   
    /**
     * Delete all the Records
     * @return boolean, true if no employee records available
     */
    public boolean deleteAllProject() throws CustomException {
        return projectDAO.deleteAllProject();
    }

    /**
     * Delete the Records of given Employee Id
     * 
     * @param employeeId, ID of the user
     * @return boolean, true if employee detail is deleted 
     */
    public boolean deleteProjectById(int projectId) throws CustomException {
        return (null != projectDAO.deleteProjectById(projectId));
    }

    /**
     * Check the Records whether it contains given Employee Id
     * 
     * @param employeeId, ID of the user
     * @return boolean, true if employee detail is available
     */
    public boolean containsProject(int projectId) throws CustomException {
        return (null != projectDAO.viewProjectById(projectId));
    }

    /**
     * View the Records of given Employee Id
     * 
     * @param employeeId, ID of the user
     * @return employeeDTO of the Employee through EmployeeMapper class
     */
    public ProjectDTO viewProjectById(int projectId) throws CustomException {
        return ProjectMapper.convertProjectToDTO(projectDAO
                                                 .viewProjectById(projectId));
    }

    /**
     * View all the Records
     *
     * @return List<EmployeeDTO>, List of employee using EmployeeMapper class 
     */
    public List<ProjectDTO> viewProject() throws CustomException {
        List<Project> projectDetails = projectDAO.viewProject();
        List<ProjectDTO> viewList = new ArrayList<>();
        for(Project project : projectDetails) {
            viewList.add(ProjectMapper.convertProjectToDTO(project));
        }
        return viewList;      
    }

    /**
     * To check the if project details available for the ID
     * 
     * @param projectId, ID of the Project
     * @return boolean, true if Project is available 
     */
    public boolean containsEmployee(int employeeId) throws CustomException {
        employeeService = new EmployeeServiceImpl();
        return employeeService.containsEmployee(employeeId);
    }

    /**
     * View All the project details
     *
     * @return List<ProjectDTO>, list of project details
     */
    public List<EmployeeDTO> viewAllEmployee() throws CustomException {
        employeeService = new EmployeeServiceImpl();
        List<EmployeeDTO> employeeDTOList = employeeService.viewEmployee(); 
        return employeeDTOList;
    }

    /**
     * View project details of the given project Id
     *
     * @return ProjectDTO, retrived Project
     */
    public EmployeeDTO viewEmployeeById(int employeeId) throws CustomException {
        employeeService = new EmployeeServiceImpl();
        return employeeService.viewEmployeeById(employeeId);
    }


    /**
     * Check whether any Records available.
     *  
     * @return boolean, true if no employee records are available 
     */
    public boolean isRecordsAvailable() throws CustomException {
        List<ProjectDTO> projectDetails = viewProject();
        return !(projectDetails.isEmpty());
    }

    /**
     * Create and store new Employee
     *
     * @param employeeDTO, EmployeeDTO containing Employee details
     * @return boolean, true if null is return from projects
     */
    public boolean createProject(ProjectDTO projectDTO) throws CustomException {
        Project project = ProjectMapper.convertDTOToProject(projectDTO);
        return (null == projectDAO.createProject(project));
    }
}





