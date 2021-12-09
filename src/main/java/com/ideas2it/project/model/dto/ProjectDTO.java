/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.model.dto;

import java.time.LocalDate;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
	
/**
 * Employee Management System model class
 * 
 * @version	1.0
 * @author	Sasikumar Raju
 */
public class ProjectDTO {
    private int id;
    private String name;
    private String domain;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private Set<EmployeeDTO> employees = new HashSet<>();

    /**
     * Employee getters and setters
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

    public Set<EmployeeDTO> getEmployees() {
        return employees;
    }
  
    public void setEmployees(Set<EmployeeDTO> employees) {
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
               .append(" Status : ").append(this.status);
        
        return display.toString();
    }
}	






