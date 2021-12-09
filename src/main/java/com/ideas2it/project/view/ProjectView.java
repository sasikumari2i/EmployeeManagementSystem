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

import com.ideas2it.project.controller.ProjectController;
import com.ideas2it.project.model.dto.AddressDTO;
import com.ideas2it.project.model.dto.EmployeeDTO;
import com.ideas2it.project.model.dto.ProjectDTO;

/**
 * User Interface for getting inputs and displaying the appropriate outputs
 *
 * @version	1.0
 * @author	Sasikumar Raju
 */
public class ProjectView {
    private ProjectController projectController = new ProjectController();
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
                case 1 : createProject();
                         break;
                case 2 : viewProject();
                         break;
                case 3 : deleteProject();
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
                isValidChoice = projectController
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
    private int getProjectId() {
        boolean isValidId = false;
        int projectId = 0;
        
        System.out.println("Enter Project Id");
        StringBuilder displayString = new StringBuilder("Project ID can have")
                               .append(" only upto 4 digits,greater than zero");
        System.out.println(displayString);
        while(!isValidId) {
            try {
                projectId  = Integer.parseInt(inputReader.nextLine());
                isValidId = projectController.getProjectIdValidated
                                               (projectId);
                if(!isValidId) {
                    System.out.println("Invalid Id, try again");
                    isValidId = false;
                }
            } catch(NumberFormatException e) {
                System.out.println("Enter only valid numbers");
            }
        }
        return projectId;
    }
    
    /**
     * Validates the given Employee Name by user 
     *
     * @return employeeName, Validated Employee Name 
     */
    private String getProjectName() {
        boolean isValidName = false;
        String projectName = null;
        
        System.out.println("Enter Project Name");
        StringBuilder displayString = new StringBuilder("Does not allow ")
                                        .append("special characters or digits");
        System.out.println(displayString);

        while(!isValidName) {
            projectName  = inputReader.nextLine();
            isValidName = projectController
                          .getProjectNameValidated(projectName);
            if(!isValidName) {
                System.out.println("Enter a valid name");
            }
        }
        return projectName;
    }

    /**
     * Validates the given Employee Name by user 
     *
     * @return employeeName, Validated Employee Name 
     */
    private String getProjectDomain() {
        boolean isValidDomain = false;
        String domain = null;
        
        System.out.println("Enter Domain Name");
        StringBuilder displayString = new StringBuilder("Does not allow ")
                                        .append("special characters or digits");
        System.out.println(displayString);

        while(!isValidDomain) {
            domain  = inputReader.nextLine();
            isValidDomain = projectController
                          .getProjectDomainValidated(domain);
            if(!isValidDomain) {
                System.out.println("Enter a valid domain");
            }
        }
        return domain;
    }
    
    /**
     * Validates the given Employee Email from user 
     *
     * @return employeeEmail, Validated Employee Email 
     */
    private String getProjectStatus() {
        boolean isValidStatus = false;
        String stringStatus = null;
        int status = 0;
        
        System.out.println("Enter the status of the project");
        System.out.println("Enter 1 if active or 2 if Inactive");
        while(!isValidStatus) {
            isValidStatus = true;
            //status = Integer.parseInt(inputReader.nextLine());
            int inputChoice = getInputChoice();
            switch(inputChoice) {
                case 1 : stringStatus = "Active";
                         break;
                case 2 : stringStatus = "Inactive";
                         break;
                default : System.out.println("Enter a valid number");
                          isValidStatus = false; 
                          break;
            }
        }
        return stringStatus;
    }
    
    /**
     * Validates the given Employee Date of Birth from the user
     *
     * @return dob, Validated Date of Birth
     */
    private LocalDate getStartDate() {
        LocalDate startDate = null;
        boolean isValidDate = false;
        String[] dateArray;
        String stringDate = null;

        System.out.println("Enter the start date : dd-MM-yyyy");
        while (!isValidDate) { 
            //System.out.println("Only above 18 yrs and below 60 yrs");
            try {
                stringDate = inputReader.nextLine();
                dateArray = stringDate.split("-");
                stringDate = String.join("-", dateArray[2], dateArray[1]
                                                          , dateArray[0]);
                startDate = LocalDate.parse(stringDate);
                if(projectController.getValidatedStartDate(startDate)) {
                    
                    isValidDate = true;
                } else {
                    System.out.println("Enter a valid date");
                }    
            } catch(ArrayIndexOutOfBoundsException e) {
                System.out.println("Please enter valid Date");
            } catch(DateTimeParseException e) {
                System.out.println("Please enter valid Date");
            } 
        }
        return startDate;
    }

    /**
     * Validates the given Employee Date of Birth from the user
     *
     * @return dob, Validated Date of Birth
     */
    private LocalDate getEndDate() {
        LocalDate endDate = null;
        boolean isValidDate = false;
        String[] dateArray;
        String stringDate = null;

        System.out.println("Enter End Date : dd-MM-yyyy");
        while (!isValidDate) { 
            //System.out.println("Only above 18 yrs and below 60 yrs");
            try {
                stringDate = inputReader.nextLine();
                dateArray = stringDate.split("-");
                stringDate = String.join("-", dateArray[2], dateArray[1]
                                                          , dateArray[0]);
                endDate = LocalDate.parse(stringDate);
                if(projectController.getValidatedEndDate(endDate)) {
                    
                    isValidDate = true;
                } else {
                    System.out.println("Enter a valid date");
                }    
            } catch(ArrayIndexOutOfBoundsException e) {
                System.out.println("Please enter valid Date");
            } catch(DateTimeParseException e) {
                System.out.println("Please enter valid Date");
            } 
        }
        return endDate;
    }

    /**
     * Checks whether the Employee Records available or empty
     * 
     * @return boolean, true if records available  
     */
    private boolean isRecordsAvailable() {
        return projectController.isRecordsAvailable();
    }

    /**
     * Gets and validates the employee details from user and Creates a new 
     * Employee
     */
    private void createProject() {
        boolean isValidId = false;
        while(!isValidId) {
            isValidId = true;
            ProjectDTO projectDTO = new ProjectDTO();
            projectDTO.setName(getProjectName());
            projectDTO.setStartDate(getStartDate());
            projectDTO.setEndDate(getEndDate());
            projectDTO.setDomain(getProjectDomain());
            projectDTO.setStatus(getProjectStatus());
            System.out.println(projectController.createProject(projectDTO)
                                      ? "Project created successfully!!" 
                                      : "Project not created");
        }
    }

    /**
     * Displays View Employee Main Menu
     */
    private void viewProject() {
        boolean isValidChoice = false;

        while(!isValidChoice) {
            isValidChoice = true; 
            if(!(isRecordsAvailable())) {
                System.out.println("No records found");
            } else {
                System.out.println("1.View by ID 2.View all 3.Main Menu");
                int viewInput = getInputChoice();
                switch(viewInput) {
                    case 1 : viewProjectById();
                             break;    
                    case 2 : viewAllProject();
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
    private void viewAllProject() {
        List<ProjectDTO> viewList = projectController.viewProject();
        for(ProjectDTO projectDTO : viewList) {
            System.out.println(projectDTO);
            Set<EmployeeDTO> employeeDTOList = projectDTO.getEmployees();
            for(EmployeeDTO employeeDTO : employeeDTOList) {
                System.out.println(employeeDTO);
            }
        }
    }


    /**
     * Displays only the details of the Employee Id given by the user
     */
    private void viewProjectById() {
        int projectId = getProjectId();
        if(projectController.containsProject(projectId)) {
            ProjectDTO projectDTO = projectController
                                      .viewProjectById(projectId);
            System.out.println(projectDTO);
            Set<EmployeeDTO> employeeDTOList = projectDTO.getEmployees();
            for(EmployeeDTO employeeDTO : employeeDTOList) {
                System.out.println(employeeDTO);
            }
        } else {
            System.out.println("Project Id not Available, Try Again");
            viewProject();
        }         
    } 

    /**
     * Displays Delete Employee Main Menu
     */
    private void deleteProject() {
        boolean isValidChoice = false;
        StringBuilder displayMsg = new StringBuilder("1.Delete by ID ");
        displayMsg.append("2.Delete all users 3.Main Menu");
        while(!isValidChoice) {
            isValidChoice = true;
            if(!(isRecordsAvailable())) {
                System.out.println("No records found");
            } else {
                System.out.println(displayMsg);
                int inputChoice = getInputChoice();
                switch(inputChoice) {
                    case 1 : deleteProjectById();
                             break;
                    case 2 : deleteAllProject();
                             break;
                    case 3 : break;
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
    private void deleteAllProject() {
        boolean isRecordsDeleted = projectController.deleteAllProject();
        System.out.println(isRecordsDeleted ? "Projects deleted"
                                            : "Projects not deleted");
    }
    
    /**
     * Deletes the details of the Employee Id given by the user
     */
    private void deleteProjectById() {
        int projectId = getProjectId();
        if(projectController.containsProject(projectId)) {
            boolean isProjectDeleted = projectController
                                        .deleteProjectById(projectId);
            System.out.println(isProjectDeleted ? "Project deleted"
                                                 : "Project not deleted");
        } else {
            System.out.println("Invalid Project ID ");
            deleteProject();
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
                int projectId = getProjectId();
                if(projectController.containsProject(projectId)) {
                    StringBuilder stringBuilder = new StringBuilder("1.Update") 
                    .append(" All 2.Name 3.Domain 4.Status 5.Start Date ")
                    .append("6.End Date 7.Assign/Unassign Employee")
                    .append(" 8.Main menu or Anyother to Back Menu");
                    System.out.println(stringBuilder);
                    updateEmployee(projectId);
                } else {
                    goToMenu();    
                }
            }
        }
    }

    /**
     * Displays Update Employee Main Menu
     */
    private void updateEmployee(int projectId) {
        int inputChoice = getInputChoice();

        switch(inputChoice) {
            case 1 : updateAllDetails(projectId);
                     break;
            case 2 : updateProjectName(projectId);
                     break;
            case 3 : updateProjectDomain(projectId);
                     break;
            case 4 : updateProjectStatus(projectId);
                     break;
            case 5 : updateProjectStartDate(projectId);
                     break;
            case 6 : updateProjectEndDate(projectId);
                     break;
            case 7 : assignUnassignDisplay(projectId);
                     break;
            default : break;
        }    
    }

    /**
     * Validates the choice number given by the user 
     *
     * @return inputChoice, Validated choice number 
     */
    private void assignUnassignDisplay(int projectId) {
        boolean isValidNo = true;
        int inputChoice = 0;
        do {
            System.out.println("1.Assign Employee 2.UnAssign Employee 3.Back");
            inputChoice = getInputChoice();
            switch(inputChoice) {
                case 1 : assignEmployee(projectId);
                         break;
                case 2 : unassignEmployee(projectId);
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
    private void updateAllDetails(int projectId) {
        boolean isUpdated = false;
    
        ProjectDTO projectDTO = projectController.viewProjectById(projectId);
        projectDTO.setId(projectId);
        projectDTO.setName(getProjectName());
        projectDTO.setStartDate(getStartDate());
        projectDTO.setEndDate(getEndDate());
        projectDTO.setDomain(getProjectDomain());
        projectDTO.setStatus(getProjectStatus());        
        System.out.println(projectController.updateAllDetails(projectDTO)
                                      ? "Project updated successfully!!" 
                                      : "Project not updated");
    }

    /**
     * Assign a project for the given Employee
     *
     * @param employeeId
     */
    private void assignEmployee(int projectId) {
        ProjectDTO projectDTO = projectController.viewProjectById(projectId);
        Set<EmployeeDTO> employeeDTOSet = projectDTO.getEmployees();
        List<EmployeeDTO> employeeDTOList = new ArrayList<>(projectController
                                                             .viewAllEmployee());
        List<EmployeeDTO> availableEmployees = new ArrayList<>(employeeDTOSet);
        employeeDTOList.removeAll(availableEmployees);
        EmployeeDTO employeeDTO = new EmployeeDTO();

        if(assignDisplay(employeeDTOList)) {
            System.out.println("Give the EmployeeId to Assign for the Project");
            int employeeId = getEmployeeId();
            if(containsEmployee(employeeDTOList, employeeId)) {
                employeeDTO = projectController.viewEmployeeById(employeeId);
                employeeDTOSet.add(employeeDTO);
                projectDTO.setEmployees(employeeDTOSet);
                System.out.println(projectController
                                               .updateAllDetails(projectDTO) ?
                                                           "Assigned Employee" :
                                                       "Employee not assigned");
            } else {
                System.out.println("Sorry Employee ID not available");    
            }
        } else {
            System.out.println("Sorry No Employees available");
        }
    }

    /**
     * Displays the available Projects if available
     *
     * @param projectDTOList
     * @return boolean, true if projects are available
     */
    private boolean assignDisplay(List<EmployeeDTO> employeeDTOList) {
        boolean isAvailable = false;

        if((null != employeeDTOList) && !(employeeDTOList.isEmpty())) {
            isAvailable = true;
            System.out.println("Employee's Available");
            for(EmployeeDTO employee : employeeDTOList) {
                StringBuilder display = new StringBuilder();
                display.append("Id : ").append(employee.getId())
                      .append(" Name : ").append(employee.getName());
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
    private void unassignEmployee(int projectId) {
        ProjectDTO projectDTO = projectController.viewProjectById(projectId);
        Set<EmployeeDTO> employeeDTOSet = projectDTO.getEmployees();
        EmployeeDTO employeeDTO = new EmployeeDTO();
        if(unAssignDisplay(employeeDTOSet)) {
            System.out.println("Enter the Employee Id to be Unassigned");
            int employeeId = getEmployeeId();
            if(containsEmployee(employeeDTOSet, employeeId)) {
                employeeDTO = projectController.viewEmployeeById(employeeId);
                Set<EmployeeDTO> unAssignSet = new HashSet<>();
                for(EmployeeDTO unAssignEmployee : employeeDTOSet) {
                    if(unAssignEmployee.getId() == projectId) {
                        unAssignSet.add(unAssignEmployee);
                    }
                }
                employeeDTOSet.removeAll(unAssignSet);
                projectDTO.setEmployees(employeeDTOSet);
                System.out.println(projectController
                                                .updateAllDetails(projectDTO) ?
                                                         "Unassigned Project" :
                                                    "Project unassign failed");
            } else {
                System.out.println("Sorry Employee ID not available");
            }
        } else {
            System.out.println("Sorry! No Employees Available for the Employee");
        }         
    }

    /**
     * Displays the available Projects if available
     *
     * @param projectDTOSet
     * @return boolean, true if projects are available
     */
    private boolean unAssignDisplay(Set<EmployeeDTO> emloyeeDTOSet) {
        boolean isAvailable = false;

        if((null != emloyeeDTOSet) && !(emloyeeDTOSet.isEmpty())) {
            isAvailable = true;
            System.out.println("Employee's Available");
            for(EmployeeDTO employee : emloyeeDTOSet) {
                StringBuilder display = new StringBuilder();
                display.append("Id : ").append(employee.getId())
                      .append(" Name : ").append(employee.getName());
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
    private boolean containsEmployee(Set<EmployeeDTO> emloyeeDTOSet, 
                                                               int employeeId) {
        boolean isAvailable = false;
        if(null != emloyeeDTOSet) {
            for(EmployeeDTO employeeDTO : emloyeeDTOSet) {
                if(employeeDTO.getId() == employeeId) {
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
    private boolean containsEmployee(List<EmployeeDTO> employeeDTOList, 
                                                               int employeeId) {
        boolean isAvailable = false;
        if(null != employeeDTOList) {
            for(EmployeeDTO employeeDTO : employeeDTOList) {
                if(employeeDTO.getId() == employeeId) {
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
    private int getEmployeeId(){
        boolean isValidId = true;
        int employeeId = 0;
        while(isValidId) {
            try {
                String stringId = inputReader.nextLine() ;
                String pattern = "^[0-9]{1,4}";
                if(stringId.matches(pattern)) {
                    employeeId = Integer.parseInt(stringId);
                    isValidId = false;
                } else {
                    System.out.println("Enter a valid Employee Id");
                }
            } catch(NumberFormatException e) {
                System.out.println("Enter a valid Employee Id");
            }
        }
        return employeeId;       
    }
    
    /**
     * Updates the Name of an Employee
     *
     * @param employeeId
     */
    private void updateProjectName(int projectId) {
        ProjectDTO projectDTO = projectController.viewProjectById(projectId);
        projectDTO.setName(getProjectName());
        System.out.println(projectController.updateAllDetails(projectDTO) ?
                                                    "Project Name updated" :
                                                         "Name not updated");
    }

    /**
     * Updates the Salary of an Employee
     *
     * @param employeeId
     */
    private void updateProjectDomain(int projectId) {
        ProjectDTO projectDTO = projectController.viewProjectById(projectId);
        projectDTO.setDomain(getProjectDomain());
        System.out.println(projectController.updateAllDetails(projectDTO) ?
                                                  "Project Domain updated" :
                                                       "Domain not updated");        
    }

    /**
     * Updates the Email ID of an Employee
     *
     * @param employeeId
     */ 
    private void updateProjectStatus(int projectId) {
        ProjectDTO projectDTO = projectController.viewProjectById(projectId);
        projectDTO.setStatus(getProjectStatus());
        System.out.println(projectController.updateAllDetails(projectDTO) ?
                                                  "Project Status updated" :
                                                       "Status not updated");
    }
    
    /**
     * Updates the Date of Birth of an Employee
     *
     * @param employeeId
     */
    private void updateProjectStartDate(int projectId) {
        ProjectDTO projectDTO = projectController.viewProjectById(projectId);
        projectDTO.setStartDate(getStartDate());
        System.out.println(projectController.updateAllDetails(projectDTO) ?
                                                     "Project Start Date updated" :
                                                          "Start Date not updated");        
    }
    
    /**
     * Updates the Date of Birth of an Employee
     *
     * @param employeeId
     */
    private void updateProjectEndDate(int projectId) {
        ProjectDTO projectDTO = projectController.viewProjectById(projectId);
        projectDTO.setEndDate(getEndDate());
        System.out.println(projectController.updateAllDetails(projectDTO) ?
                                                     "Project Ends Date updated" :
                                                          "End Date not updated");        
    }
}
