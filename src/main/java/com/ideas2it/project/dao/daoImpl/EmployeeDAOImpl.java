/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.dao.daoImpl;

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
 * Interacts with database to perform required operations
 *
 * @version	1.0
 * @author	Sasikumar Raju
 */
public class EmployeeDAOImpl implements EmployeeDAO {
   
    private Connection connection = null;
   
    /**
     * Inserts address to the employee
     *
     * @return boolean, true if records are inserted 
     * @param  Address, address record to be inserted
     * @param employeeId, employee Id of the user to be inserted
     */
    public boolean insertAddress(int employeeId, Address address) {
        PreparedStatement statement = null;
        int noOfRecords = 0;
        StringBuilder query = new StringBuilder("insert into address ")
                              .append("(employee_id,door_no,landmark,street,")
                              .append("city,pincode) values (?,?,?,?,?,?)"); 
        try {
            String pincode = String.valueOf(address.getPincode());
            statement = connection.prepareStatement(query.toString());
            statement.setInt(1,employeeId);
            statement.setString(2,address.getDoorNo());
            statement.setString(3,address.getLandMark());
            statement.setString(4,address.getStreet());
            statement.setString(5,address.getCity());
            statement.setString(6,pincode);
            noOfRecords = statement.executeUpdate();
        } catch(Exception e) { 
            System.out.println(e);
        } finally {
            DatabaseConnection.close(statement);
        }
        return (0 != noOfRecords);
    }

    /**
     * Inserts new employee details to the database
     *
     * @return Employee, employee which is inserted
     * @param  Address, Employee Address of the employee which contains employee
     */
    public Employee createEmployee(Address address) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int employeeId = 0; 
        int noOfRecords = 0;
        Employee employee = address.getEmployee();
        boolean isAddressAdded = false;
        String insertQuery = "insert into employee values (?,?,?,?,?,?)";
        try{
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(insertQuery);
            Date date = Date.valueOf(employee.getDob());  
            statement.setInt(1,0);
            statement.setLong(2,employee.getContact());
            statement.setFloat(3,employee.getSalary());
            statement.setString(4,employee.getName());
            statement.setString(5,employee.getEmail());
            statement.setDate(6,date);
            noOfRecords = statement.executeUpdate();
            insertQuery = "select last_insert_id()"; 
            statement = connection.prepareStatement(insertQuery);
            resultSet = statement.executeQuery();
            resultSet.next();
            employeeId = resultSet.getInt(1);
            if(0 != noOfRecords) {
                isAddressAdded = insertAddress(employeeId, address);       
            }
        } catch(Exception e) { 
            System.out.println(e);
        } finally {
            DatabaseConnection.close(statement);
            DatabaseConnection.close(connection);
        }
        return isAddressAdded ? (employee = null) : employee;
    }

    /**
     * Update the employee details of an employee
     *
     * @return Employee, returns the inserted employee 
     * @param  Address, Employee Address of the employee which contains employee
     */
    public Employee updateEmployee(Address address) {
        PreparedStatement statement = null;
        boolean isAddressAdded = false;
        int noOfRecords = 0;
        Employee employee = address.getEmployee();;
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
            noOfRecords = statement.executeUpdate();
            if(null != address.getDoorNo()) {
                isAddressAdded = insertAddress(employee.getId(), address);       
            }       
        } catch(Exception e) { 
            System.out.println(e + "  here");
        } finally {
            DatabaseConnection.close(statement);
            DatabaseConnection.close(connection);
        }
        return (isAddressAdded || 0 != noOfRecords) ?
                                  (employee = null) : employee;
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
        } catch(Exception e) { 
            System.out.println(e);
        } finally {
            DatabaseConnection.close(statement);
            DatabaseConnection.close(connection);
        }
        return (noOfRecords == 0);
    }

    /**
     * Deletes the given address of the employee from the database
     *
     * @return boolean, returns whether any rows affected 
     * @param  addressId, addressId of the employee to be deleted
     */
    public boolean deleteAddress(int addressId) {
        PreparedStatement statement = null;
        String deleteQuery = "Delete from address where id = ?";  
        int noOfRecords = 0;
        try{
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(deleteQuery);        
            statement.setInt(1,addressId);
            noOfRecords = statement.executeUpdate();
        } catch(Exception e) { 
            System.out.println(e);
        } finally {
            DatabaseConnection.close(statement);
            DatabaseConnection.close(connection);
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
            DatabaseConnection.close(connection);
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
        Employee employee = null;
        StringBuilder emailQuery = new StringBuilder("Select id from employee")
                                  .append(" where email = '"+employeeEmail+"'");
        try{
            connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(emailQuery.toString());
            if((resultSet.next())) {
                employee = new Employee();
            }
        }  catch(Exception e) { 
            System.out.println(e);
        } finally {
            DatabaseConnection.close(connection);
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
        boolean isAvailable = true;
        StringBuilder viewQuery = new StringBuilder("select * from"); 
        viewQuery.append(" employee where id = '"+ employeeId +"'");
        Employee employee = new Employee();
        List<Address> addressList = employee.getAddress();
        try{
            connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(viewQuery.toString());
            if(resultSet.next()) {
                employee.setId(resultSet.getInt("id"));
                employee.setContact(Long
                        .parseLong(resultSet.getString("phonenumber")));
                employee.setSalary(Float.parseFloat(resultSet
                                                    .getString("salary")));
                employee.setName(resultSet.getString("name"));
                employee.setEmail(resultSet.getString("email"));
                employee.setDob(resultSet.getDate("dob").toLocalDate());
                employee.setAddress(getAddressById(employee.getId()));
            } else {
                employee = null;
            }
        }  catch(Exception e) { 
            System.out.println(e);
        } finally {
            DatabaseConnection.close(connection);
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
        List<Employee> employees = new ArrayList<Employee>();
        Employee employee = new Employee();
        List<Address> addressList = new ArrayList<Address>();
        try{
            connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from employee");
            while(resultSet.next()) {
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
            if (!employees.isEmpty()) {
                for (int index = 0; index < employees.size(); index++) {
                     addressList = getAddressById(employees.get(index).getId());
                     employees.get(index).setAddress(addressList);
                }
            }
        }  catch(Exception e) { 
            System.out.println(e);
        } finally {
            DatabaseConnection.close(connection);
        }
        return employees;
    }    

    /**
     * List of address for the given employee
     *
     * @return List<Address>, returns available addresses for the given employee 
     * @param  employeeId, employeeId of the user
     */
    public List<Address> getAddressById(int employeeId) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Address> addressList = new ArrayList<Address>();
        String query = "select id,door_no,street,city,landmark,pincode," 
                + "employee_id from address where employee_id=" + employeeId;
        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Address address = new Address();
                address.setAddressId(resultSet.getInt(1));
                address.setDoorNo(resultSet.getString(2));
                address.setStreet(resultSet.getString(3));
                address.setCity(resultSet.getString(4));
                address.setLandMark(resultSet.getString(5));
                address.setPincode(Long.parseLong(resultSet.getString(6)));
                addressList.add(address);
            }
        }catch(Exception e) { 
            System.out.println(e);
        } finally {
            DatabaseConnection.close(statement);
            DatabaseConnection.close(connection);
        }
        return addressList;
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
            noOfRecords = statement.executeUpdate("Delete from employee");
        }  catch(Exception e) { 
            System.out.println(e);
        } finally {
            DatabaseConnection.close(connection);
        }
        return (noOfRecords == 0);
    }
}
