/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.dao.daoImpl;

import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ideas2it.project.dao.DataAccessObject;
import com.ideas2it.project.model.Employee;
import com.ideas2it.project.model.EmployeeAddress;
import com.ideas2it.project.utils.DatabaseConnection;

/**
 * Interacts with database to perform required operations
 *
 * @version	1.0
 * @author	Sasikumar Raju
 */
public class DataAccessObjectImpl implements DataAccessObject {
   
    private Connection connection = null;
    //private EmployeeAddress address = new EmployeeAddress();
   
    /**
     * Inserts new employee details to the database
     *
     * @return Employee, returns the inserted employee 
     * @param  Employee, employee record to be inserted
     */
    public Employee createEmployee(Employee employee, EmployeeAddress address) {
        PreparedStatement statement = null;
        PreparedStatement addressStatement = null;
         
        String insertQuery = "insert into employee values (?,?,?,?,?,?)";
        String insertAddressQuery = "insert into address values (?,?,?,?,?,?)"; 
        try{
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(insertQuery);
            Date date = Date.valueOf(employee.getDob());  
            statement.setInt(1,employee.getId());
            statement.setLong(2,employee.getContact());
            statement.setFloat(3,employee.getSalary());
            statement.setString(4,employee.getName());
            statement.setString(5,employee.getEmail());
            statement.setDate(6,date);
            addressStatement = connection.prepareStatement(insertAddressQuery);
            addressStatement.setInt(1,employee.getId());
            addressStatement.setString(2,address.getDoorNo());
            addressStatement.setString(3,address.getLandMark());
            addressStatement.setString(4,address.getStreet());
            addressStatement.setString(5,address.getCity());
            addressStatement.setLong(6,address.getPincode());
                if(0 != statement.executeUpdate() && 0 != addressStatement.executeUpdate()) {
                    employee = null;
                }
        } catch(Exception e) { 
            System.out.println(e);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch(SQLException e) {
                    System.out.println(e);
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    System.out.println(e);
                }    
            }
        }
        return employee;
    }
    
    /**
     * Update the employee details of an employee
     *
     * @return Employee, returns the inserted employee 
     * @param  Employee, employee record to be updated
     */
    public Employee updateEmployee(Employee employee) {
        PreparedStatement statement = null;
        StringBuilder updateQuery = new StringBuilder();
        updateQuery.append("update employee set phonenumber = ?, salary = ?,")
                   .append("name = ?,email = ?, dob = ? where id = ?");  
        try{
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(updateQuery.toString());
            Date date = Date.valueOf(employee.getDob());  
            statement.setLong(1,employee.getContact());
            statement.setFloat(2,employee.getSalary());
            statement.setString(3,employee.getName());
            statement.setString(4,employee.getEmail());
            statement.setDate(5,date);
            statement.setInt(6,employee.getId());
            if(0 != statement.executeUpdate()) {
                employee = null;
            }
        } catch(Exception e) { 
            System.out.println(e);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch(SQLException e) {
                    System.out.println(e);
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    System.out.println(e);
                }    
            }
        }
        return employee;
    }

    /**
     * Deletes the given employee from the database
     *
     * @return boolean, returns whether any rows affected 
     * @param  employeeId, employeeId of the employee to be deleted
     */
    public boolean deleteEmployeeById(int employeeId) {
        PreparedStatement statement = null;
        String deleteQuery = "Delete from employee where id = ?";  
        int noOfRecords = 0;
        try{
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(deleteQuery);        
            statement.setInt(1,employeeId);
            noOfRecords = statement.executeUpdate();
        }  catch(Exception e) { 
            System.out.println(e);
        } finally {
            if(statement != null) {
                try {
                    statement.close();
                } catch(SQLException e) {
                    System.out.println(e);
                }
            }
            if(connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    System.out.println(e);
                }    
            }
        }
        return (noOfRecords == 0);
    }

    /**
     * Retrieves the record of the employeeContact given from the user
     *
     * @return Employee, returns empty employee or null
     * @param  employeeContact, contact number used to retrieve employee
     */
    public Employee containsEmployeeContact(String employeeContact) {
        ResultSet resultSet = null;
        Employee employee = null;
        StringBuilder contactQuery = new StringBuilder();
        contactQuery.append("Select * from employee where phonenumber =")
                    .append("'"+ employeeContact +"'");
        try{
            connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(contactQuery.toString());
            if((resultSet.next())) {
                employee = new Employee();
            }
        }  catch(Exception e) { 
            System.out.println(e);
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    System.out.println(e);
                }    
            }
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
        ResultSet resultSet = null;
        Employee employee = null;
        StringBuilder emailQuery = new StringBuilder();
        emailQuery.append("Select id from employee where email = ")
                  .append("'"+ employeeEmail +"'");
        try{
            connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(emailQuery.toString());
            if((resultSet.next())) {
                employee = new Employee();
            }
        }  catch(Exception e) { 
            System.out.println(e);
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    System.out.println(e);
                }    
            }
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
        ResultSet resultSet = null;
        boolean isAvailable = true;
        StringBuilder viewQuery = new StringBuilder(); 
        viewQuery.append("Select * from employee where id = '"+ employeeId +"'");
        Employee employee = new Employee();
        try{
            connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(viewQuery.toString());
            if(resultSet.next()) {
                employee.setId(resultSet.getInt("id"));
                employee.setContact(Long
                        .parseLong(resultSet.getString("phonenumber")));
                employee.setSalary(Float.parseFloat(resultSet
                                                    .getString("salary")));
                employee.setName(resultSet.getString("name"));
                employee.setEmail(resultSet.getString("email"));
                employee.setDob(resultSet.getDate("dob").toLocalDate());
            } else {
                employee = null;
            } 
        }  catch(Exception e) { 
            System.out.println(e);
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    System.out.println(e);
                }    
            }
        }
        return employee;
    }
    
    /**
     * Retrieves List of all the the records
     *
     * @return List<Employee>, returns all the employee details
     */
    public List<Employee> viewEmployee() {
        ResultSet resultSet = null;
        boolean isAvailable = true;
        List<Employee> employees = new ArrayList<Employee>();
        try{
            connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("Select * from employee");
            while(resultSet.next()) {
                Employee employee = new Employee();
                employee.setId(resultSet.getInt("id"));
                employee.setContact(Long.parseLong(resultSet
                                                  .getString("phonenumber")));
                employee.setSalary(Float.parseFloat(resultSet
                                                   .getString("salary")));
                employee.setName(resultSet.getString("name"));
                employee.setEmail(resultSet.getString("email"));
                employee.setDob(resultSet.getDate("dob").toLocalDate());
                employees.add(employee);
            }
        }  catch(Exception e) { 
            System.out.println(e);
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    System.out.println(e);
                }    
            }
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
        int noOfRecords = 0;
        try{
            connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            noOfRecords = statement.executeUpdate("Truncate table employee");
        }  catch(Exception e) { 
            System.out.println(e);
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch(SQLException e) {
                    System.out.println(e);
                }    
            }
        }
        return (noOfRecords == 0);
    }
}
