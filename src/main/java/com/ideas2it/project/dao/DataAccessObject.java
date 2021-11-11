/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.dao;

import java.util.List;

import com.ideas2it.project.model.Employee;
import com.ideas2it.project.utils.DatabaseConnection;

/**
 * Interface for interaction with the database
 *
 * @version	1.0
 * @author	Sasikumar Raju
 */
public interface DataAccessObject {

    /**
     * Inserts new employee details to the database
     *
     * @return Employee, returns the inserted employee 
     * @param  Employee, employee record to be inserted
     */   
    public Employee createEmployee(Employee employee);

    /**
     * Update the employee details of an employee
     *
     * @return Employee, returns the inserted employee 
     * @param  Employee, employee record to be updated
     */
    public Employee updateEmployee(Employee employee);

    /**
     * Deletes the given employee from the database
     *
     * @return boolean, returns whether any rows affected 
     * @param  employeeId, employeeId of the employee to be deleted
     */
    public boolean deleteEmployeeById(int employeeId);

    /**
     * Retrieves the record of the employeeContact given from the user
     *
     * @return Employee, returns empty employee or null
     * @param  employeeContact, employee contact number used to retrieve employee
     */
    public Employee containsEmployeeContact(String employeeContact);

    /**
     * Retrieves the record of the EmployeeEmail given from the user
     *
     * @return Employee, returns empty employee or null
     * @param  EmployeeEmail, Employee email used to retrieve employee
     */
    public Employee containsEmployeeEmail(String employeeEmail);    

    /**
     * Retrieves the record from the EmployeeId given from the user
     *
     * @return Employee, returns employee details
     * @param  employeeId, Employee Id used to retrieve employee
     */
    public Employee viewEmployeeById(int employeeId);
    
    /**
     * Retrieves List of all the the records
     *
     * @return List<Employee>, returns all the employee details
     */
    public List<Employee> viewEmployee();    

    /**
     * Deletes all the records
     *
     * @return boolean, returns whether any rows affected 
     */
    public boolean deleteAllEmployee();
}
