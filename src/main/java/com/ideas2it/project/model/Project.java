/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
	
/**
 * Employee Management System Project model class
 * 
 * @version	1.0
 * @author	Sasikumar Raju
 */
public class Project {
    private int id;
    private String name;
    private String domain;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;
    private String status;
    private Set<Employee> employees = new HashSet<>();

    public Project() {}

    /**
     * Project getters and setters
     */ 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
 
    public LocalDate getStartDate() {   
       return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {   
       return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
 
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
 
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }
  
    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
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
               .append(" Start Date : ").append(this.startDate)
               .append(" End Date : ").append(this.endDate)
               .append(" Domain : ").append(this.domain)
               .append(" Status : ").append(this.status)
               .append(" Projects assigned : ").append(this.employees);
        
        return display.toString();
    }
}	






