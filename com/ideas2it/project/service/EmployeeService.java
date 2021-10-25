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

import com.ideas2it.project.dto.EmployeeDTO;
import com.ideas2it.project.mapper.EmployeeMapper;
import com.ideas2it.project.model.Employee;

/**
 * Performs the business logic for the Employee Management System
 *
 * @version	1.0
 * @author	Sasikumar Raju
 */
public class EmployeeService {

    private Map<Integer, Employee> employeeDetails = new HashMap<>();
    
    /**
     * To validate the given Choice in correct format using regex
     *
     * @return boolean valid or not 
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
     * @return boolean valid or not 
     * @param  employeeId
     */
    public boolean getEmployeeIdValidated(int employeeId) {
        String stringEmployeeId = String.valueOf(employeeId);
        String pattern = "^[0-9]{1,4}";
        return (stringEmployeeId.matches(pattern)) && (0 < employeeId);
    }

    /**
     * To validate the given Employee Name in correct format using regex
     *
     * @return boolean valid or not 
     * @param  employeeName
     */
    public boolean getEmployeeNameValidated(String employeeName) {
        String pattern = "[A-Za-z]+([ ][a-zA-Z]+)*";
        return (employeeName.matches(pattern));
    }

    /**
     * To validate the given Employee Salary in correct format using regex
     *
     * @return boolean valid or not 
     * @param  employeeSalary
     */
    public boolean getEmployeeSalaryValidated(float employeeSalary) {
        String stringSalary = String.valueOf(employeeSalary);
        return stringSalary.matches("^[0-9]{1,6}([\\.][0-9]{0,3})?$");
    }

    /**
     * To validate the given Employee Email in correct format using regex
     *
     * @return boolean valid or not 
     * @param  employeeEmail
     */
    public boolean getEmployeeEmailValidated(String employeeEmail) {
        String pattern = "[a-zA-Z0-9_\\.\\-]{3,}+[@][a-z]"
                                     + "+([\\.][a-z]{2,3})+";
        return employeeEmail.matches(pattern) 
                             && (!isEmailDuplicate(employeeEmail)); 
    }

    /**
     * To validate the given Employee Contact in correct format using regex
     *
     * @return boolean valid or not 
     * @param  employeeContact
     */
    public boolean getEmployeeContactValidated(long employeeContact) {
        String stringEmployeeContact = String.valueOf(employeeContact);
        String pattern = "[6-9][0-9]{9}";
        return stringEmployeeContact.matches(pattern) 
                                    && (!isContactDuplicate(employeeContact));
    }

    /**
     * To validate the given Employee Contact in correct format using regex
     *
     * @return boolean valid or not 
     * @param  employeeContact
     */
    public boolean getValidatedDOB(LocalDate dob) {
        Period period = Period.between(dob, LocalDate.now());
        return ((period.getYears() < 60) && (period.getYears() > 18));
    }
    
    /**
     * To update all details of an Employee
     *
     * @param employeeId
     * @param employeeDTO, EmployeeDTO containing the employee details
     */    
    public void updateAllDetails(int employeeId, EmployeeDTO employeeDTO) {
        Employee employee = employeeDetails.get(employeeId);
        employeeDetails.replace(employeeId, EmployeeMapper
                                .updateDTOToEmployee(employee, employeeDTO));
    }

    /**
     * Delete all the Records
     */
    public boolean deleteAllEmployee() {
        employeeDetails.clear();
        return employeeDetails.isEmpty();
    }

    /**
     * Delete the Records of given Employee Id
     * 
     * @param employeeId, ID of the user
     */
    public boolean deleteEmployeeById(int employeeId) {
        employeeDetails.remove(employeeId);
        return employeeDetails.containsKey(employeeId);
    }

    /**
     * Check the Records whether it contains given Employee Id
     * 
     * @param employeeId, ID of the user
     * @return boolean available
     */
    public boolean containsEmployee(int employeeId) {
        return employeeDetails.containsKey(employeeId);
    }

    /**
     * View the Records of given Employee Id
     * 
     * @param employeeId, ID of the user
     * @return employeeDTO of the Employee through EmployeeMapper class
     */
    public EmployeeDTO viewEmployeeById(int employeeId) {
        return EmployeeMapper.convertEmployeeToDTO(employeeDetails
                                                   .get(employeeId));
    }

    /**
     * View all the Records
     *
     * @return List<EmployeeDTO>, List of employee using EmployeeMapper class 
     */
    public List<EmployeeDTO> viewEmployee() {
        List<EmployeeDTO> viewList = new ArrayList<>();
        for(Integer key : employeeDetails.keySet()) {
            viewList.add(EmployeeMapper.convertEmployeeToDTO(employeeDetails
                                                             .get(key)));
        }
        return viewList;      
    }

    /**
     * Check whether any Records available.
     *  
     * @return boolean, Records avaiable 
     */
    public boolean isRecordsAvailable() {
        return employeeDetails.keySet().isEmpty();
    }

    /**
     * Create and store new Employee
     *
     * @param employeeId, Id of the user to be updated
     * @param employeeDTO, EmployeeDTO containing Employee details
     */
    public boolean createEmployee(EmployeeDTO employeeDTO) {
        return (null == employeeDetails.put(employeeDTO.getId(), EmployeeMapper
                           .convertDTOToEmployee(employeeDTO)));
    }
  
    /**
     * To check whether the given Employee Email is unique
     *
     * @return boolean unique or not 
     * @param  email
     */ 
    public boolean isEmailDuplicate(String email) {
        int duplicate = 0; 
        for (Integer key : employeeDetails.keySet()) {
            Employee employee =  employeeDetails.get(key);
            if(employee.getEmail().equals(email)) {
                duplicate++;
            }
        }
        return (0 != duplicate);
    }
    
    /**
     * To check whether the given Employee Contact Number is unique
     *
     * @return boolean unique or not 
     * @param  contact
     */
    public boolean isContactDuplicate(long contact) {
        int duplicate = 0;
        for (Integer key : employeeDetails.keySet()) {
            Employee employee =  employeeDetails.get(key);
            if(employee.getContact() == contact) {
                    duplicate++;
            }
        }
        return (0 != duplicate);
    }
}





