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
import com.ideas2it.project.service.serviceImpl.EmployeeServiceImpl;
import com.ideas2it.project.model.dto.ProjectDTO;
import com.ideas2it.project.exception.CustomException;
/**
 * Manipulates the data between view and service layer
 *
 * @version	1.0
 * @author	Sasikumar Raju
 */
public class EmployeeController {
    private EmployeeServiceImpl employeeService = new EmployeeServiceImpl();

    /**
     * To check whether the given choice is valid, from EmployeeService class
     *
     * @param inputChoice, the choice given from EmployeeView
     * @return boolean
     */
    public boolean getChoiceValidated(int inputChoice) {
        return employeeService.getChoiceValidated(inputChoice);
    }

    /**
     * To check whether the given EmployeeId is valid, from EmployeeService
     *
     * @param employeeId, Employee ID to be validated
     * @return boolean
     */
    public boolean getEmployeeIdValidated(int employeeId) {
        return employeeService.getEmployeeIdValidated(employeeId);     
    }

    /**
     * To check whether the given Employee Name is valid from EmployeeService
     *
     * @param employeeName, Employee Name to be validated
     * @return boolean
     */
    public boolean getEmployeeNameValidated(String employeeName) {
        return employeeService.getEmployeeNameValidated(employeeName);      
    }
    
    /**
     * To check whether the given Employee Salary is valid, from 
     * EmployeeService
     * 
     * @param employeeSalary, Employee Salary to be validated
     * @return boolean
     */
    public boolean getEmployeeSalaryValidated(float employeeSalary) {
        return employeeService.getEmployeeSalaryValidated(employeeSalary);     
    }

    /**
     * To check whether the given Employee Email is valid, from EmployeeService
     *
     * @param employeeEmail, Employee Email to be validated
     * @return boolean
     */
    public boolean getEmployeeEmailValidated(String employeeEmail) {
        boolean isUnique = false;
        try {
            isUnique = employeeService.getEmployeeEmailValidated(employeeEmail);
        } catch(CustomException e) {
            EmployeeManagementLogger.logger.error(e);            
        }
        return isUnique;     
    }   

    /**
     * To check whether the given Employee Contact Number is valid,
     * from EmployeeService
     *
     * @param employeeContact, Employee Contact to be validated
     * @return boolean
     */
    public boolean getEmployeeContactValidated(long employeeContact) {
        boolean isUnique = false;
        try {
            isUnique = employeeService.getEmployeeContactValidated(employeeContact);
        } catch(CustomException e) {
            EmployeeManagementLogger.logger.error(e);
        }
        return isUnique;     
    }

    /**
     * To check whether the given Employee Date of Birth is valid,
     * from EmployeeService
     *
     * @param dateString, Employee dob as String to be validated
     * @return boolean
     */
    public boolean getValidatedDOB(LocalDate dob) {
        return employeeService.getValidatedDOB(dob);     
    }

    /**
     * To check whether the Door Number given by Employee  is valid,
     * from EmployeeService
     *
     * @param doorNumber, Employee doorNo to be validated
     * @return boolean
     */
    public boolean getDoorNoValidated(String doorNo) {
        return employeeService.getDoorNoValidated(doorNo);
    }

    /**
     * To check whether the City,Street,LandMark given by Employee  is valid,
     * from EmployeeService
     *
     * @param address, Employee city or street or landmark to be validated
     * @return boolean
     */
    public boolean getAddressValidated(String address) {
        return employeeService.getAddressValidated(address);
    }

    /**
     * To check whether the Pincode given by Employee  is valid,
     * from EmployeeService
     *
     * @param pincode, Employee pincode to be validated
     * @return boolean
     */
    public boolean getPincodeValidated(long pincode) {
        return employeeService.getPincodeValidated(pincode);
    }

    /**
     * To check whether records available from EmployeeService
     *
     * @return boolean
     */
    public boolean isRecordsAvailable() {
        boolean isAvailable = false;
        try {
            isAvailable = employeeService.isRecordsAvailable(); 
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
    public boolean containsEmployee(int employeeId) {
        boolean isAvailable = false;
        try {
            isAvailable = employeeService.containsEmployee(employeeId);
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
    public boolean createEmployee(EmployeeDTO employeeDTO) {
        boolean isCreated = false;
        try {
            isCreated = employeeService.createEmployee(employeeDTO);
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
    public List<EmployeeDTO> viewEmployee() {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        try {
            employeeDTOList = employeeService.viewEmployee();
        } catch(CustomException e) {
            EmployeeManagementLogger.logger.error(e);
        }
        return employeeDTOList;
    }

    /**
     * View All the project List from the EmployeeService
     *
     * @return List<ProjectDTO>, list of projects
     */
    public List<ProjectDTO> viewAllProject() {
        List<ProjectDTO> projectDTOList = new ArrayList<>();
        try {
            projectDTOList = employeeService.viewAllProject();
        } catch(CustomException e) {
            EmployeeManagementLogger.logger.error(e);
        }   
        return projectDTOList;
    }

    /**
     * To check whether Employee contains the project using Employee Service
     *
     * @param projectId, Project ID to be checked
     * @return boolean
     */
    public boolean containsProject(int projectId) {
        boolean isAvailable = false;
        try {
            isAvailable = employeeService.containsProject(projectId);
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
            projectDTO = employeeService.viewProjectById(projectId);
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
            employeeDTO = employeeService.viewEmployeeById(employeeId);
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
    public boolean deleteEmployeeById(int employeeId) {
        boolean isDeleted = false;
        try {
            isDeleted = employeeService.deleteEmployeeById(employeeId);
        } catch(CustomException e) {
            EmployeeManagementLogger.logger.error(e);
        }
        return isDeleted; 
    }

    /**
     * Delete the Address of given Address Id
     * 
     * @param AddressId, Address ID of the user given
     * @return boolean, true if address detail is deleted 
     */
    public boolean deleteAddress(EmployeeDTO employeeDTO) {
        boolean isDeleted = false;
        try {
            isDeleted = employeeService.deleteAddress(employeeDTO);
        } catch(CustomException e) {
            EmployeeManagementLogger.logger.error(e);
        }
        return isDeleted;
    }

    /**
     * Deletes All the Records
     */
    public boolean deleteAllEmployee() {
        boolean isDeleted = false;
        try {
            isDeleted = employeeService.deleteAllEmployee();
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
    public boolean updateAllDetails(EmployeeDTO employeeDTO) {
        boolean isUpdated = false;
        try {
            isUpdated = employeeService.updateAllDetails(employeeDTO);
        } catch(CustomException e) {
            EmployeeManagementLogger.logger.error(e);
        }
        return isUpdated;
    }
}








