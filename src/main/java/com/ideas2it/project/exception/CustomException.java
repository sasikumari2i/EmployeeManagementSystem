/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.exception;

import com.ideas2it.project.utils.ConstantUtils;	
/**
 * Employee Management System Custom Exception class
 * 
 * @version	1.0
 * @author	Sasikumar Raju
 */
public class CustomException extends Exception { 
	private static final long serialVersionUID = 1L;
 
    public CustomException(String message) {  
        super(ConstantUtils.error.get(message));  
    }  
}
