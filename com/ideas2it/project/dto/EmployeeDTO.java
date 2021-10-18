/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.dto;

import com.ideas2it.project.controller.EmployeeController;
import java.time.LocalDate;

/**
 * Data Transfer Object for the Employee Management System
 * 
 * @version	1.0
 * @date	14 Oct 2021
 * @author	Sasikumar Raju
 */
public class EmployeeDTO {
    private int id;
    private String name;
    private double salary;
    private String email;
    private long contact;
    private LocalDate dob;

    /**
     * Return Employee ID 
     *
     * @return id, Employee ID
     */ 
    public int getId() {
        return id;
    }

    /**
     * Set Employee ID 
     *
     * @param id, Employee ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Return Employee Salary 
     *
     * @return salary, Employee Salary
     */ 
    public double getSalary() {
        return salary;
    }

    /**
     * Set Employee Salary 
     *
     * @param salary, Employee Salary
     */
    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Return Employee Date of Birth 
     *
     * @return dob, Employee Date of Birth
     */ 
    public LocalDate getDob() {   
       return dob;
    }

    /**
     * Set Employee Date of Birth 
     *
     * @param dob, Employee Date of Birth
     */
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    /**
     * Return Employee Name 
     *
     * @return name, Employee Name
     */ 
    public String getName() {
        return name;
    }

    /**
     * Set Employee Name 
     *
     * @param name, Employee Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return Employee Email 
     *
     * @return email, Employee Email
     */ 
    public String getEmail() {
        return email;
    }

    /**
     * Set Employee Email 
     *
     * @param email, Employee Email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Return Employee Contact 
     *
     * @return contact, Employee Contact
     */ 
    public long getContact() {
        return contact;
    }
    
    /**
     * Set Employee Contact 
     *
     * @param contact, Employee Contact
     */
    public void setContact(long contact) {
        this.contact = contact;
    }
    
    /**
     * Return All details 
     *
     * @return display, String to be displayed
     */ 
    public String toString() {
        StringBuilder display = new StringBuilder();
        display.append(" ID : " + this.id);
        display.append(" Name : " + this.name);
        display.append(" DOB : " + this.dob);
        display.append(" Salary : " + this.salary);
        display.append(" Email : " + this.email);
        display.append(" Contact : " + this.contact);
        
        return display.toString();
    }
}	

