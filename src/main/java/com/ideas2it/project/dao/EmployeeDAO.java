/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.project.dao.EmployeeDAO;
import com.ideas2it.project.model.Address;
import com.ideas2it.project.model.Employee;
import com.ideas2it.project.utils.DatabaseConnection;

/**
 * Interface to provide methods to interact with the database
 *
 * @version	1.0
 * @author	Sasikumar Raju
 */
public interface EmployeeDAO {
   
    /**
     * Inserts address to the employee
     *
     * @return boolean, true if records are inserted 
     * @param  Address, address record to be inserted
     * @param employeeId, employee Id of the user to be inserted
     */
    //public Address insertAddress(Address address);

    /**
     * Inserts new employee details to the database
     *
     * @return Employee, employee which is inserted
     * @param  Address, Employee Address of the employee which contains employee
     */
    public Employee createEmployee(Employee employee);

    /**
     * Update the employee details of an employee
     *
     * @return Employee, returns the inserted employee 
     * @param  Address, Employee Address of the employee which contains employee
     */
    public Employee updateEmployee(Employee employee);
    
    /**
     * Deletes the given employee from the database
     *
     * @return boolean, returns whether any rows affected 
     * @param  employeeId, employeeId of the employee to be deleted
     */
    public Employee deleteEmployeeById(int employeeId);

    /**
     * Deletes the given address of the employee from the database
     *
     * @return boolean, returns whether any rows affected 
     * @param  addressId, addressId of the employee to be deleted
     */
    public Address deleteAddress(int addressId);

    /**
     * Retrieves the record of the employeeContact given from the user
     *
     * @return Employee, returns empty employee or null
     * @param  employeeContact, contact number used to retrieve employee
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
     * List of address for the given employee
     *
     * @return List<Address>, returns available addresses for the given employee 
     * @param  employeeId, employeeId of the user
     */
   // public List<Address> getAddressById(int employeeId);
    
    /**
     * Deletes all the records
     *
     * @return boolean, returns whether any rows affected 
     * @param  employeeId, employeeId of the employee to be deleted
     */
    public boolean deleteAllEmployee();
}
