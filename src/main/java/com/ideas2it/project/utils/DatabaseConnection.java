package com.ideas2it.project.utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DatabaseConnection {

	private DatabaseConnection() {
	}

	private static SessionFactory sessionFactory = null;
	private static Session session = null;

	/**
	 * Open the Database Connection using Session Factory
	 *
	 * @return Session Factory
	 */
	public static SessionFactory getSessionFactory() {
		if (null == sessionFactory) {
			try {
				sessionFactory = new Configuration().configure().buildSessionFactory();
			} catch (HibernateException e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}

	/**
	 * Create a Session using the Session Factory available
	 *
	 * @return Session
	 */
	public static Session getSession() {
		try {
			session = getSessionFactory().openSession();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return session;
	}

	/**
	 * Close the Session
	 */
	public static void closeSession() {
		if (null != session) {
			try {
				session.close();
			} catch (HibernateException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Close the Session Factory
	 */
	public static void closeSessionFactory() {
		if (null != sessionFactory) {
			try {
				sessionFactory.close();
			} catch (HibernateException e) {
				e.printStackTrace();
			}
		}
	}
}
