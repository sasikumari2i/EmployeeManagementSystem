/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.dao.daoImpl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.project.dao.EmployeeDAO;
import com.ideas2it.project.model.Address;
import com.ideas2it.project.model.Employee;
import com.ideas2it.project.utils.DatabaseConnection;

import org.hibernate.HibernateException; 
import org.hibernate.Session; 
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Interacts with database to perform required operations
 *
 * @version	1.0
 * @author	Sasikumar Raju
 */
public class EmployeeDAOImpl implements EmployeeDAO {
   
    private SessionFactory factory = null;
        
    /**
     * Inserts address to the employee
     *
     * @return boolean, true if records are inserted 
     * @param  Address, address record to be inserted
     * @param employeeId, employee Id of the user to be inserted
     */
    /*public Address insertAddress(Address address) {
        Transaction transaction = null;
        int addressID = 0;
        
        factory = DatabaseConnection.getSessionFactory();
        Session session = factory.openSession();
        try {
            transaction = session.beginTransaction();
            Employee employee = address.getEmployee();
            if (employee.getId() > 0) {
                address.setEmployee(employee);
                if (null != address.getDoorNo()) {
                    addressID = (Integer)session.save(address);
                }
            }
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("Error in adding Address");
            if (transaction != null) transaction.rollback();
            e.printStackTrace(); 
        } finally {
            session.close(); 
        }
        return (addressID > 0) ? (address = null) : address;
    }*/   

    /**
     * Inserts new employee details to the database
     *
     * @return Employee, employee which is inserted
     * @param  Address, Employee Address of the employee which contains employee
     */
    public Employee createEmployee(Employee employee) {
        Transaction transaction = null;
        int employeeID = 0;

        factory = DatabaseConnection.getSessionFactory();
        Session session = factory.openSession();
        try {
            transaction = session.beginTransaction();
            employeeID = (Integer)session.save(employee);
            employee.setId(employee.getId());
            transaction.commit();
        } catch (Exception e) {
            System.out.println("Error in creating employee");
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); 
        } finally {
            session.close(); 
        }
        return (0 != employeeID) ? employee = null : employee;
    }

    /**
     * Update the employee details of an employee
     *
     * @return Employee, returns the inserted employee 
     * @param  Address, Employee Address of the employee which contains employee
     */
    public Employee updateEmployee(Employee employee) {
        Transaction transaction = null;
        int employeeID = 0;

        factory = DatabaseConnection.getSessionFactory();
        Session session = factory.openSession();
        try {
            transaction = session.beginTransaction();
            employeeID = employee.getId();
            employee = (Employee)session.merge(employee); 
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("Session file exception");
            employee = null;
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); 
        } finally {
            session.close(); 
        }
        return employee;
    }   
    
    /**
     * Deletes the given employee from the database
     *
     * @return boolean, returns whether any rows affected 
     * @param  employeeId, employeeId of the employee to be deleted
     */
    public Employee deleteEmployeeById(int employeeId) {
        Transaction transaction = null;
        Employee employee = null;

        factory = DatabaseConnection.getSessionFactory();
        Session session = factory.openSession();
        try {
            transaction = session.beginTransaction();
            employee = (Employee) session.get(Employee.class, employeeId);
            session.delete(employee);
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("Session file exception");
            if (transaction != null) transaction.rollback();
            employee = null;
            e.printStackTrace(); 
        } finally {
            session.close(); 
        }
        System.out.println(employee);
        return employee;
    }

    /**
     * Deletes the given address of the employee from the database
     *
     * @return boolean, returns whether any rows affected 
     * @param  addressId, addressId of the employee to be deleted
     */
    public Address deleteAddress(int addressId) {
        Transaction transaction = null;
        Address address = null;
        int employeeID = 0;

        factory = DatabaseConnection.getSessionFactory();
        Session session = factory.openSession();
        try {
            transaction = session.beginTransaction();
            address = (Address) session.get(Address.class, addressId);
            session.delete(address);
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("Session file exception");
            address = null;
            if (transaction != null) transaction.rollback();
            e.printStackTrace(); 
        } finally {
            session.close(); 
        }
        return address;
    }

    /**
     * Retrieves the record of the employeeContact given from the user
     *
     * @return Employee, returns empty employee or null
     * @param  employeeContact, contact number used to retrieve employee
     */
    public Employee containsEmployeeContact(String employeeContact) {
        Transaction transaction = null;
        Employee employee = null;

        factory = DatabaseConnection.getSessionFactory();
        Session session = factory.openSession();
        StringBuilder query = new StringBuilder("FROM Employee WHERE ")
                                 .append("phonenumber = '"+employeeContact+"'");
        try {
            transaction = session.beginTransaction();
            employee = (Employee)session.createQuery(query.toString())
                                                          .uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("Session file exception");
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close(); 
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
        Transaction transaction = null;
        Employee employee = null;

        factory = DatabaseConnection.getSessionFactory();
        Session session = factory.openSession();
        String query = "FROM Employee WHERE email = '"+ employeeEmail + "'";
        try {
            transaction = session.beginTransaction();
            employee = (Employee)session.createQuery(query).uniqueResult();
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("Session file exception");
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close(); 
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
        Employee employee = new Employee();
        StringBuilder query = new StringBuilder("Select e FROM Employee ")
                    .append("e JOIN Fetch e.address where e.id = "+ employeeId);
        factory = DatabaseConnection.getSessionFactory();
        Session session = factory.openSession();
        try {
            transaction = session.beginTransaction();
            employee = (Employee)session.createQuery(query.toString())
                                                    .uniqueResult();
        } catch (HibernateException e) {
            System.out.println("Session file exception");
            transaction.commit();
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close(); 
        }
        return employee;
    }
    
    /**
     * Retrieves List of all the the records
     *
     * @return List<Employee>, returns all the employee details
     */
    public List<Employee> viewEmployee() {
        Transaction transaction = null;
        List<Employee> employees = null;
        String query = "Select DISTINCT e FROM Employee e JOIN Fetch e.address";
        factory = DatabaseConnection.getSessionFactory();
        Session session = factory.openSession();
        try {
            transaction = session.beginTransaction();
            employees = session.createQuery(query).getResultList();
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("Session file exception");
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            session.close(); 
        } finally {
        }
        return employees;
    }
    
    /**
     * Deletes all the records
     *
     * @return boolean, returns whether any rows affected 
     * @param  employeeId, employeeId of the employee to be deleted
     */
    public boolean deleteAllEmployee() {
        Transaction transaction = null;
        Employee employee = null;
        int employeeID = 0;
        String query = "delete FROM Employee";
        factory = DatabaseConnection.getSessionFactory();
        Session session = factory.openSession();
        try {
            transaction = session.beginTransaction();
            employeeID = session.createQuery(query).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            System.out.println("Session file exception");
            if (transaction != null) transaction.rollback();
            e.printStackTrace(); 
        } finally {
            session.close(); 
        }
        return (employeeID != 0);
    }
}
