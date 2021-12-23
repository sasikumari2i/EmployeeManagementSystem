package com.ideas2it.project.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.ideas2it.project.exception.CustomException;
import com.ideas2it.project.logger.EmployeeManagementLogger;
import com.ideas2it.project.service.EmployeeService;
import com.ideas2it.project.service.serviceImpl.EmployeeServiceImpl;

/**
 * Servlet Filter implementation class DupilcateFilter
 */
//@WebFilter("/EmployeeServlet")
public class DuplicateFilter implements Filter {
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request.getParameter("servletId").equals("2")) {
			long contact = Long.parseLong(request.getParameter("contact"));
			String email = (String) request.getParameter("email");
			LocalDate dob = LocalDate.parse(request.getParameter("dob"));

			EmployeeService employeeService = new EmployeeServiceImpl();
			try {
				boolean isUniqueEmail = employeeService.getEmployeeEmailValidated(email);
				boolean isUniqueContact = employeeService.getEmployeeContactValidated(contact);
				boolean isValidDob = employeeService.getValidatedDOB(dob);
				if (isUniqueEmail && isUniqueContact && isValidDob) {
					chain.doFilter(request, response);
				} else if (!isUniqueEmail) {
					boolean isDuplicateEmail = true;
					RequestDispatcher dispatcher = request.getRequestDispatcher("createEmployee.jsp");
					request.setAttribute("isDuplicateEmail", isDuplicateEmail);
					dispatcher.forward(request, response);
				} else if (!isValidDob) {
					boolean notValidDob = true;
					RequestDispatcher dispatcher = request.getRequestDispatcher("createEmployee.jsp");
					request.setAttribute("notValidDob", notValidDob);
					dispatcher.forward(request, response);
				} else if (!isUniqueContact) {
					boolean isDuplicateContact = true;
					RequestDispatcher dispatcher = request.getRequestDispatcher("createEmployee.jsp");
					request.setAttribute("isDuplicateContact", isDuplicateContact);
					dispatcher.forward(request, response);
				}
			} catch (Exception e) {
				EmployeeManagementLogger.logger.error(e);
			}
		} else {
			chain.doFilter(request, response);
		}
	}

}
