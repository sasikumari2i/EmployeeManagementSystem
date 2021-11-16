package com.ideas2it.project.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {

    private DatabaseConnection() {
    }
    
    private static Connection connection = null;   

    /**
     * Open the Database Connection
     *
     * @return, Database connection
     */
    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/employee_management_system";
        String username = "root";
        String password = "Test@123"; 
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  
            connection = DriverManager.getConnection
                             (url,username,password);
        } catch (ClassNotFoundException | SQLException exception) {
            System.out.println(exception.getMessage());
        }
        return connection;
    }

    /**
     * Closes the Database Connection
     *
     * @param Connection, database connection
     */    
    public static void close(Connection connection) {
        try {
            if (null != connection) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the Prepared statement
     *
     * @param PreparedStatement
     */
    public static void close(PreparedStatement statement) {
        try {
            if (null != statement) {
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Closes the ResultSet
     *
     * @param resultSet
     */    
    public static void close(ResultSet resultSet) {
        try {
            if (null != resultSet) {
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
