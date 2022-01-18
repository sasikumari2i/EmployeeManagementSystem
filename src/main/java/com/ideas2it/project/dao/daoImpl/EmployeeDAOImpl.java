/*
 * Copyright (c) Ideas2It Technologies, Inc. All Rights Reserved.
 */
package com.ideas2it.project.dao.daoImpl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.ideas2it.project.dao.EmployeeDAO;
import com.ideas2it.project.exception.CustomException;
import com.ideas2it.project.model.Address;
import com.ideas2it.project.model.Employee;
import com.ideas2it.project.utils.DatabaseConnection;

/**
 * Interacts with database to perform required operations
 *
 * @version 1.0
 * @author Sasikumar Raju
 */
public class EmployeeDAOImpl implements EmployeeDAO {

	/**
	 * Inserts new employee details to the database
	 *
	 * @return Employee, employee which is inserted
	 * @param Employee, Employee to be inserted
	 */
	public Employee createEmployee(Employee employee) throws CustomException {
		Session session = null;
		Transaction transaction = null;
		int employeeID = 0;
		try {
			session = DatabaseConnection.getSession();
			transaction = session.beginTransaction();
			List<Address> address = employee.getAddress();
			employee.setAddress(address);
			employeeID = (Integer) session.save(employee);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new CustomException("ERROR_CODE_001");
		} finally {
			DatabaseConnection.closeSession();
		}
		return (0 != employeeID) ? employee = null : employee;
	}

	/**
	 * Update the employee details of an employee
	 *
	 * @return Employee, returns the updated employee
	 * @param Employee, Employee to be updated
	 */
	public Employee updateEmployee(Employee employee) throws CustomException {
		Session session = null;
		Transaction transaction = null;
		try {
			session = DatabaseConnection.getSession();
			transaction = session.beginTransaction();
			employee = (Employee) session.merge(employee);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new CustomException("ERROR_CODE_004");
		} finally {
			DatabaseConnection.closeSession();
		}
		return employee;
	}

	/**
	 * Deletes the given employee from the database
	 *
	 * @return Employee, returns the employee deleted
	 * @param employeeId, employeeId of the employee to be deleted
	 */
	public Employee deleteEmployeeById(int employeeId) throws CustomException {
		Session session = null;
		Transaction transaction = null;
		Employee employee = null;
		try {
			session = DatabaseConnection.getSession();
			transaction = session.beginTransaction();
			employee = (Employee) session.get(Employee.class, employeeId);
			session.delete(employee);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new CustomException("ERROR_CODE_005");
		} finally {
			DatabaseConnection.closeSession();
		}
		return employee;
	}

	/**
	 * Retrieves the record of the employeeContact given from the user
	 *
	 * @return Employee, returns empty employee or null
	 * @param employeeContact, contact number used to retrieve employee
	 */
	public Employee containsEmployeeContact(String employeeContact) throws CustomException {
		Session session = null;
		Employee employee = null;
		StringBuilder query = new StringBuilder("select e from Employee e where e.contact = '" + employeeContact + "'");
		try {
			session = DatabaseConnection.getSession();
			employee = (Employee) session.createQuery(query.toString()).uniqueResult();
		} catch (HibernateException e) {
			throw new CustomException("ERROR_CODE_007");
		} finally {
			DatabaseConnection.closeSession();
		}
		return employee;
	}

	/**
	 * Retrieves the record of the EmployeeEmail given from the user
	 *
	 * @return Employee, returns empty employee or null
	 * @param EmployeeEmail, Employee email used to retrieve employee
	 */
	public Employee containsEmployeeEmail(String employeeEmail) throws CustomException {
		Session session = null;
		Employee employee = null;
		StringBuilder query = new StringBuilder("select e from Employee e where e.email = '" + employeeEmail + "'");
		try {
			session = DatabaseConnection.getSession();
			employee = (Employee) session.createQuery(query.toString()).uniqueResult();
		} catch (HibernateException e) {
			throw new CustomException("ERROR_CODE_008");
		} finally {
			DatabaseConnection.closeSession();
		}
		return employee;
	}

	/**
	 * Retrieves the record from the EmployeeId given from the user
	 *
	 * @return Employee, returns employee details
	 * @param employeeId, Employee Id used to retrieve employee
	 */
	public Employee viewEmployeeById(int employeeId) throws CustomException {
		Session session = null;
		Employee employee = new Employee();
		try {
			session = DatabaseConnection.getSession();
			employee = (Employee) session.get(Employee.class, employeeId);
			if (null != employee) {
				Hibernate.initialize(employee.getAddress());
				Hibernate.initialize(employee.getProjects());
			}
		} catch (HibernateException e) {
			throw new CustomException("ERROR_CODE_002");
		} finally {
			DatabaseConnection.closeSession();
		}
		return employee;
	}

	/**
	 * Retrieves List of all the the records
	 *
	 * @return List<Employee>, returns all the employee details
	 */
	public List<Employee> viewEmployee() throws CustomException {
		Session session = null;
		List<Employee> employees = null;
		StringBuilder query = new StringBuilder("Select DISTINCT e from ");
		query.append("Employee e left JOIN Fetch e.address a ");
		query.append("LEFT JOIN Fetch e.projects p");
		try {
			session = DatabaseConnection.getSession();
			employees = session.createQuery(query.toString()).getResultList();
		} catch (HibernateException e) {
			throw new CustomException("ERROR_CODE_003");
		} finally {
			DatabaseConnection.closeSession();
		}
		return employees;
	}

	/**
	 * Deletes all the records
	 *
	 * @return boolean, returns whether any rows affected
	 */
	public boolean deleteAllEmployee() throws CustomException {
		Session session = null;
		Transaction transaction = null;
		int employeeID = 0;
		String query = "delete FROM Employee";

		try {
			session = DatabaseConnection.getSession();
			transaction = session.beginTransaction();
			employeeID = session.createQuery(query).executeUpdate();
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new CustomException("ERROR_CODE_006");
		} finally {
			DatabaseConnection.closeSession();
		}
		return (employeeID != 0);
	}
}
