/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.dao;

import java.util.List;

import com.ideas2it.project.exception.CustomException;
import com.ideas2it.project.model.Project;

/**
 * Interacts with database to perform required operations
 *
 * @version	1.0
 * @author	Sasikumar Raju
 */
public interface ProjectDAO {
       
    /**
     * Inserts new project details to the database
     *
     * @return Project, project which is inserted
     * @param  Project, project to be inserted
     */
    public Project createProject(Project project) throws CustomException;

    /**
     * Update the project details of an project
     *
     * @return project, returns the updated project 
     * @param  project, project to be updated
     */
    public Project updateProject(Project project) throws CustomException;   
    
    /**
     * Deletes the given project from the database
     *
     * @return project, returns the employee deleted 
     * @param  projectId, projectId of the project to be deleted
     */
    public Project deleteProjectById(int projectId) throws CustomException;

    /**
     * Retrieves the record from the projectId given from the project
     *
     * @return project, returns project details
     * @param  projectId, project Id used to retrieve employee
     */
    public Project viewProjectById(int projectId) throws CustomException;
    
    /**
     * Retrieves List of all the the records
     *
     * @return List<Project>, returns all the Project details
     */
    public List<Project> viewProject() throws CustomException;
    
    /**
     * Deletes all the records
     *
     * @return boolean, returns whether any rows affected 
     */
    public boolean deleteAllProject() throws CustomException;
}
