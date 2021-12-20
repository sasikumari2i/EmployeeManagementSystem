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

import com.ideas2it.project.logger.EmployeeManagementLogger;
import com.ideas2it.project.model.dto.EmployeeDTO;
import com.ideas2it.project.service.EmployeeService;
import com.ideas2it.project.service.serviceImpl.EmployeeServiceImpl;

/**
 * Servlet Filter implementation class UpdateDuplicateFilter
 */
//@WebFilter("/EmployeeServlet")
public class UpdateDuplicateFilter implements Filter {

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (request.getParameter("servletId").equals("3")) {
			int employeeId = Integer.parseInt(request.getParameter("id"));
			EmployeeService employeeService = new EmployeeServiceImpl();
			try {
				EmployeeDTO employeeDTO = employeeService.viewEmployeeById(employeeId);
				long contact = Long.parseLong(request.getParameter("contact"));
				String email = (String) request.getParameter("email");
				LocalDate dob = LocalDate.parse(request.getParameter("dob"));

				boolean isUniqueEmail = employeeService.getEmployeeEmailValidated(email);
				boolean isUniqueContact = employeeService.getEmployeeContactValidated(contact);
				boolean isValidDob = employeeService.getValidatedDOB(dob);

				if ((isUniqueEmail) && (isUniqueContact) && (isValidDob)) {
					chain.doFilter(request, response);
				} else if (!isValidDob) {
					boolean notValidDob = true;
					RequestDispatcher dispatcher = request.getRequestDispatcher("createEmployee.jsp");
					request.setAttribute("notValidDob", notValidDob);
					request.setAttribute("employeeDTO", employeeDTO);
					dispatcher.forward(request, response);
				} else if (!isUniqueEmail && !(employeeDTO.getEmail().equals(email))) {
					boolean isDuplicateEmail = true;
					RequestDispatcher dispatcher = request.getRequestDispatcher("createEmployee.jsp");
					request.setAttribute("isDuplicateEmail", isDuplicateEmail);
					request.setAttribute("employeeDTO", employeeDTO);
					dispatcher.forward(request, response);
				} else if ((!isUniqueContact) && (employeeDTO.getContact() != contact)) {
					boolean isDuplicateContact = true;
					RequestDispatcher dispatcher = request.getRequestDispatcher("createEmployee.jsp");
					request.setAttribute("isDuplicateContact", isDuplicateContact);
					request.setAttribute("employeeDTO", employeeDTO);
					dispatcher.forward(request, response);
				} else {
					chain.doFilter(request, response);
				}
			} catch (Exception e) {
				EmployeeManagementLogger.logger.error(e);
			}
		} else {
			chain.doFilter(request, response);
		}
	}
}
