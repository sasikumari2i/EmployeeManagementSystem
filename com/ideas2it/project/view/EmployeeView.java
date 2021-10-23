/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.view;

import java.lang.ArrayIndexOutOfBoundsException;
import java.lang.NumberFormatException;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.ideas2it.project.controller.EmployeeController;
import com.ideas2it.project.dto.EmployeeDTO;

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
     * Displays the main menu and switches to the required option
     */
    public void showMainMenu() {
        boolean isCorrectStart = true;
        int inputChoice = 0;
        
        System.out.println("-----Welcome!!!-----");
        do {
            System.out.println("1.CREATE 2.DISPLAY 3.DELETE 4.UPDATE 5.Exit ");
            inputChoice = getInputChoice();
            switch(inputChoice) {
                case 1 : createEmployee();
                         break;
                case 2 : viewEmployee();
                         break;
                case 3 : deleteEmployee();
                         break;
                case 4 : updateEmployee();
                         break;
                case 5 : isCorrectStart = false;
                         System.out.println("Thank you!!!");
                         break;
                default : System.out.println("Choose from the given numbers");    
                          break;
            }
        } while(isCorrectStart);
    }
    
    /**
     * Validates the choice number given 
     *
     * @return inputChoice, Validated choice number 
     */
    private int getInputChoice() {
        int inputChoice = 0;
        boolean isValidChoice = false;
        
        while(!isValidChoice) {
            try {
                inputChoice = inputReader.nextInt();
                inputReader.skip("\n");
                isValidChoice = employeeController.getChoiceValidated
                                                   (inputChoice);
                if(!isValidChoice) {
                    System.out.println("Enter a valid option");
                }
            } catch(InputMismatchException e) {
                inputReader.nextLine();
                System.out.println("Enter only number");     
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
        StringBuilder displayString = new StringBuilder("Employee ID can have");
        displayString.append(" only upto 4 digits,greater than zero");
        System.out.println(displayString);
        while(!isValidId) {
            try {
                employeeId  = inputReader.nextInt();
                inputReader.skip("\n");
                isValidId = employeeController.getEmployeeIdValidated
                                               (employeeId);
                if(!isValidId) {
                    System.out.println("Invalid Id, try again");
                    isValidId = false;
                }
            } catch(InputMismatchException e) {
                inputReader.nextLine();
                System.out.println("Enter only numbers");
            }
        }
        return employeeId;
    }
    
    /**
     * Validates the given Employee Name from user 
     *
     * @return employeeName, Validated Employee Name 
     */
    private String getEmployeeName() {
        boolean isValidName = false;
        String employeeName = null;
        
        System.out.println("Enter Employee Name");
        StringBuilder displayString = new StringBuilder("Does not allow ");
        displayString.append("special characters or digits");
        System.out.println(displayString);

        while(!isValidName) {
            employeeName  = inputReader.nextLine();
            isValidName = employeeController.getEmployeeNameValidated
                                            (employeeName);
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
                employeeSalary = inputReader.nextFloat();
                inputReader.skip("\n");
                isValidSalary = employeeController.getEmployeeSalaryValidated
                                          (employeeSalary);
                if(!isValidSalary) {
                    System.out.println("Enter a valid salary");
                }
            } catch(InputMismatchException e) {
                inputReader.nextLine();
                System.out.println("Enter only numbers");
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

        System.out.println("Enter Employee Email");
        StringBuilder displayString = new StringBuilder("Only Standard format");
        displayString.append(" is acceptable,only(-_.) only these 3 ");
        displayString.append("characters are allowed");
        System.out.println(displayString);
 
        while(!isValidEmail) {
            employeeEmail = inputReader.nextLine();
            isValidEmail = employeeController.getEmployeeEmailValidated
                                             (employeeEmail);
            if(!isValidEmail) {
                StringBuilder errorMsg = new StringBuilder("Email is already");
                errorMsg.append(" available or invalid format, Try another");
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
        
        System.out.println("Enter Employee Phone Number");
        StringBuilder displayString = new StringBuilder("Phone number starts");
        displayString.append(" only between 6-9, no special cases, no white");
        displayString.append(" spaces allowed and should be unique");
        System.out.println(displayString);

        while(!isValidContact) {
            try {
                employeeContact = inputReader.nextLong();
                inputReader.skip("\n");
                isValidContact = employeeController
                                 .getEmployeeContactValidated(employeeContact); 
                if(!isValidContact) {
                    StringBuilder errorMsg = new StringBuilder("Phone number ");
                    errorMsg.append("already available or Invalid ")
                            .append("format) , try another");
                    System.out.println(errorMsg);
                } 
            } catch(InputMismatchException e) {
                inputReader.nextLine();
                System.out.println("Enter only numbers");
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
        boolean isCorrectDob = false;
        String[] dateArray;
        String dateString = null;

        System.out.println("Enter DOB : dd-MM-yyyy");
        while (!isCorrectDob) { 
            System.out.println("Only above 18 yrs and below 60 yrs");
            try {
                dateString = inputReader.nextLine();
                dateArray = dateString.split("-");
                dateString = String.join("-", dateArray[2], dateArray[1]
                                                          , dateArray[0]);
                dob = LocalDate.parse(dateString);
                if(employeeController.getValidatedDOB(dob)) {
                    
                    isCorrectDob = true;
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
     * @return boolean 
     */
    private boolean isRecordsAvailable() {
        return employeeController.isRecordsAvailable();
    }

    /**
     * Checks whether the Contact Number is unique
     *
     * @return boolean 
     * @param employeeContact, Contact number of the employee
     */
    private boolean isContactDuplicate(long employeeContact) {
        return employeeController.isContactDuplicate(employeeContact);
    }   
   
    /**
     * Checks whether the Email is unique 
     *
     * @return boolean
     * @param employeeEmail, email Id of the employee
     */
    private boolean isEmailDuplicate(String employeeEmail) {
        return employeeController.isEmailDuplicate(employeeEmail);
    }

    /**
     * Creates new Employee 
     */
    private void createEmployee() {
        int employeeId = getEmployeeId();
        if(employeeController.checkEmployeeId(employeeId)) {
            System.out.println("Employee ID already available, Try another");
            createEmployee();
        } else {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employeeId);
            employeeDTO.setName(getEmployeeName());
            employeeDTO.setSalary(getEmployeeSalary());
            employeeDTO.setDob(getDateOfBirth());
            employeeDTO.setEmail(getEmployeeEmail());
            employeeDTO.setContact(getEmployeeContact());
            employeeController.createEmployee(employeeDTO);
        }
    }
  
    /**
     * Displays View Employee Main Menu
     */
    private void viewEmployee() {
        if(isRecordsAvailable()) {
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
                          viewEmployee();    
                          break;        
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
        }
    }


    /**
     * Displays only the details of given Employee Id
     */
    private void viewEmployeeById() {
        int employeeId = getEmployeeId();
        if(employeeController.checkEmployeeId(employeeId)) {
            System.out.println(employeeController.viewEmployeeById(employeeId));
        } else {
            System.out.println("Employee Id not Available, Try Again");
            viewEmployee();
        }         
    } 

    /**
     * Displays Delete Employee Main Menu
     */
    private void deleteEmployee() {
        if(isRecordsAvailable()) {
            System.out.println("No records found");
        } else {
            System.out.println("1.Delete by ID 2.Delete all users 3.Main Menu");
            int inputChoice = getInputChoice();
            switch(inputChoice) {
                case 1 : deleteEmployeeById();
                         break;
                case 2 : deleteAllEmployee();
                         break;
                case 3 : break;
                default : System.out.println("Enter between from 1 - 3");
                          deleteEmployee();
                          break;
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
     * Delete the details of the given Employee Id
     */
    private void deleteEmployeeById() {
        int employeeId = getEmployeeId();
        if(employeeController.checkEmployeeId(employeeId)) {
            boolean isEmployeeDeleted = employeeController.deleteEmployeeById
                                                  (employeeId);
            System.out.println(isEmployeeDeleted ? "User not deleted"
                                                 : "User deleted");
        } else {
            System.out.println("Invalid Employee ID ");
            deleteEmployee();
        }    
    } 

    /**
     * Displays Update Employee Main Menu
     */
    private void updateEmployee() {
        if(isRecordsAvailable()) {
            System.out.println("No records found");
        } else {
            int employeeId = getEmployeeId();
            if(employeeController.checkEmployeeId(employeeId)) {
                StringBuilder stringBuilder = new StringBuilder("1.Update All"); 
                stringBuilder.append(" 2.Name 3.Salary 4.Email 5.DOB 6.");
                stringBuilder.append("Contact 7.Main Menu or Any other number"); 
                System.out.println(stringBuilder.append(" to Back Menu"));

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
                    case 7 : break;
                    default : updateEmployee(); 
                              break;
                }
            } else {
                goToMenu();    
            }
        }    
    }

    /**
     * Returns to Update menu if 1 is given or Returns to Main menu if any 
     * other number given 
     */
    private void goToMenu() {
        StringBuilder displayString = new StringBuilder("Number invalid! ");
        displayString.append("Enter 1.Update or Any other Number for Home");
        System.out.println(displayString);

        int inputChoice = getInputChoice(); 
        if(inputChoice == 1) {
            updateEmployee();
        } else {
            showMainMenu();
        }
    }

    /**
     * To update all details of an Employee
     *
     * @param employeeId
     */
    private void updateAllDetails(int employeeId) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
   
        employeeDTO.setName(getEmployeeName());
        employeeDTO.setSalary(getEmployeeSalary());
        employeeDTO.setDob(getDateOfBirth());
        employeeDTO.setContact(getEmployeeContact());
        employeeDTO.setEmail(getEmployeeEmail());
        employeeController.updateAllDetails(employeeId, employeeDTO);
    }
    
    /**
     * To update the Name of an Employee
     *
     * @param employeeId
     */
    private void updateEmployeeName(int employeeId) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        String employeeName = getEmployeeName();
        employeeController.updateEmployeeName(employeeId, employeeName);        
    }

    /**
     * To update the Salary of an Employee
     *
     * @param employeeId
     */
    private void updateEmployeeSalary(int employeeId) {
        float employeeSalary = getEmployeeSalary();
        employeeController.updateEmployeeSalary(employeeId, employeeSalary);        
    }

    /**
     * To update the Email ID of an Employee
     *
     * @param employeeId
     */ 
    private void updateEmployeeEmail(int employeeId) {
        String employeeEmail = getEmployeeEmail();
        employeeController.updateEmployeeEmail(employeeId, employeeEmail);        
    }

    /**
     * To update the Contact Number of an Employee
     *
     * @param employeeId
     */
    private void updateEmployeeContact(int employeeId) {
        long employeeContact = getEmployeeContact();
        employeeController.updateEmployeeContact(employeeId, employeeContact);        
    }
    
    /**
     * To update the Date of Birth of an Employee
     *
     * @param employeeId
     */
    private void updateEmployeeDob(int employeeId) {
        LocalDate employeeDob = getDateOfBirth();
        employeeController.updateEmployeeDob(employeeId, employeeDob);        
    }
}






