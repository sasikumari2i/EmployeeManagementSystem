/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
import com.ideas2it.project.view.EmployeeView;
import com.ideas2it.project.view.ProjectView;

/**
 * This class is to manage the employee  and the project details of an 
 * organization. A new employee or project can be created. An existing user's 
 * details can be viewed, updated and deleted. An available project's details 
 * can be viewed, updated and deleted. A project can be assigned or unassigned
 * to an employee and an employee can be assigned or unassigned to a project. 
 * Address of an employee can be added or deleted.  
 *
 * @version	1.0
 * @author	Sasikumar Raju
 */
public class EmployeeManagement {
    
    public static void main(String[] args) { 
       	boolean isExit = true;

        EmployeeView employeeView  = new EmployeeView();
        ProjectView projectView = new ProjectView();
        do {
            System.out.println("-----Welcome!!!-----");
            System.out.println("1.Employee View 2.Project View 3.Exit");
            int inputChoice = employeeView.getInputChoice();
            switch(inputChoice) {
                case 1 : employeeView.showMainMenu();
                         break;
                case 2 : projectView.showMainMenu();
                         break;
                case 3 : isExit = false;
                         System.out.println("Thank you!!!");
                         break; 
                default : System.out.println("Choose from the given numbers");    
                          break;
            }
        } while(isExit);	
        
    }
}



