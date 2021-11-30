/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.model.dto;

import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

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
    private List<AddressDTO> address = new ArrayList<AddressDTO>();
    private Set<ProjectDTO> projects = new HashSet<ProjectDTO>();

    /**
     * Override equals method to compare two objects
     */
    public boolean equals(Object object) {
        EmployeeDTO employee = (EmployeeDTO)object;
        return this.id == employee.id;
    }

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

    public List<AddressDTO> getAddress() {
        return address;
    }
    
    public void setAddress(List<AddressDTO> address) {
        this.address = address;
    }

    public Set<ProjectDTO> getProjects() {
        return projects;
    }
  
    public void setProjects(Set<ProjectDTO> projects) {
        this.projects = projects;
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
               .append(this.contact).append("\n");
        return display.toString();
    }
}	

