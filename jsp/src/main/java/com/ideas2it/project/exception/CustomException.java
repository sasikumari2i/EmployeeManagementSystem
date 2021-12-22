/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.exception;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.lang.Exception;
import com.ideas2it.project.utils.ConstantUtils;	
/**
 * Employee Management System model class
 * 
 * @version	1.0
 * @author	Sasikumar Raju
 */
public class CustomException extends Exception { 
 
    public CustomException(String message) {  
        super(ConstantUtils.error.get(message));  
    }  
}
