/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.model.dto;

import java.time.LocalDate;

import com.ideas2it.project.controller.EmployeeController;
import com.ideas2it.project.model.dto.EmployeeAddressDTO;
import com.ideas2it.project.model.EmployeeAddress;

/**
 * Data Transfer Object for the Employee Management System
 * 
 * @version	1.0
 * @author	Sasikumar Raju
 */
public class EmployeeDTO {
    private int id;
    private float salary;
    private long contact;
    private String name;
    private String email;
    private LocalDate dob;
    private EmployeeAddressDTO address;

    /**
     * EmployeeDTO getters and setters
     */ 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    public LocalDate getDob() {   
       return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
 
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
 
    public long getContact() {
        return contact;
    }
    
    public void setContact(long contact) {
        this.contact = contact;
    }

    public EmployeeAddressDTO getAddress() {
        return address;
    }
    
    public void setAddress(EmployeeAddressDTO address) {
        this.address = address;
    }
    
    /**
     * Overrides toString method to display the details 
     *
     * @return display, String to be displayed
     */ 
    public String toString() {
        StringBuilder display = new StringBuilder();
        display.append(" ID : ").append(this.id).append(" Name : ")
               .append(this.name).append(" DOB : ").append(this.dob) 
               .append(" Salary : ").append(String.format("%.3f", this.salary))
               .append(" Email : ").append(this.email).append(" Contact : ")
               .append(this.contact);
        
        return display.toString();
    }
}	

