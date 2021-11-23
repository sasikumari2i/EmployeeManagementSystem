/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.utils;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;

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
        List<Address> addressList = employee.getAddress();
        try {
            employeeDTO.setId(employee.getId());        
            employeeDTO.setName(employee.getName());
            employeeDTO.setEmail(employee.getEmail());
            employeeDTO.setContact(employee.getContact());
            employeeDTO.setSalary(employee.getSalary());
            employeeDTO.setDob(employee.getDob());
            if(null != addressList) {
                List<AddressDTO> addressDTOList = new ArrayList<>();
                for(Address address : addressList) {
                    addressDTOList.add(convertAddressToDTO(address));            
                }
                employeeDTO.setAddress(addressDTOList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        if (null != employee.getName()) {
            EmployeeDTO employeeDTO = new EmployeeDTO();            
            employeeDTO.setId(employee.getId());
            employeeDTO.setName(employee.getName());
            employeeDTO.setDob(employee.getDob());
            employeeDTO.setEmail(employee.getEmail());
            employeeDTO.setSalary(employee.getSalary());
            employeeDTO.setContact(employee.getContact());
            addressDTO.setEmployee(employeeDTO);
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
        List<AddressDTO> addressDTOList = employeeDTO.getAddress();
        Employee employee = new Employee();
        List<Address> addressList = new ArrayList<>();

        employee.setId(employeeDTO.getId());        
        employee.setEmail(employeeDTO.getEmail());
        employee.setName(employeeDTO.getName());
        employee.setContact(employeeDTO.getContact());
        employee.setSalary(employeeDTO.getSalary());
        employee.setDob(employeeDTO.getDob());
        if(null != addressDTOList) {        
            for(AddressDTO address : employeeDTO.getAddress()) {
                addressList.add(convertAddressDTOToAddress(address));
            }
            //System.out.println(addressList);
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
            EmployeeDTO employeeDTO = addressDTO.getEmployee();
            Address address = new Address();
            
            address.setAddressId(addressDTO.getAddressId());
            address.setDoorNo(addressDTO.getDoorNo());
            address.setLandMark(addressDTO.getLandMark());
            address.setCity(addressDTO.getCity());
            address.setStreet(addressDTO.getStreet());
            address.setPincode(addressDTO.getPincode());
            //address.setEmployeeId(addressDTO.getEmployeeId());
            if (null != employeeDTO) {
                address.setEmployee(convertDTOToEmployee(employeeDTO));
            }
        return address;     
    }
}
