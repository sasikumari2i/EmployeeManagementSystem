package com.ideas2it.project.utils;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {

    private DatabaseConnection() {
    }
    
    private static Connection connection = null;   
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  
            connection = DriverManager.getConnection
                             ("jdbc:mysql://localhost:3306/employee_management_system","root","Test@123");
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return connection;
    }
}
