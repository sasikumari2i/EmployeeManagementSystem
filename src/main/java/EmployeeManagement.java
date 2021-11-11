/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
import com.ideas2it.project.view.EmployeeView;

/**
 * This class is to manage the employee details of an organization. A new 
 * employee can be created. An existing user's details can be viewed, updated
 * and deleted.We can either delete all the employees or a single employee.
 * 
 * @version	1.0
 * @author	Sasikumar Raju
 */
public class EmployeeManagement {
    
    public static void main(String[] args) {        
       	EmployeeView employeeView  = new EmployeeView();	
        employeeView.showMainMenu();
    }
}



