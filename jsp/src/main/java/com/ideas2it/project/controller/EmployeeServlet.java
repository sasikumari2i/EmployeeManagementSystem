package com.ideas2it.project.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ideas2it.project.exception.CustomException;
import com.ideas2it.project.logger.EmployeeManagementLogger;
import com.ideas2it.project.model.dto.AddressDTO;
import com.ideas2it.project.model.dto.EmployeeDTO;
import com.ideas2it.project.model.dto.ProjectDTO;
import com.ideas2it.project.service.EmployeeService;
import com.ideas2it.project.service.serviceImpl.EmployeeServiceImpl;

/**
 * Servlet implementation class EmployeeServlet
 */
//@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int action = Integer.parseInt(request.getParameter("servletId"));
		try {
			switch (action) {
			case 5:
				getAddress(request, response);
				break;
			case 7:
				getProjectAssigned(request, response);
				break;
			case 9:
				getProjectUnAssigned(request, response);
				break;
			case 10:
				getAddressUpdated(request, response);
				break;
			case 13:
				getEmployeeUpdated(request, response);
				break;
			case 14:
				viewAllEmployee(request, response);
				break;
			case 15:
				viewEmployeeById(request, response);
				break;
			}
		} catch (Exception e) {
			EmployeeManagementLogger.logger.error(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int action = Integer.parseInt(request.getParameter("servletId"));
		try {
			switch (action) {
			case 2:
				insertEmployee(request, response);
				break;
			case 3:
				updateEmployee(request, response);
				break;
			case 4:
				addAddress(request, response);
				break;
			case 6:
				assignProject(request, response);
				break;
			case 8:
				unAssignProject(request, response);
				break;
			case 11:
				deleteAddress(request, response);
				break;
			case 12:
				deleteEmployee(request, response);
				break;
			}
		} catch (Exception e) {
			EmployeeManagementLogger.logger.error(e);
		}
	}

	/*
	 * private void createEmployee(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { //EmployeeDTO employeeDTO =
	 * null; request.getRequestDispatcher("createEmployee.jsp").forward(request,
	 * response); }
	 */

	private void insertEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AddressDTO addressDTO = new AddressDTO();
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setName(request.getParameter("name"));
		employeeDTO.setDob(LocalDate.parse(request.getParameter("dob")));
		employeeDTO.setContact(Long.parseLong(request.getParameter("contact")));
		employeeDTO.setEmail(request.getParameter("email"));
		employeeDTO.setSalary(Float.parseFloat(request.getParameter("salary")));
		addressDTO.setCity(request.getParameter("city"));
		addressDTO.setDoorNo(request.getParameter("doorNo"));
		addressDTO.setLandMark(request.getParameter("landmark"));
		addressDTO.setStreet(request.getParameter("street"));
		addressDTO.setPincode(Long.parseLong(request.getParameter("pincode")));
		List<AddressDTO> addressList = new ArrayList<>();
		addressList.add(addressDTO);
		employeeDTO.setAddress(addressList);
		EmployeeService employeeService = new EmployeeServiceImpl();
		boolean isCreated = false;
		try {
			isCreated = employeeService.createEmployee(employeeDTO);
			if (isCreated) {
				request.setAttribute("isCreated", isCreated);
				request.getRequestDispatcher("employeeView.jsp").forward(request, response);
			} else {
				EmployeeDTO employee = null;
				boolean notCreated = true;
				request.setAttribute("employeeDTO", employee);
				request.setAttribute("notCreated", notCreated);
				request.getRequestDispatcher("createEmployee.jsp").forward(request, response);
			}
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
	}

	private void updateEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EmployeeService employeeService = new EmployeeServiceImpl();
		try {
			EmployeeDTO employeeDTO = employeeService.viewEmployeeById(Integer.parseInt(request.getParameter("id")));
			employeeDTO.setId(Integer.parseInt(request.getParameter("id")));
			employeeDTO.setName(request.getParameter("name"));
			employeeDTO.setDob(LocalDate.parse(request.getParameter("dob")));
			employeeDTO.setContact(Long.parseLong(request.getParameter("contact")));
			employeeDTO.setEmail(request.getParameter("email"));
			employeeDTO.setSalary(Float.parseFloat(request.getParameter("salary")));
			employeeDTO.setAddress(employeeDTO.getAddress());
			boolean isUpdated = false;
			isUpdated = employeeService.updateAllDetails(employeeDTO);
			if (isUpdated) {
				boolean isMerged = true;
				request.setAttribute("isMerged", isMerged);
				request.getRequestDispatcher("ViewEmployeeDetails.jsp").forward(request, response);
			} else {
				boolean notMerged = true;
				request.setAttribute("notMerged", notMerged);
				request.getRequestDispatcher("ViewEmployeeDetails.jsp").forward(request, response);
			}
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
	}

	private void addAddress(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EmployeeService employeeService = new EmployeeServiceImpl();
		AddressDTO addressDTO = new AddressDTO();
		int employeeId = Integer.parseInt(request.getParameter("id"));
		try {
			EmployeeDTO employeeDTO = employeeService.viewEmployeeById(employeeId);
			addressDTO.setCity(request.getParameter("city"));
			addressDTO.setDoorNo(request.getParameter("doorNo"));
			addressDTO.setLandMark(request.getParameter("landmark"));
			addressDTO.setStreet(request.getParameter("street"));
			addressDTO.setPincode(Long.parseLong(request.getParameter("pincode")));
			List<AddressDTO> addressList = employeeDTO.getAddress();
			addressList.add(addressDTO);
			int count = 1;
			for (AddressDTO address : addressList) {
				address.setSerialId(count);
				count++;
			}
			employeeDTO.setAddress(addressList);
			boolean isUpdated = false;
			isUpdated = employeeService.updateAllDetails(employeeDTO);
			if (isUpdated) {
				boolean isAdded = true;
				request.setAttribute("isAdded", isAdded);
				request.getRequestDispatcher("ViewEmployeeDetails.jsp").forward(request, response);
			} else {
				boolean notAdded = true;
				request.setAttribute("notAdded", notAdded);
				request.getRequestDispatcher("ViewEmployeeDetails.jsp").forward(request, response);
			}
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
	}

	private void getAddress(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EmployeeDTO employeeDTO = null;
		EmployeeService employeeService = new EmployeeServiceImpl();
		try {
			employeeDTO = employeeService.viewEmployeeById(Integer.parseInt(request.getParameter("id")));
			boolean isAdded = true;
			RequestDispatcher dispatcher = request.getRequestDispatcher("createEmployee.jsp");
			request.setAttribute("isAdded", isAdded);
			request.setAttribute("employeeId", employeeDTO.getId());
			dispatcher.forward(request, response);
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
	}

	private void assignProject(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			EmployeeService employeeService = new EmployeeServiceImpl();
			EmployeeDTO employeeDTO = employeeService.viewEmployeeById(Integer.parseInt(request.getParameter("id")));
			Set<ProjectDTO> projectSet = employeeDTO.getProjects();
			String[] selectedIds = request.getParameterValues("selected");
			for (String projectId : selectedIds) {
				ProjectDTO projectDTO = employeeService.viewProjectById(Integer.parseInt(projectId));
				projectSet.add(projectDTO);
			}
			employeeDTO.setProjects(projectSet);
			boolean isUpdated = false;
			isUpdated = employeeService.updateAllDetails(employeeDTO);
			if (isUpdated) {
				boolean isAssigned = true;
				request.setAttribute("isAssigned", isAssigned);
				request.getRequestDispatcher("ViewEmployeeDetails.jsp").forward(request, response);
			} else {
				boolean notAssigned = true;
				request.setAttribute("notAssigned", notAssigned);
				request.getRequestDispatcher("ViewEmployeeDetails.jsp").forward(request, response);
			}
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
	}

	private void getProjectAssigned(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EmployeeService employeeService = new EmployeeServiceImpl();
		EmployeeDTO employeeDTO = new EmployeeDTO();
		try {
			employeeDTO = employeeService.viewEmployeeById(Integer.parseInt(request.getParameter("id")));
			Set<ProjectDTO> projectDTOSet = employeeDTO.getProjects();
			List<ProjectDTO> projectDTOList = new ArrayList<>(employeeService.viewAllProject());
			List<ProjectDTO> availableProjects = new ArrayList<>(projectDTOSet);
			projectDTOList.removeAll(availableProjects);
			RequestDispatcher dispatcher = request.getRequestDispatcher("AssignProject.jsp");
			request.setAttribute("projectDTOList", projectDTOList);
			request.setAttribute("employeeId", employeeDTO.getId());
			dispatcher.forward(request, response);
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
	}

	private void deleteAddress(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			EmployeeService employeeService = new EmployeeServiceImpl();
			EmployeeDTO employeeDTO = employeeService.viewEmployeeById(Integer.parseInt(request.getParameter("id")));
			List<AddressDTO> addressList = employeeDTO.getAddress();
			List<AddressDTO> deleteAddresses = new ArrayList<>();
			String[] selectedIds = request.getParameterValues("selected");
			for (AddressDTO addressDTO : addressList) {
				for (int index = 0; index < selectedIds.length; index++) {
					if (addressDTO.getAddressId() == (Integer.parseInt(selectedIds[index]))) {
						deleteAddresses.add(addressDTO);
					}
				}
			}
			addressList.removeAll(deleteAddresses);
			employeeDTO.setAddress(addressList);
			boolean isUpdated = employeeService.deleteAddress(employeeDTO);
			if (isUpdated) {
				boolean isDeleted = true;
				request.setAttribute("isDeleted", isDeleted);
				request.getRequestDispatcher("ViewEmployeeDetails.jsp").forward(request, response);
			} else {
				boolean notDeleted = true;
				request.setAttribute("notDeleted", notDeleted);
				request.getRequestDispatcher("ViewEmployeeDetails.jsp").forward(request, response);
			}
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		boolean isDeleted = false;
		EmployeeService employeeService = new EmployeeServiceImpl();
		try {
			isDeleted = employeeService.deleteEmployeeById(Integer.parseInt(request.getParameter("id")));
			if (isDeleted) {
				boolean isRemoved = true;
				request.setAttribute("isDeleted", isRemoved);
				request.getRequestDispatcher("ViewEmployeeDetails.jsp").forward(request, response);
			} else {
				boolean notRemoved = true;
				request.setAttribute("notDeleted", notRemoved);
				request.getRequestDispatcher("ViewEmployeeDetails.jsp").forward(request, response);
			}
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
	}

	private void getEmployeeUpdated(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		EmployeeService employeeService = new EmployeeServiceImpl();
		try {
			employeeDTO = employeeService.viewEmployeeById(Integer.parseInt(request.getParameter("id")));
			RequestDispatcher dispatcher = request.getRequestDispatcher("createEmployee.jsp");
			request.setAttribute("employeeDTO", employeeDTO);
			dispatcher.forward(request, response);
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
	}

	private void viewAllEmployee(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EmployeeService employeeService = new EmployeeServiceImpl();
		try {
			List<EmployeeDTO> employeeList = employeeService.viewEmployee();
			request.setAttribute("employeeList", employeeList);
			request.getRequestDispatcher("ViewEmployee.jsp").forward(request, response);
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
	}

	private void viewEmployeeById(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		EmployeeService employeeService = new EmployeeServiceImpl();
		try {
			int employeeId = Integer.parseInt(request.getParameter("id"));
			if (employeeService.containsEmployee(employeeId)) {
				employeeDTO = employeeService.viewEmployeeById(employeeId);
				request.setAttribute("employee", employeeDTO);
				request.getRequestDispatcher("ViewEmployeeById.jsp").forward(request, response);
			} else {
				boolean notAvailable = true;
				request.setAttribute("notAvailable", notAvailable);
				request.getRequestDispatcher("ViewSpecificEmployee.jsp").forward(request, response);
			}
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
	}

	private void unAssignProject(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			EmployeeService employeeService = new EmployeeServiceImpl();
			EmployeeDTO employeeDTO = employeeService.viewEmployeeById(Integer.parseInt(request.getParameter("id")));
			Set<ProjectDTO> availableProjects = employeeDTO.getProjects();
			List<ProjectDTO> projectList = new ArrayList<>();
			String[] selectedIds = request.getParameterValues("selected");
			for (String projectId : selectedIds) {
				ProjectDTO projectDTO = employeeService.viewProjectById(Integer.parseInt(projectId));
				projectList.add(projectDTO);
			}
			availableProjects.removeAll(projectList);
			employeeDTO.setProjects(availableProjects);
			boolean isUpdated = false;
			isUpdated = employeeService.updateAllDetails(employeeDTO);
			if (isUpdated) {
				boolean unAssigned = true;
				request.setAttribute("unAssigned", unAssigned);
				request.getRequestDispatcher("ViewEmployeeDetails.jsp").forward(request, response);
			} else {
				boolean notUnAssigned = true;
				request.setAttribute("notUnAssigned", notUnAssigned);
				request.getRequestDispatcher("ViewEmployeeDetails.jsp").forward(request, response);
			}
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
	}

	private void getProjectUnAssigned(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		EmployeeService employeeService = new EmployeeServiceImpl();
		try {
			employeeDTO = employeeService.viewEmployeeById(Integer.parseInt(request.getParameter("id")));
			Set<ProjectDTO> projectDTOSet = employeeDTO.getProjects();
			RequestDispatcher dispatcher = request.getRequestDispatcher("UnAssignProject.jsp");
			request.setAttribute("projectDTOSet", projectDTOSet);
			request.setAttribute("employeeId", employeeDTO.getId());
			dispatcher.forward(request, response);
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
	}

	private void getAddressUpdated(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		EmployeeService employeeService = new EmployeeServiceImpl();
		try {
			employeeDTO = employeeService.viewEmployeeById(Integer.parseInt(request.getParameter("id")));
			List<AddressDTO> addressList = employeeDTO.getAddress();
			int count = 1;
			for (AddressDTO addressDTO : addressList) {
				addressDTO.setSerialId(count);
				count++;
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("UpdateAddress.jsp");
			request.setAttribute("employeeId", employeeDTO.getId());
			request.setAttribute("addressList", addressList);
			dispatcher.forward(request, response);
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
	}

}