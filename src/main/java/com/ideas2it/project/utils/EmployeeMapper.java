/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.utils;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.project.model.Address;
import com.ideas2it.project.model.dto.AddressDTO;
import com.ideas2it.project.model.dto.EmployeeDTO; 
import com.ideas2it.project.model.Employee;

/**
 * Maps between service and control layers for converting DTO objects to 
 * model objects and vice versa
 *
 * @version	1.0
 * @date	14 Oct 2021
 * @author	Sasikumar Raju
 */
public class EmployeeMapper {

    /**
     * Converts Employee to DTO
     *
     * @param employee, Employee to be converted
     * @return employeeDTO, DTO object
     */   
    public static EmployeeDTO convertEmployeeToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        
        employeeDTO.setId(employee.getId());        
        employeeDTO.setName(employee.getName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setContact(employee.getContact());
        employeeDTO.setSalary(employee.getSalary());
        employeeDTO.setDob(employee.getDob());
        List<AddressDTO> addressList = new ArrayList<AddressDTO>();
        if(null != employee.getAddress()) {
            for(Address address : employee.getAddress()) {
                addressList.add(convertAddressToDTO(address));
            }
        }
        employeeDTO.setAddress(addressList);
        return employeeDTO;
    }

    /**
     * Converts Address to AddressDTO
     *
     * @param address, Address to be converted
     * @return AddressDTO, AddressDTO object
     */
    public static AddressDTO convertAddressToDTO(Address address) {
        AddressDTO addressDTO = new AddressDTO();
        
        addressDTO.setAddressId(address.getAddressId());
        addressDTO.setDoorNo(address.getDoorNo());
        addressDTO.setLandMark(address.getLandMark());
        addressDTO.setCity(address.getCity());
        addressDTO.setStreet(address.getStreet());
        addressDTO.setPincode(address.getPincode());
        Employee employee = address.getEmployee();
        addressDTO.setEmployeeId(address.getEmployeeId());
        if (null != employee) {
            addressDTO.setEmployee(convertEmployeeToDTO(employee));
        }
        return addressDTO;
    }

    /**
     * Converts DTO to Employee
     *
     * @param employeeDTO, EmployeeDTO to be converted
     * @return employee, Employee class 
     */       
    public static Employee convertDTOToEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();

        employee.setId(employeeDTO.getId());        
        employee.setEmail(employeeDTO.getEmail());
        employee.setName(employeeDTO.getName());
        employee.setContact(employeeDTO.getContact());
        employee.setSalary(employeeDTO.getSalary());
        employee.setDob(employeeDTO.getDob());
        List<Address> addressList = new ArrayList<Address>();
        if(null != employeeDTO.getAddress()) {
            for(AddressDTO address : employeeDTO.getAddress()) {
                addressList.add(convertAddressDTOToAddress(address));
            }
        }
        employee.setAddress(addressList);
        return employee;
    }

    /**
     * Converts AddressDTO to Address
     *
     * @param addressDTO, AddressDTO to be converted
     * @return Address, Employee's Address
     */
    public static Address convertAddressDTOToAddress(AddressDTO addressDTO) {
            Address address = new Address();
        
            address.setDoorNo(addressDTO.getDoorNo());
            address.setLandMark(addressDTO.getLandMark());
            address.setCity(addressDTO.getCity());
            address.setStreet(addressDTO.getStreet());
            address.setPincode(addressDTO.getPincode());
            EmployeeDTO employeeDTO = addressDTO.getEmployee();
            address.setEmployeeId(addressDTO.getEmployeeId());
            if (null != employeeDTO) {
                address.setEmployee(convertDTOToEmployee(employeeDTO));
            }
        return address;     
    }
}
