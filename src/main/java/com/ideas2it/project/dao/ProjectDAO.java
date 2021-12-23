/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.ideas2it.project.dao.ProjectDAO;
import com.ideas2it.project.model.Address;
import com.ideas2it.project.model.Project;
import com.ideas2it.project.model.Employee;
import com.ideas2it.project.utils.DatabaseConnection;
import com.ideas2it.project.exception.CustomException;

/**
 * Interacts with database to perform required operations
 *
 * @version	1.0
 * @author	Sasikumar Raju
 */
public interface ProjectDAO {
       
    /**
     * Inserts new employee details to the database
     *
     * @return Employee, employee which is inserted
     * @param  Employee, Employee to be inserted
     */
    public Project createProject(Project project) throws CustomException;

    /**
     * Update the employee details of an employee
     *
     * @return Employee, returns the updated employee 
     * @param  Employee, Employee to be updated
     */
    public Project updateProject(Project project) throws CustomException;   
    
    /**
     * Deletes the given employee from the database
     *
     * @return Employee, returns the employee deleted 
     * @param  employeeId, employeeId of the employee to be deleted
     */
    public Project deleteProjectById(int projectId) throws CustomException;

    /**
     * Retrieves the record from the EmployeeId given from the user
     *
     * @return Employee, returns employee details
     * @param  employeeId, Employee Id used to retrieve employee
     */
    public Project viewProjectById(int projectId) throws CustomException;
    
    /**
     * Retrieves List of all the the records
     *
     * @return List<Employee>, returns all the employee details
     */
    public List<Project> viewProject() throws CustomException;
    
    /**
     * Deletes all the records
     *
     * @return boolean, returns whether any rows affected 
     */
    public boolean deleteAllProject() throws CustomException;
}
