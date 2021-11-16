/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.model.dto;

/**
 * Employee Address model class
 * 
 * @version	1.0
 * @author	Sasikumar Raju
 */
public class AddressDTO {
    private int employeeId;
    private int addressId;
    private EmployeeDTO employeeDTO;
    private String doorNo;
    private String landMark;
    private String street;
    private String city;
    private Long pincode;

    /**
     * Employee getters and setters
     */
    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
 
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
    
    public EmployeeDTO getEmployee() {
        return employeeDTO;
    }

    public void setEmployee(EmployeeDTO employeeDTO) {
        this.employeeDTO = employeeDTO;
    }
    
    /**
     * Overrides toString method to display the details 
     *
     * @return display, String to be displayed
     */ 
    public String toString() {
        StringBuilder display = new StringBuilder();
        display.append(" Address ID : ").append(this.addressId)
               .append(" DoorNo : ").append(this.doorNo)
               .append(" Landmark : ").append(this.landMark)
               .append(" Street : ").append(this.street)
               .append(" City : ").append(this.city)
               .append(" Pincode : ").append(this.pincode)
               .append("\n");
        return display.toString();
    }
}	






