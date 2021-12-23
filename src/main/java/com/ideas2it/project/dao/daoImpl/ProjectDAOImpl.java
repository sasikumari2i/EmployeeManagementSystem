/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.dao.daoImpl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.project.dao.ProjectDAO;
import com.ideas2it.project.exception.CustomException;
import com.ideas2it.project.model.Employee;
import com.ideas2it.project.model.Project;
import com.ideas2it.project.utils.DatabaseConnection;

/**
 * Interacts with database to perform required operations
 *
 * @version	1.0
 * @author	Sasikumar Raju
 */
public class ProjectDAOImpl implements ProjectDAO {
       
    /**
     * Inserts new employee details to the database
     *
     * @return Employee, employee which is inserted
     * @param  Employee, Employee to be inserted
     */
    public Project createProject(Project project) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        int projectID = 0;

        try {
            session = DatabaseConnection.getSession();
            transaction = session.beginTransaction();
            projectID = (Integer)session.save(project);
            project.setId(project.getId());
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CustomException("ERROR_CODE_009");
        } finally {
            DatabaseConnection.closeSession(); 
        }
        return (0 != projectID) ? project = null : project;
    }

    /**
     * Update the employee details of an employee
     *
     * @return Employee, returns the updated employee 
     * @param  Employee, Employee to be updated
     */
    public Project updateProject(Project project) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        int projectID = 0;

        try {
            session = DatabaseConnection.getSession();
            transaction = session.beginTransaction();
            projectID = project.getId();
            session.update(project);
            transaction.commit();
        } catch (HibernateException e) {
            project = null;
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CustomException("ERROR_CODE_012"); 
        } finally {
            DatabaseConnection.closeSession(); 
        }
        return project;
    }   
    
    /**
     * Deletes the given employee from the database
     *
     * @return Employee, returns the employee deleted 
     * @param  employeeId, employeeId of the employee to be deleted
     */
    public Project deleteProjectById(int projectId) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        Project project = null;

        try {
            session = DatabaseConnection.getSession();
            transaction = session.beginTransaction();
            project = (Project) session.get(Project.class, projectId);
            session.delete(project);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            project = null;
            throw new CustomException("ERROR_CODE_013"); 
        } finally {
            DatabaseConnection.closeSession(); 
        }
        return project;
    }

    /**
     * Retrieves the record from the EmployeeId given from the user
     *
     * @return Employee, returns employee details
     * @param  employeeId, Employee Id used to retrieve employee
     */
    public Project viewProjectById(int projectId) throws CustomException {
        //Transaction transaction = null;
        Session session = null;
        Project project = new Project();
        StringBuilder query = new StringBuilder("select p from Project p left")
                      .append(" join fetch p.employees e")
                      .append(" where p.id ='" + projectId +"'");             
        try {
            session = DatabaseConnection.getSession();
            //transaction = session.beginTransaction();
            project = (Project) session.get(Project.class, projectId);
            //project = (Project)session.createQuery(query.toString())
              //                                      .uniqueResult();
            Hibernate.initialize(project.getEmployees());
            //transaction.commit();
        } catch (HibernateException e) {
            //if (transaction != null) {
              //  transaction.rollback();
            //}
            throw new CustomException("ERROR_CODE_010");
        } finally {
            DatabaseConnection.closeSession(); 
        }
        return project;
    }
    
    /**
     * Retrieves List of all the the records
     *
     * @return List<Employee>, returns all the employee details
     */
    public List<Project> viewProject() throws CustomException {
        Session session = null;
        //Transaction transaction = null;
        List<Project> projects = null;
        StringBuilder query = new StringBuilder("SELECT DISTINCT p FROM ");
                      query.append("Project p LEFT JOIN FETCH p.employees e ");

        try {
            session = DatabaseConnection.getSession();
            //transaction = session.beginTransaction();
            projects = session.createQuery(query.toString()).getResultList();
            //projects = session.createQuery(query.toString()).list();
            //transaction.commit();
        } catch (HibernateException e) {
            //if (transaction != null) {
              //  transaction.rollback();
            //}
            throw new CustomException("ERROR_CODE_011");
        } finally {
            DatabaseConnection.closeSession();
        }
        return projects;
    }
    
    /**
     * Deletes all the records
     *
     * @return boolean, returns whether any rows affected 
     */
    public boolean deleteAllProject() throws CustomException {
        Session session = null;
        Transaction transaction = null;
        Project project = null;
        int projectID = 0;
        String query = "delete FROM Project";

        try {
            session = DatabaseConnection.getSession();
            transaction = session.beginTransaction();
            projectID = session.createQuery(query).executeUpdate();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new CustomException("ERROR_CODE_014"); 
        } finally {
            DatabaseConnection.closeSession(); 
        }
        return (projectID != 0);
    }
}
