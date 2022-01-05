/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.service.serviceImpl;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.project.dao.EmployeeDAO;
import com.ideas2it.project.exception.CustomException;
import com.ideas2it.project.model.Employee;
import com.ideas2it.project.model.dto.AddressDTO;
import com.ideas2it.project.model.dto.EmployeeDTO;
import com.ideas2it.project.model.dto.ProjectDTO;
import com.ideas2it.project.service.EmployeeService;
import com.ideas2it.project.service.ProjectService;
import com.ideas2it.project.utils.EmployeeMapper;

/**
 * Performs the business logic for the Employee Management System
 *
 * @version 1.0
 * @author Sasikumar
 */
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeDAO employeeDAO;
	private ProjectService projectService;

	public void setEmployeeDAO(EmployeeDAO employeeDAO) {
		this.employeeDAO = employeeDAO;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
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
	 * To validate the given Employee ID in correct format using regex
	 *
	 * @return boolean, true if Id is valid
	 * @param employeeId
	 */
	public boolean getEmployeeIdValidated(int employeeId) {
		String stringEmployeeId = String.valueOf(employeeId);
		String pattern = "^[0-9]{1,4}";
		return (stringEmployeeId.matches(pattern)) && (0 < employeeId);
	}

	/**
	 * To validate the given Employee Name in correct format using regex
	 *
	 * @return boolean, true if Name is valid
	 * @param employeeName
	 */
	public boolean getEmployeeNameValidated(String employeeName) {
		String pattern = "[A-Za-z]+([ ][a-zA-Z]+)*";
		return (employeeName.matches(pattern));
	}

	/**
	 * To validate the given Employee Salary in correct format using regex
	 *
	 * @return boolean, true if salary is valid
	 * @param employeeSalary
	 */
	public boolean getEmployeeSalaryValidated(float employeeSalary) {
		String stringSalary = String.valueOf(employeeSalary);
		return stringSalary.matches("^[0-9]{1,6}([\\.][0-9]{0,3})?$");
	}

	/**
	 * To validate the given Employee Email in correct format using regex
	 *
	 * @return boolean, true if email is valid
	 * @param employeeEmail
	 */
	public boolean getEmployeeEmailValidated(String employeeEmail) throws CustomException {
		// String pattern = "[a-zA-Z0-9_\\.\\-]{3,}+[@][a-z]"
		// + "+([\\.][a-z]{2,3})+";
		return (null != employeeDAO.containsEmployeeEmail(employeeEmail));
	}

	/**
	 * To validate the given Employee Contact in correct format using regex
	 *
	 * @return boolean, true if Phone number is valid
	 * @param employeeContact
	 */
	public boolean getEmployeeContactValidated(long employeeContact) throws CustomException {
		String stringEmployeeContact = String.valueOf(employeeContact);
		// String pattern = "[6-9][0-9]{9}";
		return (null != employeeDAO.containsEmployeeContact(stringEmployeeContact));
	}

	/**
	 * To validate the given Employee Contact in correct format using regex
	 *
	 * @return boolean, true if date of birth is valid
	 * @param employeeContact
	 */
	public boolean getValidatedDOB(LocalDate dob) {
		Period period = Period.between(dob, LocalDate.now());
		return !((period.getYears() < 60) && (period.getYears() > 18));
	}

	/**
	 * To validate the given Door number in correct format using regex
	 *
	 * @return boolean, true if door number is valid
	 * @param doorNo, door number given by the user
	 */
	public boolean getDoorNoValidated(String doorNo) {
		String pattern = "[\\w&&[^_]]+[/-]{0,1}[\\w&&[^_]]+";
		return (doorNo.matches(pattern));
	}

	/**
	 * To validate the given city,street,landmark in correct format using regex
	 *
	 * @return boolean, true if each are valid
	 * @param address, common for city,street,landmark
	 */
	public boolean getAddressValidated(String address) {
		String pattern = "[A-Za-z0-9]+([ ][a-zA-Z0-9]+)*";
		return (address.matches(pattern));
	}

	/**
	 * To validate the given Pincode in correct format using regex
	 *
	 * @return boolean, true if pincode is valid
	 * @param pincode pincode given by the user
	 */
	public boolean getPincodeValidated(long pincode) {
		String stringPincode = String.valueOf(pincode);
		String pattern = "[1-9][0-9]{5}";
		return (stringPincode.matches(pattern));
	}

	/**
	 * To update all details of an Employee
	 *
	 * @param addressDTO, AddressDTO containing the employee details
	 * @return boolean, true if records are updated
	 */
	public boolean updateAllDetails(EmployeeDTO employeeDTO) throws CustomException {
		Employee employee = EmployeeMapper.convertDTOToEmployee(employeeDTO);
		return (null != employeeDAO.updateEmployee(employee));
	}

	/**
	 * Delete all the Records
	 * 
	 * @return boolean, true if no employee records available
	 */
	public boolean deleteAllEmployee() throws CustomException {
		return employeeDAO.deleteAllEmployee();
	}

	/**
	 * Delete the Records of given Employee Id
	 * 
	 * @param employeeId, ID of the user
	 * @return boolean, true if employee detail is deleted
	 */
	public boolean deleteEmployeeById(int employeeId) throws CustomException {
		return (null != employeeDAO.deleteEmployeeById(employeeId));
	}

	/**
	 * Deletes the Address given from the user
	 * 
	 * @param employeeDTO
	 * @return boolean, true if employee address is deleted
	 */
	public boolean deleteAddress(EmployeeDTO employeeDTO) throws CustomException {
		Employee employee = EmployeeMapper.convertDTOToEmployee(employeeDTO);
		return (null != employeeDAO.updateEmployee(employee));
	}

	public List<AddressDTO> addAddress(EmployeeDTO employeeDTO, AddressDTO addressDTO) {
		List<AddressDTO> addressList = new ArrayList<>();
		addressList = employeeDTO.getAddress();
		addressList.add(addressDTO);
		int count = 1;
		for (AddressDTO address : addressList) {
			address.setSerialId(count);
			count++;
		}
		return addressList;
	}

	/**
	 * Check the Records whether it contains given Employee Id
	 * 
	 * @param employeeId, ID of the user
	 * @return boolean, true if employee detail is available
	 */
	public boolean containsEmployee(int employeeId) throws CustomException {
		return (null != employeeDAO.viewEmployeeById(employeeId));
	}

	/**
	 * View the Records of given Employee Id
	 * 
	 * @param employeeId, ID of the user
	 * @return employeeDTO of the Employee through EmployeeMapper class
	 */
	public EmployeeDTO viewEmployeeById(int employeeId) throws CustomException {
		return EmployeeMapper.convertEmployeeToDTO(employeeDAO.viewEmployeeById(employeeId));
	}

	/**
	 * View all the Records
	 *
	 * @return List<EmployeeDTO>, List of employee using EmployeeMapper class
	 */
	public List<EmployeeDTO> viewEmployee() throws CustomException {
		List<Employee> employeeDetails = employeeDAO.viewEmployee();
		List<EmployeeDTO> viewList = new ArrayList<>();
		for (Employee employee : employeeDetails) {
			viewList.add(EmployeeMapper.convertEmployeeToDTO(employee));
		}
		return viewList;
	}

	/**
	 * To check the if project details available for the ID
	 * 
	 * @param projectId, ID of the Project
	 * @return boolean, true if Project is available
	 */
	public boolean containsProject(int projectId) throws CustomException {
		return projectService.containsProject(projectId);
	}

	/**
	 * View All the project details
	 *
	 * @return List<ProjectDTO>, list of project details
	 */
	public List<ProjectDTO> viewAllProject() throws CustomException {
		List<ProjectDTO> projectDTOList = projectService.viewProject();
		return projectDTOList;
	}

	/**
	 * View project details of the given project Id
	 *
	 * @return ProjectDTO, retrived Project
	 */
	public ProjectDTO viewProjectById(int projectId) throws CustomException {
		return projectService.viewProjectById(projectId);
	}

	/**
	 * Check whether any Records available.
	 * 
	 * @return boolean, true if no employee records are available
	 */
	public boolean isRecordsAvailable() throws CustomException {
		List<EmployeeDTO> employeeDetails = viewEmployee();
		return !(employeeDetails.isEmpty());
	}

	/**
	 * Create and store new Employee
	 *
	 * @param employeeDTO, EmployeeDTO containing Employee details
	 * @return boolean, true if null is return from projects
	 */
	public boolean createEmployee(EmployeeDTO employeeDTO) throws CustomException {
		Employee employee = EmployeeMapper.convertDTOToEmployee(employeeDTO);
		return (null == employeeDAO.createEmployee(employee));
	}
}
