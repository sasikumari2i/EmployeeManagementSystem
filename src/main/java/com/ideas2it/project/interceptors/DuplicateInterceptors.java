package com.ideas2it.project.interceptors;

import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import com.ideas2it.project.model.dto.AddressDTO;
import com.ideas2it.project.model.dto.EmployeeDTO;
import com.ideas2it.project.service.EmployeeService;

public class DuplicateInterceptors implements HandlerInterceptor {

	EmployeeService employeeService;

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		long contact = Long.parseLong(request.getParameter("contact"));
		String email = (String) request.getParameter("email");
		LocalDate dob = LocalDate.parse(request.getParameter("dob"));

		boolean isUniqueEmail = employeeService.getEmployeeEmailValidated(email);
		boolean isUniqueContact = employeeService.getEmployeeContactValidated(contact);
		boolean isValidDob = employeeService.getValidatedDOB(dob);

		if (isUniqueEmail && isUniqueContact && isValidDob) {
			return true;
		} else {
			if (!isUniqueEmail) {
				boolean isDuplicateEmail = true;
				RequestDispatcher dispatcher = request.getRequestDispatcher("createEmployee.jsp");
				request.setAttribute("isDuplicateEmail", isDuplicateEmail);
				request.setAttribute("employee", new EmployeeDTO());
				request.setAttribute("address", new AddressDTO());            	
				dispatcher.forward(request, response);
				
			} else if (!isValidDob) {
				boolean notValidDob = true;
				RequestDispatcher dispatcher = request.getRequestDispatcher("createEmployee.jsp");
				request.setAttribute("notValidDob", notValidDob);
				request.setAttribute("employee", new EmployeeDTO());
				request.setAttribute("address", new AddressDTO());
				dispatcher.forward(request, response);
			} else if (!isUniqueContact) {
				boolean isDuplicateContact = true;
				RequestDispatcher dispatcher = request.getRequestDispatcher("createEmployee.jsp");
				request.setAttribute("isDuplicateContact", isDuplicateContact);
				request.setAttribute("employee", new EmployeeDTO());
				request.setAttribute("address", new AddressDTO());
				dispatcher.forward(request, response);
			}
			return false;
		}
	}

}
