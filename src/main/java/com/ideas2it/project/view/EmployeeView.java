/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.view;

import java.lang.ArrayIndexOutOfBoundsException;
import java.lang.NumberFormatException;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.HashSet;

import com.ideas2it.project.controller.EmployeeController;
import com.ideas2it.project.model.dto.AddressDTO;
import com.ideas2it.project.model.dto.EmployeeDTO;
import com.ideas2it.project.model.dto.ProjectDTO;

/**
 * User Interface for getting inputs and displaying the appropriate outputs
 *
 * @version	1.0
 * @author	Sasikumar Raju
 */
public class EmployeeView {
    private EmployeeController employeeController = new EmployeeController();
    private Scanner inputReader = new Scanner(System.in);

    /**
     * Displays the main menu and switches to the required menu
     */
    public void showMainMenu() {
        boolean isExit = true;
        int inputChoice = 0;
        
        do {
            System.out.println("1.CREATE 2.DISPLAY 3.DELETE 4.UPDATE 5.Back ");
            inputChoice = getInputChoice();
            switch(inputChoice) {
                case 1 : createEmployee();
                         break;
                case 2 : viewEmployee();
                         break;
                case 3 : deleteEmployee();
                         break;
                case 4 : updateDisplay();
                         break;
                case 5 : isExit = false;
                         System.out.println("Thank you!!!");
                         break;
                default : System.out.println("Choose from the given numbers");    
                          break;
            }
        } while(isExit);
    }
    
    /**
     * Validates the choice number given by the user 
     *
     * @return inputChoice, Validated choice number 
     */
    public int getInputChoice() {
        int inputChoice = 0;
        boolean isValidChoice = false;
        
        while(!isValidChoice) {
            try {
                inputChoice = Integer.parseInt(inputReader.nextLine());
                isValidChoice = employeeController
                                .getChoiceValidated(inputChoice);
                if(!isValidChoice) {
                    System.out.println("Enter a valid option");
                }
            } catch(NumberFormatException e) {
                System.out.println("Enter only valid number");     
            }
        }
        return inputChoice;
    }
    
    /**
     * Validates the given EmployeeId from user 
     *
     * @return employeeId, Validated Employee ID 
     */
    private int getEmployeeId() {
        boolean isValidId = false;
        int employeeId = 0;
        
        System.out.println("Enter Employee Id");
        StringBuilder displayString = new StringBuilder("Employee ID can have")
                               .append(" only upto 4 digits,greater than zero");
        System.out.println(displayString);
        while(!isValidId) {
            try {
                employeeId  = Integer.parseInt(inputReader.nextLine());
                isValidId = employeeController.getEmployeeIdValidated
                                               (employeeId);
                if(!isValidId) {
                    System.out.println("Invalid Id, try again");
                    isValidId = false;
                }
            } catch(NumberFormatException e) {
                System.out.println("Enter only valid numbers");
            }
        }
        return employeeId;
    }
    
    /**
     * Validates the given Employee Name by user 
     *
     * @return employeeName, Validated Employee Name 
     */
    private String getEmployeeName() {
        boolean isValidName = false;
        String employeeName = null;
        
        System.out.println("Enter Employee Name");
        StringBuilder displayString = new StringBuilder("Does not allow ")
                                        .append("special characters or digits");
        System.out.println(displayString);

        while(!isValidName) {
            employeeName  = inputReader.nextLine();
            isValidName = employeeController
                          .getEmployeeNameValidated(employeeName);
            if(!isValidName) {
                System.out.println("Enter a valid name");
            }
        }
        return employeeName;
    }

    /**
     * Validates the given Employee Salary from user 
     *
     * @return employeeSalary, Validated Employee Salary 
     */
    private float getEmployeeSalary() {
        boolean isValidSalary = false;
        float employeeSalary = 0;

        System.out.println("Enter Employee Salary");
        while(!isValidSalary) {
            try {
                employeeSalary = Float.parseFloat(inputReader.nextLine());
                isValidSalary = employeeController
                                .getEmployeeSalaryValidated(employeeSalary);
                if(!isValidSalary) {
                    System.out.println("Enter a valid salary");
                }
            } catch(NumberFormatException e) {
                System.out.println("Enter only valid numbers");
            }
        }
        return employeeSalary;
    }
    
    /**
     * Validates the given Employee Email from user 
     *
     * @return employeeEmail, Validated Employee Email 
     */
    private String getEmployeeEmail() {
        boolean isValidEmail = false;
        String employeeEmail = null;
        
        StringBuilder errorMsg = new StringBuilder("Email is already available")
                                     .append(" or invalid format, Try another");
        System.out.println("Enter Employee Email");
        StringBuilder displayString = new StringBuilder("Only Standard format")
                               .append(" is acceptable,only(-_.) only these 3 ")
                               .append("characters are allowed");
        System.out.println(displayString);
 
        while(!isValidEmail) {
            employeeEmail = inputReader.nextLine();
            isValidEmail = employeeController
                           .getEmployeeEmailValidated(employeeEmail);
            if(!isValidEmail) {
                System.out.println(errorMsg);    
            }
        }
        return employeeEmail;
    }
    
    /**
     * Validates the given Employee Contact Number from user 
     *
     * @return employeeContact, Validated Employee Contact Number 
     */
    private long getEmployeeContact() {
        boolean isValidContact = false;
        long employeeContact = 0;
        
        StringBuilder errorMsg = new StringBuilder("Phone number already ")
                                           .append("available or Invalid ")
                                           .append("format , try another");
        System.out.println("Enter Employee Phone Number");
        StringBuilder displayString = new StringBuilder("Phone number starts")
                       .append(" only between 6-9, no special cases, no white")
                       .append(" spaces allowed and should be unique");
        System.out.println(displayString);

        while(!isValidContact) {
            try {
                employeeContact = Long.parseLong(inputReader.nextLine());
                isValidContact = employeeController
                                 .getEmployeeContactValidated(employeeContact); 
                if(!isValidContact) {
                    System.out.println(errorMsg);
                } 
            } catch(NumberFormatException e) {
                System.out.println("Enter only valid numbers");
            }
        }
        return employeeContact;
    }

    /**
     * Validates the given Employee Date of Birth from the user
     *
     * @return dob, Validated Date of Birth
     */
    private LocalDate getDateOfBirth() {
        LocalDate dob = null;
        boolean isValidDob = false;
        String[] dateArray;
        String stringDate = null;

        System.out.println("Enter DOB : dd-MM-yyyy");
        while (!isValidDob) { 
            System.out.println("Only above 18 yrs and below 60 yrs");
            try {
                stringDate = inputReader.nextLine();
                dateArray = stringDate.split("-");
                stringDate = String.join("-", dateArray[2], dateArray[1]
                                                          , dateArray[0]);
                dob = LocalDate.parse(stringDate);
                if(employeeController.getValidatedDOB(dob)) {
                    
                    isValidDob = true;
                } else {
                    System.out.println("Enter a valid dob");
                }    
            } catch(ArrayIndexOutOfBoundsException e) {
                System.out.println("Please enter valid Date");
            } catch(DateTimeParseException e) {
                System.out.println("Please enter valid Date");
            } 
        }
        return dob;
    }

    /**
     * Checks whether the Employee Records available or empty
     * 
     * @return boolean, true if records available  
     */
    private boolean isRecordsAvailable() {
        return employeeController.isRecordsAvailable();
    }

    /**
     * Gets and validates the employee details from user and Creates a new 
     * Employee
     */
    private void createEmployee() {
        boolean isValidId = false;
        while(!isValidId) {
            isValidId = true;
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setName(getEmployeeName());
            employeeDTO.setSalary(getEmployeeSalary());
            employeeDTO.setDob(getDateOfBirth());
            employeeDTO.setEmail(getEmployeeEmail());
            employeeDTO.setContact(getEmployeeContact());
            List<AddressDTO> addressList = new ArrayList<AddressDTO>();
            addressList.add(getEmployeeAddress());
            employeeDTO.setAddress(addressList); 
            System.out.println(employeeController.createEmployee(employeeDTO)
                                      ? "Employee created successfully!!" 
                                      : "Employee not created");
        }
    }

    /**
     * Gets and validates the address from user and Creates a new 
     * address for the employee
     *
     * @return AddressDTO, The created addressDTO 
     */
    private AddressDTO getEmployeeAddress() {
        System.out.println("Enter your address");
        AddressDTO addressDTO = new AddressDTO();
            
        addressDTO.setDoorNo(getDoorNo());
        addressDTO.setLandMark(getLandMark());
        addressDTO.setStreet(getStreet());
        addressDTO.setCity(getCity());
        addressDTO.setPincode(getPincode());
        return addressDTO;
    }

    /**
     * Validates the given Door number of the user
     *
     * @return doorno, Validated Door number
     */
    private String getDoorNo() {
        boolean isValidDoorNo = false;
        String doorNo = null;

        System.out.println("Enter Door Number");
        while (!isValidDoorNo) { 
            doorNo = inputReader.nextLine();
            isValidDoorNo = employeeController.getDoorNoValidated(doorNo);
            if(!isValidDoorNo) {
                System.out.println("Please enter a valid door no");
            }  
        }
        return doorNo;
    }

    /**
     * Validates the given Landmark of the user
     *
     * @return landmark, Validated landmark
     */
    private String getLandMark() {
        boolean isValidLandMark = false;
        String landMark = null;

        System.out.println("Enter Land Mark");
        while (!isValidLandMark) { 
            landMark = inputReader.nextLine();
            isValidLandMark = employeeController.getAddressValidated(landMark);
            if(!isValidLandMark) {
                System.out.println("Please enter a valid Land Mark");
            }  
        }
        return landMark;
    }

    /**
     * Validates the given street given by the user
     *
     * @return street, Validated street name
     */
    private String getStreet() {
        boolean isValidStreet = false;
        String street = null;

        System.out.println("Enter Street");
        while (!isValidStreet) { 
            street = inputReader.nextLine();
            isValidStreet = employeeController.getAddressValidated(street);
            if(!isValidStreet) {
                System.out.println("Please enter a valid Street");
            }  
        }
        return street;
    }

    /**
     * Validates the given city of the user
     *
     * @return city, Validated city
     */
    private String getCity() {
        boolean isValidCity = false;
        String city = null;

        System.out.println("Enter City");
        while (!isValidCity) { 
            city = inputReader.nextLine();
            isValidCity = employeeController.getAddressValidated(city);
            if(!isValidCity) {
                 System.out.println("Please enter a valid City");
            }  
        }
        return city;
    }

    /**
     * Validates the given Pincode of the user
     *
     * @return pincode, Validated pincode
     */
    private long getPincode() {
        boolean isValidPincode = false;
        long pincode = 0;

        System.out.println("Enter Pincode");
        while (!isValidPincode) { 
            try {
                pincode = Long.parseLong(inputReader.nextLine());
                isValidPincode = employeeController
                                .getPincodeValidated(pincode);
                if(!isValidPincode) {
                     System.out.println("Please enter a valid Pincode");
                }  
            } catch(NumberFormatException e) {
                System.out.println("Enter only valid Pincode");
            } 
        }
        return pincode;
    }
  
    /**
     * Displays View Employee Main Menu
     */
    private void viewEmployee() {
        boolean isValidChoice = false;

        while(!isValidChoice) {
            isValidChoice = true; 
            if(!(isRecordsAvailable())) {
                System.out.println("No records found");
            } else {
                System.out.println("1.View by ID 2.View all 3.Main Menu");
                int viewInput = getInputChoice();
                switch(viewInput) {
                    case 1 : viewEmployeeById();
                             break;    
                    case 2 : viewAllEmployee();
                             break;
                    case 3 : break;
                    default : System.out.println("Choose between 1 to 3");
                              isValidChoice = false;    
                              break;        
                }
            }
        }		
    }

    /**
     * Display all Employee details in the record
     */
    private void viewAllEmployee() {
        List<EmployeeDTO> viewList = employeeController.viewEmployee();
        for(EmployeeDTO employeeDTO : viewList) {
            System.out.println(employeeDTO);
            List<AddressDTO> addressList = employeeDTO.getAddress();
            int count = 1;
            for(AddressDTO address : addressList) {
                address.setSerialId(count);
                System.out.println(address);
                count++;
            }
            Set<ProjectDTO> projectDTOList = employeeDTO.getProjects();
            for(ProjectDTO projectDTO : projectDTOList) {
                System.out.println(projectDTO);
            }
        }
    }


    /**
     * Displays only the details of the Employee Id given by the user
     */
    private void viewEmployeeById() {
        int employeeId = getEmployeeId();
        if(employeeController.containsEmployee(employeeId)) {
            EmployeeDTO employeeDTO = employeeController
                                      .viewEmployeeById(employeeId);
            System.out.println(employeeDTO);
            List<AddressDTO> addressList = employeeDTO.getAddress();
            int count = 1;
            for(AddressDTO address : addressList) {
                address.setSerialId(count);
                System.out.println(address);
                count++;
            }
            Set<ProjectDTO> projectDTOList = employeeDTO.getProjects();
            for(ProjectDTO projectDTO : projectDTOList) {
                System.out.println(projectDTO);
            }
        } else {
            System.out.println("Employee Id not Available, Try Again");
            viewEmployee();
        }         
    } 

    /**
     * Displays Delete Employee Main Menu
     */
    private void deleteEmployee() {
        boolean isValidChoice = false;
        StringBuilder displayMsg = new StringBuilder("1.Delete by ID ");
        displayMsg.append("2.Delete all users 3.Delete address 4.Main Menu");
        while(!isValidChoice) {
            isValidChoice = true;
            if(!(isRecordsAvailable())) {
                System.out.println("No records found");
            } else {
                System.out.println(displayMsg);
                int inputChoice = getInputChoice();
                switch(inputChoice) {
                    case 1 : deleteEmployeeById();
                             break;
                    case 2 : deleteAllEmployee();
                             break;
                    case 3 : deleteAddress();
                             break;
                    case 4 : break;
                    default : System.out.println("Enter between from 1 - 3");
                              isValidChoice = false;
                              break;
                }
            }
        }    
    }

    /**
     * Deletes all the Employees
     */
    private void deleteAllEmployee() {
        boolean isRecordsDeleted = employeeController.deleteAllEmployee();
        System.out.println(isRecordsDeleted ? "Employees deleted"
                                            : "Employees not deleted");
    }
    
    /**
     * Deletes the details of the Employee Id given by the user
     */
    private void deleteEmployeeById() {
        int employeeId = getEmployeeId();
        if(employeeController.containsEmployee(employeeId)) {
            boolean isEmployeeDeleted = employeeController
                                        .deleteEmployeeById(employeeId);
            System.out.println(isEmployeeDeleted ? "User deleted"
                                                 : "User not deleted");
        } else {
            System.out.println("Invalid Employee ID ");
            deleteEmployee();
        }    
    } 

    /**
     * Gets the List of address of an employee and set a serial Id for 
     * each address to display
     *
     * @return List<AddressDTO>, List of AddressDTO after setting serial Id
     */
    private List<AddressDTO> getAddressList(List<AddressDTO> addressList) {
        boolean isValidId = false;
        AddressDTO addressDTO = new AddressDTO();
        int addressId = 0;
        while(!isValidId) {
            try {
                int serialId = Integer.parseInt(inputReader.nextLine());
                for(AddressDTO address : addressList) {
                    if(address.getSerialId() == serialId) {
                        addressDTO = address;
                        isValidId = true;    
                    }
                }
                addressList.remove(addressDTO);
                if(!isValidId) {
                    System.out.println("Enter valid serial id");
                }
            } catch(NumberFormatException e) {
                System.out.println("Enter a valid serial Id");
            }
        }
        return addressList;
    }

    /**
     * Delete an address of the user
     */
    private void deleteAddress() {
        int employeeId = getEmployeeId();
        boolean isAddressDeleted = false;
        StringBuilder errorMsg = new StringBuilder("Sorry! Employee should ")
                                .append("have atleast 2 address to delete one");
   
        if(employeeController.containsEmployee(employeeId)) {
            EmployeeDTO employeeDTO = employeeController
                                      .viewEmployeeById(employeeId);
            List<AddressDTO> addressList = employeeDTO.getAddress();
            int count = 1;
            for(AddressDTO address : addressList) {
                address.setSerialId(count);
                System.out.println(address);
                count++;
            }
            if(2 <= addressList.size()) {
                System.out.println("Enter the serial to be deleted");
                employeeDTO.setAddress(getAddressList(addressList));
                isAddressDeleted = employeeController
                                                    .deleteAddress(employeeDTO);
                System.out.println(isAddressDeleted ? "Address deleted"
                                                    : "Address not deleted");
            } else {
                System.out.println(errorMsg);  
                deleteEmployee();  
            }
        } else {
            System.out.println("Invalid Employee ID ");
            deleteEmployee();
        }    
    }

    /**
     * Displays Update Employee Verification menu
     */
    private void updateDisplay() {
        boolean isValidChoice = false;

        while(!isValidChoice) {
            isValidChoice = true;
            if(!(isRecordsAvailable())) {
                System.out.println("No records found");
            } else {
                int employeeId = getEmployeeId();
                if(employeeController.containsEmployee(employeeId)) {
                    StringBuilder stringBuilder = new StringBuilder("1.Update") 
                    .append(" All 2.Name 3.Salary 4.Email 5.DOB 6.Contact ")
                    .append("7.Add new address 8.Assign/Unassign Project")
                    .append("9.Main menu or Anyother to Back Menu");
                    System.out.println(stringBuilder);
                    updateEmployee(employeeId);
                } else {
                    goToMenu();    
                }
            }
        }
    }

    /**
     * Displays Update Employee Main Menu
     */
    private void updateEmployee(int employeeId) {
        int inputChoice = getInputChoice();

        switch(inputChoice) {
            case 1 : updateAllDetails(employeeId);
                     break;
            case 2 : updateEmployeeName(employeeId);
                     break;
            case 3 : updateEmployeeSalary(employeeId);
                     break;
            case 4 : updateEmployeeEmail(employeeId);
                     break;
            case 5 : updateEmployeeDob(employeeId);
                     break;
            case 6 : updateEmployeeContact(employeeId);
                     break;
            case 7 : addAddress(employeeId);
                     break;
            case 8 : assignUnassignDisplay(employeeId);
                     break;
            case 9 : break;
            default : break;
        }    
    }

    /**
     * Validates the choice number given by the user 
     *
     * @return inputChoice, Validated choice number 
     */
    private void assignUnassignDisplay(int employeeId) {
        boolean isValidNo = true;
        int inputChoice = 0;
        do {
            System.out.println("1.Assign Project 2.UnAssign Project 3.Back");
            inputChoice = getInputChoice();
            switch(inputChoice) {
                case 1 : assignProject(employeeId);
                         break;
                case 2 : unassignProject(employeeId);
                         break;
                case 3 : isValidNo = false;
                         break;
                default : System.out.println("Choose from the given numbers");
                          break;
            }
        } while(isValidNo);
    }

    /**
     * Returns to Update menu if 1 is given or Returns to Main menu if any 
     * other number given 
     */
    private void goToMenu() {
        StringBuilder displayString = new StringBuilder("Id invalid! ");
        displayString.append("Enter 1.Update or Any other Number for Home");
        System.out.println(displayString);
        int inputChoice = getInputChoice();
        if(inputChoice == 1) {
            updateDisplay();
        } else {
            showMainMenu();
        }
    }

    /**
     * Updates all details of an Employee
     *
     * @param employeeId
     */
    private void updateAllDetails(int employeeId) {
        boolean isUpdated = false;
        
        EmployeeDTO employeeDTO = employeeController
                                  .viewEmployeeById(employeeId);
        employeeDTO.setId(employeeId);
        employeeDTO.setName(getEmployeeName());
        employeeDTO.setSalary(getEmployeeSalary());
        employeeDTO.setDob(getDateOfBirth());
        employeeDTO.setContact(getEmployeeContact());
        employeeDTO.setEmail(getEmployeeEmail());
        System.out.println(employeeController.updateAllDetails(employeeDTO)
                                      ? "Employee updated successfully!!" 
                                      : "Employee not updated");
    }

    /**
     * Assign a project for the given Employee
     *
     * @param employeeId
     */
    private void assignProject(int employeeId) {
        EmployeeDTO employeeDTO = employeeController
                                                  .viewEmployeeById(employeeId);
        Set<ProjectDTO> projectDTOSet = employeeDTO.getProjects();
        List<ProjectDTO> projectDTOList = new ArrayList<>(employeeController
                                                             .viewAllProject());
        List<ProjectDTO> availableProjects = new ArrayList<>(projectDTOSet);
        projectDTOList.removeAll(availableProjects);
        ProjectDTO projectDTO = new ProjectDTO();

        if(assignDisplay(projectDTOList)) {
            System.out.println("Give the ProjectId to Assign for the Employee");
            int projectId = getProjectId();
            if(containsProject(projectDTOList, projectId)) {
                projectDTO = employeeController.viewProjectById(projectId);
                projectDTOSet.add(projectDTO);
                employeeDTO.setProjects(projectDTOSet);
                System.out.println(employeeController
                                               .updateAllDetails(employeeDTO) ?
                                                           "Assigned Project" :
                                                       "Project not assigned");
            } else {
                System.out.println("Sorry Project ID not available");    
            }
        } else {
            System.out.println("Sorry No Projects available");
        }
    }

    /**
     * Displays the available Projects if available
     *
     * @param projectDTOList
     * @return boolean, true if projects are available
     */
    private boolean assignDisplay(List<ProjectDTO> projectDTOList) {
        boolean isAvailable = false;

        if((null != projectDTOList) && !(projectDTOList.isEmpty())) {
            isAvailable = true;
            System.out.println("Project's Available");
            for(ProjectDTO project : projectDTOList) {
                StringBuilder display = new StringBuilder();
                display.append("Id : ").append(project.getId())
                      .append(" Name : ").append(project.getName());
                System.out.println(display);
            }
        }
        return isAvailable;
    }

    /**
     * Unassign a project for the given Employee Id
     *
     * @param employeeId
     */
    private void unassignProject(int employeeId) {
        EmployeeDTO employeeDTO = employeeController
                                                 .viewEmployeeById(employeeId);
        Set<ProjectDTO> projectDTOSet = employeeDTO.getProjects();
        ProjectDTO projectDTO = new ProjectDTO();
        if(unAssignDisplay(projectDTOSet)) {
            System.out.println("Enter the Project Id to be Unassigned");
            int projectId = getProjectId();
            if(containsProject(projectDTOSet, projectId)) {
                projectDTO = employeeController.viewProjectById(projectId);
                Set<ProjectDTO> unAssignSet = new HashSet<>();
                for(ProjectDTO unAssignProject : projectDTOSet) {
                    if(unAssignProject.getId() == employeeId) {
                        unAssignSet.add(unAssignProject);
                    }
                }
                projectDTOSet.removeAll(unAssignSet);
                employeeDTO.setProjects(projectDTOSet);
                System.out.println(employeeController
                                                .updateAllDetails(employeeDTO) ?
                                                         "Unassigned Employee" :
                                                    "Employee unassign failed");
            } else {
                System.out.println("Sorry Project ID not available");
            }
        } else {
            System.out.println("Sorry! No Projects Available for the Employee");
        }         
    }

    /**
     * Displays the available Projects if available
     *
     * @param projectDTOSet
     * @return boolean, true if projects are available
     */
    private boolean unAssignDisplay(Set<ProjectDTO> projectDTOSet) {
        boolean isAvailable = false;

        if((null != projectDTOSet) && !(projectDTOSet.isEmpty())) {
            isAvailable = true;
            System.out.println("Project's Available");
            for(ProjectDTO project : projectDTOSet) {
                StringBuilder display = new StringBuilder();
                display.append("Id : ").append(project.getId())
                      .append(" Name : ").append(project.getName());
                System.out.println(display);
            }
        }
        return isAvailable;
    }

    /**
     * To Check whether the Set of Project has the given ProjectId
     *
     * @param projectDTOSet
     * @param projectId
     * @return boolean, true if project is available
     */
    private boolean containsProject(Set<ProjectDTO> projectDTOSet, 
                                                               int projectId) {
        boolean isAvailable = false;
        if(null != projectDTOSet) {
            for(ProjectDTO projectDTO : projectDTOSet) {
                if(projectDTO.getId() == projectId) {
                    isAvailable = true;
                } 
            }
        }
        return isAvailable;
    }

    /**
     * To Check whether the List of Project has the given ProjectId
     *
     * @param projectDTOList
     * @param projectId
     * @return boolean, true if project is available
     */    
    private boolean containsProject(List<ProjectDTO> projectDTOList, 
                                                               int projectId) {
        boolean isAvailable = false;
        if(null != projectDTOList) {
            for(ProjectDTO projectDTO : projectDTOList) {
                if(projectDTO.getId() == projectId) {
                    isAvailable = true;
                } 
            }
        }
        return isAvailable;
    }

    /**
     * Validates the project Id given by the user
     *
     * @return projectId Validated
     */
    private int getProjectId(){
        boolean isValidId = true;
        int projectId = 0;
        while(isValidId) {
            try {
                String stringId = inputReader.nextLine() ;
                String pattern = "^[0-9]{1,4}";
                if(stringId.matches(pattern)) {
                    projectId = Integer.parseInt(stringId);
                    isValidId = false;
                } else {
                    System.out.println("Enter a valid Project Id");
                }
            } catch(NumberFormatException e) {
                System.out.println("Enter a valid Project Id");
            }
        }
        return projectId;       
    }
    
    /**
     * Updates the Name of an Employee
     *
     * @param employeeId
     */
    private void updateEmployeeName(int employeeId) {
        EmployeeDTO employeeDTO = employeeController
                                  .viewEmployeeById(employeeId);
        employeeDTO.setName(getEmployeeName());
        System.out.println(employeeController.updateAllDetails(employeeDTO) ?
                                                    "Employee Name updated" :
                                                         "Name not updated");
    }

    /**
     * Updates the Salary of an Employee
     *
     * @param employeeId
     */
    private void updateEmployeeSalary(int employeeId) {
        EmployeeDTO employeeDTO = employeeController.viewEmployeeById
                                                     (employeeId);
        employeeDTO.setSalary(getEmployeeSalary());
        System.out.println(employeeController.updateAllDetails(employeeDTO) ?
                                                  "Employee Salary updated" :
                                                       "Salary not updated");        
    }

    /**
     * Updates the Email ID of an Employee
     *
     * @param employeeId
     */ 
    private void updateEmployeeEmail(int employeeId) {
        EmployeeDTO employeeDTO = employeeController.viewEmployeeById
                                                     (employeeId);
        employeeDTO.setEmail(getEmployeeEmail());
        System.out.println(employeeController.updateAllDetails(employeeDTO) ?
                                                  "Employee Salary updated" :
                                                       "Salary not updated");
    }

    /**
     * Adds new address for the given employee
     *
     * @param employeeId
     */
    private void addAddress(int employeeId) {
        EmployeeDTO employeeDTO = employeeController.viewEmployeeById
                                                     (employeeId);
        AddressDTO addressDTO = getEmployeeAddress();
        List<AddressDTO> addressList = employeeDTO.getAddress();
        addressList.add(addressDTO);
        employeeDTO.setAddress(addressList);
        System.out.println(employeeController.updateAllDetails(employeeDTO) ?
                                                 "Employee Address updated" :
                                                      "Address not updated");
    }

    /**
     * Updates the Contact Number of an Employee
     *
     * @param employeeId
     */
    private void updateEmployeeContact(int employeeId) {
        EmployeeDTO employeeDTO = employeeController.viewEmployeeById
                                                     (employeeId);
        employeeDTO.setContact(getEmployeeContact());
        System.out.println(employeeController.updateAllDetails(employeeDTO) ?
                                                 "Employee Contact updated" :
                                                      "Contact not updated");        
    }
    
    /**
     * Updates the Date of Birth of an Employee
     *
     * @param employeeId
     */
    private void updateEmployeeDob(int employeeId) {
        EmployeeDTO employeeDTO = employeeController.viewEmployeeById
                                                     (employeeId);
        employeeDTO.setDob(getDateOfBirth());
        System.out.println(employeeController.updateAllDetails(employeeDTO) ?
                                                     "Employee DOB updated" :
                                                          "DOB not updated");        
    }
}
