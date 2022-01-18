/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.utils;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
	
/**
 * Employee Management System Constant values class
 * 
 * @version	1.0
 * @author	Sasikumar Raju
 */
public class ConstantUtils { 
    public static final Map<String, String> error = getError();
    public static final String ERROR_CODE_001 = "ERROR_CODE_001";
    public static final String ERROR_CODE_002 = "ERROR_CODE_002";
    public static final String ERROR_CODE_003 = "ERROR_CODE_003";
    public static final String ERROR_CODE_004 = "ERROR_CODE_004";
    public static final String ERROR_CODE_005 = "ERROR_CODE_005";
    public static final String ERROR_CODE_006 = "ERROR_CODE_006";
    public static final String ERROR_CODE_007 = "ERROR_CODE_007";
    public static final String ERROR_CODE_008 = "ERROR_CODE_008";
    public static final String ERROR_CODE_009 = "ERROR_CODE_009";
    public static final String ERROR_CODE_010 = "ERROR_CODE_010";
    public static final String ERROR_CODE_011 = "ERROR_CODE_011";
    public static final String ERROR_CODE_012 = "ERROR_CODE_012";
    public static final String ERROR_CODE_013 = "ERROR_CODE_013";
    public static final String ERROR_CODE_014 = "ERROR_CODE_014";

    /**
     * Gets list of error message and its coressponding code.
     *
     * @return  Map<String, String>  group containing pair of error code and its
     *                               corresponding message 
     */
    private static Map<String, String> getError() {
        Map<String, String> errorCodes = new HashMap<String, String>();

        errorCodes.put(ERROR_CODE_001, "Employee not created");
        errorCodes.put(ERROR_CODE_002, "Unable to display Employee");
        errorCodes.put(ERROR_CODE_003, "Unable to display Employees");
        errorCodes.put(ERROR_CODE_004, "Unable to update Employee");
        errorCodes.put(ERROR_CODE_005, "Unable to delete Employee");
        errorCodes.put(ERROR_CODE_006, "Unable to display Employees");
        errorCodes.put(ERROR_CODE_007, "Unable to check phonenumber uniqueness");
        errorCodes.put(ERROR_CODE_008, "Unable to check email uniqueness");
        errorCodes.put(ERROR_CODE_009, "Project not created");
        errorCodes.put(ERROR_CODE_010, "Unable to display Project");
        errorCodes.put(ERROR_CODE_011, "Unable to display Projects");
        errorCodes.put(ERROR_CODE_012, "Unable to update Project");
        errorCodes.put(ERROR_CODE_013, "Unable to delete Project");
        errorCodes.put(ERROR_CODE_014, "Unable to delete Projects");

        return Collections.unmodifiableMap(errorCodes);

    }

}


