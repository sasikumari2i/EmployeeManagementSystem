/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;	
	
/**
 * Employee Management System Logger class
 * 
 * @version	1.0
 * @author	Sasikumar Raju
 */
public class EmployeeManagementLogger {

    public static final Logger logger = LogManager
            .getLogger(EmployeeManagementLogger.class.getName());
            
    public void error(String message) {
        logger.error(message);   
    }
   
    public void debug(String message) {
        logger.debug(message);   
    }

    public void fatal(String message) {
        logger.fatal(message);   
    }
}
