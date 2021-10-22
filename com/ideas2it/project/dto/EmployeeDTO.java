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
     * Employee Id getter and setter
     */ 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     *  
     * Employee Salary getter and setter
     */ 
    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    /**
     * Employee Date of birth getter and setter
     */
    public LocalDate getDob() {   
       return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    /**
     * Employee Name getter and setter
     */ 
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Employee Email getter and setter
     */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Employee Contact getter and setter
     */ 
    public long getContact() {
        return contact;
    }
    
    public void setContact(long contact) {
        this.contact = contact;
    }
    
    /**
     * Overrides toString method to display the details 
     *
     * @return display, String to be displayed
     */ 
    public String toString() {
        StringBuilder display = new StringBuilder();
        display.append(" ID : " + this.id).append(" Name : " + this.name)
               .append(" DOB : " + this.dob) 
               .append(" Salary : " + String.format("%.3f", this.salary))
               .append(" Email : " + this.email)
               .append(" Contact : " + this.contact);
        
        return display.toString();
    }
}	

