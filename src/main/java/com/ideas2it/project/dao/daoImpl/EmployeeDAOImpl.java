/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.dao.daoImpl;

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

import com.ideas2it.project.dao.EmployeeDAO;
import com.ideas2it.project.model.Address;
import com.ideas2it.project.model.Project;
import com.ideas2it.project.model.Employee;
import com.ideas2it.project.utils.DatabaseConnection;

/**
 * Interacts with database to perform required operations
 *
 * @version	1.0
 * @author	Sasikumar Raju
 */
public class EmployeeDAOImpl implements EmployeeDAO {
       
    /**
     * Inserts new employee details to the database
     *
     * @return Employee, employee which is inserted
     * @param  Employee, Employee to be inserted
     */
    public Employee createEmployee(Employee employee) {
        Session session = null;
        Transaction transaction = null;
        int employeeID = 0;

        try {
            session = DatabaseConnection.getSession();
            transaction = session.beginTransaction();
            employeeID = (Integer)session.save(employee);
            employee.setId(employee.getId());
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); 
        } finally {
            DatabaseConnection.closeSession(); 
        }
        return (0 != employeeID) ? employee = null : employee;
    }

    /**
     * Update the employee details of an employee
     *
     * @return Employee, returns the updated employee 
     * @param  Employee, Employee to be updated
     */
    public Employee updateEmployee(Employee employee) {
        Session session = null;
        Transaction transaction = null;
        int employeeID = 0;

        try {
            session = DatabaseConnection.getSession();
            transaction = session.beginTransaction();
            employeeID = employee.getId();
            employee = (Employee)session.merge(employee);
            transaction.commit();
        } catch (HibernateException e) {
            employee = null;
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); 
        } finally {
            DatabaseConnection.closeSession(); 
        }
        return employee;
    }   
    
    /**
     * Deletes the given employee from the database
     *
     * @return Employee, returns the employee deleted 
     * @param  employeeId, employeeId of the employee to be deleted
     */
    public Employee deleteEmployeeById(int employeeId) {
        Session session = null;
        Transaction transaction = null;
        Employee employee = null;

        try {
            session = DatabaseConnection.getSession();
            transaction = session.beginTransaction();
            employee = (Employee) session.get(Employee.class, employeeId);
            session.delete(employee);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            employee = null;
            e.printStackTrace(); 
        } finally {
            DatabaseConnection.closeSession(); 
        }
        return employee;
    }

    /**
     * Retrieves the record of the employeeContact given from the user
     *
     * @return Employee, returns empty employee or null
     * @param  employeeContact, contact number used to retrieve employee
     */
    public Employee containsEmployeeContact(String employeeContact) {
        Session session = null;
        Transaction transaction = null;
        Employee employee = null;
        StringBuilder query = new StringBuilder("select e from Employee e ")
                .append("left join fetch e.address a left join fetch ")
                .append("e.projects p WHERE e.email = '"+ employeeContact +"'");
        try {
            session = DatabaseConnection.getSession();
            transaction = session.beginTransaction();
            employee = (Employee)session.createQuery(query.toString())
                                                          .uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("Session file exception");
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeSession(); 
        }
        return employee;
    }  

    /**
     * Retrieves the record of the EmployeeEmail given from the user
     *
     * @return Employee, returns empty employee or null
     * @param  EmployeeEmail, Employee email used to retrieve employee
     */
    public Employee containsEmployeeEmail(String employeeEmail) {
        Session session = null;
        Transaction transaction = null;
        Employee employee = null;
        StringBuilder query = new StringBuilder("select e from Employee e left")
                   .append(" join fetch e.address a left join fetch e.projects") 
                   .append(" p WHERE e.email = '"+ employeeEmail +"'"); 
                             
        try {
            session = DatabaseConnection.getSession();
            transaction = session.beginTransaction();
            employee = (Employee)session.createQuery(query.toString())
                                                          .uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeSession(); 
        }
        return employee;
    }

    /**
     * Retrieves the record from the EmployeeId given from the user
     *
     * @return Employee, returns employee details
     * @param  employeeId, Employee Id used to retrieve employee
     */
    public Employee viewEmployeeById(int employeeId) {
        Transaction transaction = null;
        Session session = null;
        Employee employee = new Employee();
        StringBuilder query = new StringBuilder("select e from Employee e left")
                      .append(" join fetch e.address a left join fetch ")
                      .append("e.projects p where e.id ='" + employeeId +"'");             

        try {
            session = DatabaseConnection.getSession();
            transaction = session.beginTransaction();
            employee = (Employee)session.createQuery(query.toString())
                                                    .uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeSession(); 
        }
        return employee;
    }
    
    /**
     * Retrieves List of all the the records
     *
     * @return List<Employee>, returns all the employee details
     */
    public List<Employee> viewEmployee() {
        Session session = null;
        Transaction transaction = null;
        List<Employee> employees = null;
        StringBuilder query = new StringBuilder("Select DISTINCT e from ");
                      query.append("Employee e left JOIN Fetch e.address a ");
                      query.append("LEFT JOIN Fetch e.projects p");

        try {
            session = DatabaseConnection.getSession();
            transaction = session.beginTransaction();
            employees = session.createQuery(query.toString()).list();
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("Session file exception");
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeSession();
        }
        return employees;
    }
    
    /**
     * Deletes all the records
     *
     * @return boolean, returns whether any rows affected 
     */
    public boolean deleteAllEmployee() {
        Session session = null;
        Transaction transaction = null;
        Employee employee = null;
        int employeeID = 0;
        String query = "delete FROM Employee";

        try {
            session = DatabaseConnection.getSession();
            transaction = session.beginTransaction();
            employeeID = session.createQuery(query).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); 
        } finally {
            DatabaseConnection.closeSession(); 
        }
        return (employeeID != 0);
    }
}
