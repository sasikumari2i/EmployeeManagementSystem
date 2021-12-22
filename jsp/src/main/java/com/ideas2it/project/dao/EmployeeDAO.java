/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.dao;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.project.model.Address;
import com.ideas2it.project.model.Employee;
import com.ideas2it.project.utils.DatabaseConnection;
import com.ideas2it.project.exception.CustomException;

/**
 * Interface to provide methods to interact with the database
 *
 * @version	1.0
 * @author	Sasikumar Raju
 */
public interface EmployeeDAO {
   
    /**
     * Inserts new employee details to the database
     *
     * @return Employee, employee which is inserted
     * @param  Employee, Employee to be inserted
     */
    public Employee createEmployee(Employee employee) throws CustomException;

    /**
     * Update the employee details of an employee
     *
     * @return Employee, returns the updated employee 
     * @param  Employee, Employee to be updated
     */
    public Employee updateEmployee(Employee employee) throws CustomException;   
    
    /**
     * Deletes the given employee from the database
     *
     * @return Employee, returns the employee deleted 
     * @param  employeeId, employeeId of the employee to be deleted
     */
    public Employee deleteEmployeeById(int employeeId) throws CustomException;

    /**
     * Retrieves the record of the employeeContact given from the user
     *
     * @return Employee, returns empty employee or null
     * @param  employeeContact, contact number used to retrieve employee
     */
    public Employee containsEmployeeContact(String employeeContact) throws CustomException;  

    /**
     * Retrieves the record of the EmployeeEmail given from the user
     *
     * @return Employee, returns empty employee or null
     * @param  EmployeeEmail, Employee email used to retrieve employee
     */
    public Employee containsEmployeeEmail(String employeeEmail) throws CustomException;

    /**
     * Retrieves the record from the EmployeeId given from the user
     *
     * @return Employee, returns employee details
     * @param  employeeId, Employee Id used to retrieve employee
     */
    public Employee viewEmployeeById(int employeeId) throws CustomException;
    
    /**
     * Retrieves List of all the the records
     *
     * @return List<Employee>, returns all the employee details
     */
    public List<Employee> viewEmployee() throws CustomException;
    
    /**
     * Deletes all the records
     *
     * @return boolean, returns whether any rows affected 
     */
    public boolean deleteAllEmployee() throws CustomException;
}
