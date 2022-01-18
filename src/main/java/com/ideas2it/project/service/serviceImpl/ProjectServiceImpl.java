/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.service.serviceImpl;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.project.dao.ProjectDAO;
import com.ideas2it.project.exception.CustomException;
import com.ideas2it.project.model.Project;
import com.ideas2it.project.model.dto.EmployeeDTO;
import com.ideas2it.project.model.dto.ProjectDTO;
import com.ideas2it.project.service.EmployeeService;
import com.ideas2it.project.service.ProjectService;
import com.ideas2it.project.utils.ProjectMapper;

/**
 * Performs the business logic for the Project Management System
 *
 * @version 1.0
 * @author Sasikumar
 */
public class ProjectServiceImpl implements ProjectService {

	private ProjectDAO projectDAO;
	private EmployeeService employeeService;

	/**
	 * Setter method for ProjectService
	 */
	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	/**
	 * Setter method for ProjectDAO
	 */
	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}

	/**
	 * To validate the given Choice in correct format using regex
	 *
	 * @return boolean, true if choice is valid
	 * @param inputChoice
	 */
	public boolean getChoiceValidated(int inputChoice) {
		String stringChoice = String.valueOf(inputChoice);
		String stringPattern = "^[1-9]{1}";
		return (stringChoice.matches(stringPattern));
	}

	/**
     * To validate the given Project ID in correct format using regex
     *
     * @return boolean, true if Id is valid 
     * @param  projectId
     */
	public boolean getProjectIdValidated(int projectId) {
		String stringProjectId = String.valueOf(projectId);
		String pattern = "^[0-9]{1,4}";
		return (stringProjectId.matches(pattern)) && (0 < projectId);
	}

	/**
     * To validate the given Project Name in correct format using regex
     *
     * @return boolean, true if Name is valid 
     * @param  projectName
     */
	public boolean getProjectNameValidated(String projectName) {
		String pattern = "[A-Za-z]+([ ][a-zA-Z]+)*";
		return (projectName.matches(pattern));
	}

	/**
     * To validate the given domain Name in correct format using regex
     *
     * @return boolean, true if Name is valid 
     * @param  domain
     */
	public boolean getProjectDomainValidated(String domain) {
		String pattern = "[A-Za-z]+([ ][a-zA-Z]+)*";
		return (domain.matches(pattern));
	}

	/**
     * To validate the given startDate of the project is valid
     *
     * @return boolean, true if startDate is valid 
     * @param  startDate
     */
	public boolean getValidatedStartDate(LocalDate startDate) {
		Period period = Period.between(startDate, LocalDate.now());
		return ((period.getYears() < 60) && (period.getYears() > 18));
	}

	/**
     * To validate the given endDate of the project is valid
     *
     * @return boolean, true if endDate is valid 
     * @param  endDate
     */
	public boolean getValidatedEndDate(LocalDate endDate) {
		Period period = Period.between(endDate, LocalDate.now());
		return ((period.getYears() < 60) && (period.getYears() > 18));
	}

	/**
     * To update all details of an Project
     *
     * @param projectDTO, ProjectDTO containing the Project details
     * @return boolean, true if records are updated 
     */
	public boolean updateAllDetails(ProjectDTO projectDTO) throws CustomException {
		Project project = ProjectMapper.convertDTOToProject(projectDTO);
		return (null != projectDAO.updateProject(project));
	}

	/**
     * Delete all the Records
     * 
     * @return boolean, true if no project records available
     */
	public boolean deleteAllProject() throws CustomException {
		return projectDAO.deleteAllProject();
	}

	/**
     * Delete the Records of given Project Id
     * 
     * @param projectId, ID of the project
     * @return boolean, true if project detail is deleted 
     */
	public boolean deleteProjectById(int projectId) throws CustomException {
		return (null != projectDAO.deleteProjectById(projectId));
	}

	/**
     * Check the Records whether it contains given Project Id
     * 
     * @param projectId, ID of the Project
     * @return boolean, true if project detail is available
     */
	public boolean containsProject(int projectId) throws CustomException {
		return (null != projectDAO.viewProjectById(projectId));
	}

	/**
     * View the Records of given Project Id
     * 
     * @param projectId, ID of the Project
     * @return ProjectDTO of the Project 
     */
	public ProjectDTO viewProjectById(int projectId) throws CustomException {
		return ProjectMapper.convertProjectToDTO(projectDAO.viewProjectById(projectId));
	}

	/**
     * View all the Records
     *
     * @return List<ProjectDTO>, List of project 
     */
	public List<ProjectDTO> viewProject() throws CustomException {
		List<Project> projectDetails = projectDAO.viewProject();
		List<ProjectDTO> viewList = new ArrayList<>();
		for (Project project : projectDetails) {
			viewList.add(ProjectMapper.convertProjectToDTO(project));
		}
		return viewList;
	}

	/**
     * To check the if employee details available for the ID
     * 
     * @param employeeId, ID of the Employee
     * @return boolean, true if Employee is available 
     */
	public boolean containsEmployee(int employeeId) throws CustomException {
		return employeeService.containsEmployee(employeeId);
	}

	/**
     * View All the employee details
     *
     * @return List<EmployeeDTO>, list of employee details
     */
	public List<EmployeeDTO> viewAllEmployee() throws CustomException {
		List<EmployeeDTO> employeeDTOList = employeeService.viewEmployee();
		return employeeDTOList;
	}

	/**
     * View employee details of the given employeeId
     *
     * @return EmployeeDTO, retrieved Project
     */
	public EmployeeDTO viewEmployeeById(int employeeId) throws CustomException {
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
     * Create and store new Project
     *
     * @param ProjectDTO, ProjectDTO containing Employee details
     * @return boolean, true if null is return from projects
     */
	public boolean createProject(ProjectDTO projectDTO) throws CustomException {
		Project project = ProjectMapper.convertDTOToProject(projectDTO);
		return (null == projectDAO.createProject(project));
	}
}
