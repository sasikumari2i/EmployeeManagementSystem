/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.view;

import com.ideas2it.project.dto.EmployeeDTO;
import com.ideas2it.project.controller.EmployeeController;
import java.util.Scanner;
import java.time.LocalDate;
import java.lang.NumberFormatException;
import java.time.format.DateTimeParseException;
import java.lang.ArrayIndexOutOfBoundsException;
import java.time.Period;
import java.util.List;

/**
 * User Interface for getting inputs and printing the appropriate outputs
 *
 * @version	1.0
 * @date	14 Oct 2021
 * @author	Sasikumar Raju
 */
public class EmployeeView {
    EmployeeController controller = new EmployeeController();
    Scanner inputReader = new Scanner(System.in);

    /**
     * Displays the main menu and switches to the required option
     */
    public void getMainMenu() {
        boolean isValid = true;
        int inputChoice = 0;
        
        System.out.println("-----Employee Management System------");
        while(isValid) {
            System.out.println("1.CREATE 2.DISPLAY 3.DELETE 4.UPDATE");
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
                default : System.out.println("Choose from the given numbers");    
                break;
            }
        }
    }
    
    /**
     * Validates the choice number given
     *
     * @return inputChoice, Validated choice number 
     */
    public int getInputChoice() {
        int inputChoice = 0;
        boolean isValid = false;
        
        while(!isValid) {
            try {
                inputChoice = Integer.parseInt(inputReader.nextLine());
                isValid = controller.getChoiceValidated(inputChoice);
                if(!isValid) {
                    System.out.println("Enter a valid option");
                }
            } catch(NumberFormatException e) {
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
    public int getEmployeeId() {
        boolean isValid = false;
        int employeeId = 0;
        
        System.out.println("Enter Employee Id");
        StringBuilder displayString = new StringBuilder("Employee ID can have");
        displayString.append(" only upto 4 digits,greater than zero");
        System.out.println(displayString);
        while(!isValid) {
            try {
                employeeId  = Integer.parseInt(inputReader.nextLine());
                isValid = controller.getEmployeeIdValidated(employeeId);
                if((!isValid) || (0 >= employeeId)) {
                    System.out.println("Invalid Id, try again");
                    isValid = false;
                }
            } catch(NumberFormatException e) {
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
    public String getEmployeeName() {
        boolean isValid = false;
        String employeeName = null;
        
        System.out.println("Enter Employee Name");
        StringBuilder displayString = new StringBuilder("Does not allow ");
        displayString.append("special characters or digits");
        System.out.println(displayString);
        while(!isValid) {
            employeeName  = inputReader.nextLine();
            isValid = controller.getEmployeeNameValidated(employeeName);
            if(!isValid) {
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
    public float getEmployeeSalary() {
        boolean isValid = false;
        float employeeSalary = 0;
 
        System.out.println("Enter Employee Salary");
        while(!isValid) {
            try {
                employeeSalary = Float.parseFloat(inputReader.nextLine());
                isValid = controller.getEmployeeSalaryValidated(employeeSalary);
                if(!isValid) {
                    System.out.println("Enter a valid salary");
                }
            } catch(NumberFormatException e) {
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
    public String getEmployeeEmail() {
        boolean isValid = false;
        String employeeEmail = null;

        System.out.println("Enter Employee Email");
        StringBuilder displayString = new StringBuilder("Only Standard format");
        displayString.append(" is acceptable,only(-_.) only these 3 ");
        displayString.append("characters are allowed");
        System.out.println(displayString);
 
        while(!isValid) {
            employeeEmail = inputReader.nextLine();
            isValid = controller.getEmployeeEmailValidated(employeeEmail);
            boolean isDuplicate = isEmailDuplicate(employeeEmail);
            if(isValid) {
                if(isDuplicate) {
                    isValid = false;
                    System.out.println("EmailId already available");  
                }
            } else {
                System.out.println("Invalid Email, Try another");
            }
        }
        return employeeEmail;
    }
    
    /**
     * Validates the given Employee Contact Number from user 
     *
     * @return employeeContact, Validated Employee Contact Number 
     */
    public long getEmployeeContact() {
        boolean isValid = false;
        long employeeContact = 0;
        
        System.out.println("Enter Employee Phone Number");
        StringBuilder displayString = new StringBuilder("Phone number starts");
        displayString.append(" only between 6-9, no special cases, no white");
        displayString.append(" spaces allowed and should be unique");
        System.out.println(displayString);

        while(!isValid) {
            try {
                employeeContact = Long.parseLong(inputReader.nextLine());
                isValid = controller
                          .getEmployeeContactValidated(employeeContact); 
                boolean isDuplicate = isContactDuplicate(employeeContact);
                if(isValid) {
                    if(isDuplicate) {
                        isValid = false;
                        System.out.println("Phone number already available");  
                    }
                } else {
                    System.out.println("Invalid Number, Try another");
                } 
            } catch(NumberFormatException e) {
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
    public LocalDate getDateOfBirth() {
        LocalDate dob = null;
        boolean isCorrectDob = true;
        String[] dateArray;
        String date = null;

        while (isCorrectDob) { 
            System.out.println("Enter DOB : dd-MM-yyyy");
            try {
                date = inputReader.nextLine();
                dateArray = date.split("-");
                date = String.join("-", dateArray[2], dateArray[1]
                                   , dateArray[0]);
                dob = LocalDate.parse(date);
                Period period = Period.between(dob, LocalDate.now());
                if(!((period.getYears() < 60) && (period.getYears() > 18))) {
                    System.out.println("Only above 18 yrs and below 60 yrs");
                } else {
                    isCorrectDob = false;
                } 
            } catch (DateTimeParseException e) {
                System.out.println("Please enter valid Date");           
            } catch(ArrayIndexOutOfBoundsException e ) {
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
    public boolean isRecordsAvailable() {
        return controller.isRecordsAvailable();
    }

    /**
     * Checks whether the Contact Number is unique
     *
     * @return boolean 
     */
    public boolean isContactDuplicate(long employeeContact) {
        return controller.isContactDuplicate(employeeContact);
    }   
   
    /**
     * Checks whether the Email is unique 
     *
     * @return boole
     */
    public boolean isEmailDuplicate(String employeeEmail) {
        return controller.isEmailDuplicate(employeeEmail);
    }

    /**
     * Creates new Employee using controller class using given details 
     */
    public void createEmployee() {
        int employeeId = getEmployeeId();
        if(controller.checkEmployeeId(employeeId)) {
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
            controller.createEmployee(employeeId, employeeDTO);
        }
    }
  
    /**
     * Displays View Employee Main Menu
     */
    public void viewEmployee() {
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
                case 3 :             
                break;
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
        List<EmployeeDTO> viewList = controller.viewEmployee();
        for(EmployeeDTO employeeDTO : viewList) {
            System.out.println(employeeDTO);
        }
    }


    /**
     * Displays only the details of given Employee Id
     */
    private void viewEmployeeById() {
        int employeeId = getEmployeeId();
        if(controller.checkEmployeeId(employeeId)) {
            System.out.println(controller.viewEmployeeById(employeeId));
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
                case 2 : controller.deleteAllEmployee();
                    System.out.println("All the Users are deleted");     
                break;
                case 3 : 
                break;
                default : System.out.println("Enter between from 1 - 3");
                    deleteEmployee();
                break;
            }
        }    
    }
    
    /**
     * Delete the details of the given Employee Id
     */
    private void deleteEmployeeById() {
        int employeeId = getEmployeeId();
        if(controller.checkEmployeeId(employeeId)) {
            controller.deleteEmployeeById(employeeId);
            System.out.println("User " + employeeId + " is deleted");
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
            if(controller.checkEmployeeId(employeeId)) {
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
                    case 7 :
                    break;
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
            getMainMenu();
        }
    }

    /**
     * To update all details of an Employee
     *
     * @param employeeId
     */
    public void updateAllDetails(int employeeId) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
   
        employeeDTO.setName(getEmployeeName());
        employeeDTO.setSalary(getEmployeeSalary());
        employeeDTO.setDob(getDateOfBirth());
        employeeDTO.setContact(getEmployeeContact());
        employeeDTO.setEmail(getEmployeeEmail());
        controller.updateAllDetails(employeeId, employeeDTO);                          
    }
    
    /**
     * To update the Name of an Employee
     *
     * @param employeeId
     */
    public void updateEmployeeName(int employeeId) {
        String employeeName = getEmployeeName();
        controller.updateEmployeeName(employeeId, employeeName);        
    }

    /**
     * To update the Salary of an Employee
     *
     * @param employeeId
     */
    public void updateEmployeeSalary(int employeeId) {
        float employeeSalary = getEmployeeSalary();
        controller.updateEmployeeSalary(employeeId, employeeSalary);        
    }

    /**
     * To update the Email ID of an Employee
     *
     * @param employeeId
     */ 
    public void updateEmployeeEmail(int employeeId) {
        String employeeEmail = getEmployeeEmail();
        controller.updateEmployeeEmail(employeeId, employeeEmail);        
    }

    /**
     * To update the Contact Number of an Employee
     *
     * @param employeeId
     */
    public void updateEmployeeContact(int employeeId) {
        long employeeContact = getEmployeeContact();
        controller.updateEmployeeContact(employeeId, employeeContact);        
    }
    
    /**
     * To update the Date of Birth of an Employee
     *
     * @param employeeId
     */
    public void updateEmployeeDob(int employeeId) {
        LocalDate employeeDob = getDateOfBirth();
        controller.updateEmployeeDob(employeeId, employeeDob);        
    }
}






