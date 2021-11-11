/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.model;

/**
 * Employee Address model class
 * 
 * @version	1.0
 * @author	Sasikumar Raju
 */
public class EmployeeAddress {	
    private String doorNo;
    private String landMark;
    private String street;
    private String city;
    private Long pincode;

    /**
     * Employee getters and setters
     */ 
    public String getDoorNo() {
        return doorNo;
    }

    public void setDoorNo(String doorNo) {
        this.doorNo = doorNo;
    }

    public String getLandMark() {
        return landMark;
    }

    public void setLandMark(String landMark) {
        this.landMark = landMark;
    }
 
    public String getCity() {   
       return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
 
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
 
    public Long getPincode() {
        return pincode;
    }

    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }
    
    /**
     * Overrides toString method to display the details 
     *
     * @return display, String to be displayed
     */ 
    public String toString() {
        StringBuilder display = new StringBuilder();
        display.append(" DoorNo : ").append(this.doorNo)
               .append(" Landmark : ").append(this.landMark)
               .append(" Street : ").append(this.street)
               .append(" City : ").append(this.city)
               .append(" Pincode : ").append(this.pincode);
        
        return display.toString();
    }
}	






