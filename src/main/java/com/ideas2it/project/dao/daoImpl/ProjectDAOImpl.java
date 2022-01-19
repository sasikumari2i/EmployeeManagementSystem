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
import com.ideas2it.project.model.Project;
import com.ideas2it.project.utils.DatabaseConnection;

/**
 * Interacts with database to perform required operations
 *
 * @version 1.0
 * @author Sasikumar Raju
 */
public class ProjectDAOImpl implements ProjectDAO {

	/**
	 * Inserts new Project details to the database
	 *
	 * @return Project, Project which is inserted
	 * @param Project, Project to be inserted
	 */
	public Project createProject(Project project) throws CustomException {
		Session session = null;
		Transaction transaction = null;
		int projectID = 0;
		try {
			session = DatabaseConnection.getSession();
			transaction = session.beginTransaction();
			projectID = (Integer) session.save(project);
			transaction.commit();
		} catch (HibernateException e) {
			e.printStackTrace();
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
	 * Update the Project details of an Project
	 *
	 * @return Project, returns the updated Project
	 * @param Project, Project to be updated
	 */
	public Project updateProject(Project project) throws CustomException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = DatabaseConnection.getSession();
			transaction = session.beginTransaction();
			session.update(project);
			transaction.commit();
		} catch (HibernateException e) {
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
	 * Deletes the given Project from the database
	 *
	 * @return Project, returns the Project deleted
	 * @param projectId, projectId of the Project to be deleted
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
			throw new CustomException("ERROR_CODE_013");
		} finally {
			DatabaseConnection.closeSession();
		}
		return project;
	}

	/**
	 * Retrieves the record from the projectId given from the user
	 *
	 * @return Project, returns Project details
	 * @param projectId, Project Id used to retrieve Project
	 */
	public Project viewProjectById(int projectId) throws CustomException {
		Session session = null;
		Project project = new Project();
		try {
			session = DatabaseConnection.getSession();
			project = (Project) session.get(Project.class, projectId);
			if (null != project) {
				Hibernate.initialize(project.getEmployees());
			}
		} catch (HibernateException e) {
			throw new CustomException("ERROR_CODE_010");
		} finally {
			DatabaseConnection.closeSession();
		}
		return project;
	}

	/**
	 * Retrieves List of all the the records
	 *
	 * @return List<Project>, returns all the Project details
	 */
	public List<Project> viewProject() throws CustomException {
		Session session = null;
		List<Project> projects = null;
		StringBuilder query = new StringBuilder("SELECT DISTINCT p FROM ");
		query.append("Project p LEFT JOIN FETCH p.employees e ");
		try {
			session = DatabaseConnection.getSession();
			projects = session.createQuery(query.toString()).getResultList();
		} catch (HibernateException e) {
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
