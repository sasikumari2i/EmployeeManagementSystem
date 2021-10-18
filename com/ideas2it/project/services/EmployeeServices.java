/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.services;

import com.ideas2it.project.model.Employee;
import com.ideas2it.project.dto.EmployeeDTO;
import com.ideas2it.project.mapper.EmployeeMapper;
import java.time.LocalDate;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.Map;
import java.util.HashMap;
import java.text.ParseException;
import java.util.List;
import java.util.ArrayList;

/**
 * EmployeeServices class gives the business logic for the model class
 *
 * @version	1.0
 * @date	14 Oct 2021
 * @author	Sasikumar Raju
 */
public class EmployeeServices {

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
        return stringEmployeeId.matches(pattern);
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
        String stringEmployeeSalary = String.valueOf(employeeSalary);
        String pattern = "^[0-9]{1,6}([\\.][\\d]{0,5})?";
        return stringEmployeeSalary.matches(pattern);
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
        return employeeEmail.matches(pattern); 
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
        return stringEmployeeContact.matches(pattern);
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
     * To update Name of an Employee
     *
     * @param employeeId, Id of the user to be updated
     * @param employeeName, Name of the user
     */
    public void updateEmployeeName(int employeeId, String employeeName) {
        Employee employee = employeeDetails.get(employeeId);
        employee.setName(employeeName);
        employeeDetails.put(employeeId, employee);
    }
 
    /**
     * To update Salary of an Employee
     *
     * @param employeeId, Id of the user to be updated
     * @param employeeSalary, Salary of the user
     */
    public void updateEmployeeSalary(int employeeId, float employeeSalary) {
        Employee employee = employeeDetails.get(employeeId);
        employee.setSalary(employeeSalary);
        employeeDetails.put(employeeId, employee);
    }
    
    /**
     * To update Email of an Employee
     *
     * @param employeeId, Id of the user to be updated
     * @param employeeEmail, Email of the user
     */    
    public void updateEmployeeEmail(int employeeId, String employeeEmail) {
        Employee employee = employeeDetails.get(employeeId);
        employee.setEmail(employeeEmail);
        employeeDetails.put(employeeId, employee);
    }
    
    /**
     * To update Date of Birth of an Employee
     *
     * @param employeeId, Id of the user to be updated
     * @param dob, Date of Birth of the user
     */
    public void updateEmployeeDOB(int employeeId, LocalDate employeeDob) {
        Employee employee = employeeDetails.get(employeeId);
        employee.setDob(employeeDob);
        employeeDetails.put(employeeId, employee);
    }
    
    /**
     * To update Contact Number of an Employee
     *
     * @param employeeId, Id of the user to be updated
     * @param employeeContact, Contact number of the user
     */
    public void updateEmployeeContact(int employeeId, long employeeContact) {
        Employee employee = employeeDetails.get(employeeId);
        employee.setContact(employeeContact);
        employeeDetails.put(employeeId, employee);
    }

    /**
     * Delete all the Records
     */
    public void deleteAllEmployee() {
        employeeDetails.clear();
    }

    /**
     * Delete the Records of given Employee Id
     * 
     * @param employeeId, ID of the user
     */
    public void deleteEmployeeById(int employeeId) {
        employeeDetails.remove(employeeId);
    }

    /**
     * Check the Records whether it contains given Employee Id
     * 
     * @param employeeId, ID of the user
     * @return boolean available
     */
    public boolean checkEmployeeId(int employeeId) {
        return employeeDetails.containsKey(employeeId);
    }

    /**
     * View the Records of given Employee Id
     * 
     * @param employeeId, ID of the user
     * @return employeeDTO of the Employee through EmployeeMapper class
     */
    public EmployeeDTO viewEmployeeById(int employeeId) {
        return EmployeeMapper.convertEmployeeToDTO(employeeDetails.get(employeeId));
    }

    /**
     * View all the Records
     *
     * @return List<EmployeeDTO>, List of employee using EmployeeMapper class 
     */
    public List<EmployeeDTO> viewEmployee() {
        List<EmployeeDTO> viewList = new ArrayList<>();
        for(Integer key : employeeDetails.keySet()) {
            viewList.add(EmployeeMapper.convertEmployeeToDTO(employeeDetails.get(key)));
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
    public void createEmployee(int employeeId, EmployeeDTO employeeDTO) {
        employeeDetails.put(employeeId,
                            EmployeeMapper.convertDTOToEmployee(employeeDTO));
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





