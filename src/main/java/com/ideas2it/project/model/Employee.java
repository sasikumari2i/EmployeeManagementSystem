/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.model;

import java.time.LocalDate;
import com.ideas2it.project.model.Employee;
	
/**
 * Employee Management System model class
 * 
 * @version	1.0
 * @author	Sasikumar Raju
 */
public class Employee {
    private int id;
    private long contact;
    private float salary;
    private String name;
    private String email;
    private LocalDate dob;
    private EmployeeAddress address;

    /**
     * Employee getters and setters
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
    
    /**
     * Overrides toString method to display the details 
     *
     * @return display, String to be displayed
     */ 
    public String toString() {
        StringBuilder display = new StringBuilder();
        display.append(" ID : ").append(this.id)
               .append(" Name : ").append(this.name)
               .append(" DOB : ").append(this.dob)
               .append(" Salary : ").append(String.format("%.3f", this.salary))
               .append(" Email : ").append(this.email)
               .append(" Contact : ").append(this.contact);
        
        return display.toString();
    }
}	






