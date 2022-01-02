package com.ideas2it.project.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServlet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ideas2it.project.exception.CustomException;
import com.ideas2it.project.logger.EmployeeManagementLogger;
import com.ideas2it.project.model.dto.EmployeeDTO;
import com.ideas2it.project.model.dto.ProjectDTO;
import com.ideas2it.project.service.ProjectService;

/**
 * Servlet implementation class ProjectServlet
 */
//@WebServlet("/ProjectServlet")
@Controller
public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProjectService projectService;

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	@RequestMapping("/createPro")
	public String showform(Model m) {
		m.addAttribute("project", new ProjectDTO());
		return "createProject";
	}

	@PostMapping("/savePro")
	public String save(@ModelAttribute("project") ProjectDTO projectDTO, BindingResult result, Model m) {
		boolean isCreated = false;
		try {
			isCreated = projectService.createProject(projectDTO);
		} catch (CustomException e) {
			e.printStackTrace();
			EmployeeManagementLogger.logger.error(e);
		}
		m.addAttribute("isCreated", isCreated);
		return "projectView";
	}

	@RequestMapping("/viewAllPro")
	private String viewAllProject(Model m) {
		try {
			List<ProjectDTO> projectList = projectService.viewProject();
			m.addAttribute("projectList", projectList);
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
		return "ViewProject";
	}

	@RequestMapping("/viewProById")
	private String viewProjectById(Model m, @RequestParam int id) {
		ProjectDTO projectDTO = new ProjectDTO();
		try {
			if (projectService.containsProject(id)) {
				projectDTO = projectService.viewProjectById(id);
				m.addAttribute("project", projectDTO);
				return "ViewProjectById";
			} else {
				boolean notAvailable = true;
				m.addAttribute("notAvailable", notAvailable);
				return "ViewSpecificProject";
			}
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
			return "error";
		}
	}

	@RequestMapping("/viewProDetails")
	private String getEmployeeAssigned(Model m, @RequestParam int id) {
		ProjectDTO projectDTO = new ProjectDTO();
		try {
			projectDTO = projectService.viewProjectById(id);
			Set<EmployeeDTO> employeeDTOSet = projectDTO.getEmployees();
			List<EmployeeDTO> employeeDTOList = new ArrayList<>(projectService.viewAllEmployee());
			List<EmployeeDTO> availableEmployees = new ArrayList<>(employeeDTOSet);
			employeeDTOList.removeAll(availableEmployees);
			m.addAttribute("employeeDTOList", employeeDTOList);
			m.addAttribute("employeeDTOSet", employeeDTOSet);
			m.addAttribute("project", projectDTO);
			m.addAttribute("projectId", projectDTO.getId());
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
		return "AssignEmployee";
	}

	@RequestMapping("/deleteProject")
	private String deleteProject(Model m, @RequestParam int id) {
		boolean isDeleted = false;
		boolean notDeleted = false;
		try {
			isDeleted = projectService.deleteProjectById(id);
			if (isDeleted) {
				m.addAttribute("isDeleted", isDeleted);
			} else {
				notDeleted = true;
				m.addAttribute("notDeleted", notDeleted);
			}
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
		return "ViewProjectDetails";
	}

	@RequestMapping("/getProUpdated")
	private String updateProject(Model m, @RequestParam int id) {
		ProjectDTO projectDTO = new ProjectDTO();
		try {
			projectDTO = projectService.viewProjectById(id);
			m.addAttribute("project", projectDTO);
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
		return "createProject";
	}

	@PostMapping("/updateProject")
	private String getProjectUpdated(@ModelAttribute("project") ProjectDTO project, @RequestParam int id,
			BindingResult result, Model m) {
		boolean isUpdated = false;
		boolean isMerged = false;
		boolean notMerged = false;

		try {
			// ProjectDTO projectDTO = projectService.viewProjectById(id);
			isUpdated = projectService.updateAllDetails(project);
			if (isUpdated) {
				isMerged = true;
				m.addAttribute("isMerged", isMerged);
			} else {
				notMerged = true;
				m.addAttribute("notMerged", notMerged);
			}
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
		return "ViewProjectDetails";
	}

	@RequestMapping("/assignEmployee")
	private String assignProject(@ModelAttribute("selected") String[] selected, Model m, @RequestParam int id,
			BindingResult result) {
		try {
			ProjectDTO projectDTO = projectService.viewProjectById(id);
			Set<EmployeeDTO> employeeSet = projectDTO.getEmployees();
			for (String employeeId : selected) {
				EmployeeDTO employeeDTO = projectService.viewEmployeeById(Integer.parseInt(employeeId));
				employeeSet.add(employeeDTO);
			}
			projectDTO.setEmployees(employeeSet);
			boolean isUpdated = false;
			isUpdated = projectService.updateAllDetails(projectDTO);
			if (isUpdated) {
				boolean isAssigned = true;
				m.addAttribute("isAssigned", isAssigned);
			} else {
				boolean notAssigned = true;
				m.addAttribute("notAssigned", notAssigned);
			}
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
		return "ViewProjectDetails";
	}

	@RequestMapping("/unAssignEmployee")
	private String unAssignProject(@ModelAttribute("selected") String[] selected, Model m, @RequestParam int id,
			BindingResult result) {
		try {

			ProjectDTO projectDTO = projectService.viewProjectById(id);
			List<EmployeeDTO> availableEmployees = new ArrayList<EmployeeDTO>(projectDTO.getEmployees());
			List<EmployeeDTO> employeeList = new ArrayList<EmployeeDTO>();

			for (String employeeId : selected) {
				EmployeeDTO employeeDTO = projectService.viewEmployeeById(Integer.parseInt(employeeId));
				employeeList.add(employeeDTO);
			}
			availableEmployees.removeAll(employeeList);
			Set<EmployeeDTO> employeeDTOSet = new HashSet<EmployeeDTO>(availableEmployees);
			projectDTO.setEmployees(employeeDTOSet);
			boolean isUpdated = projectService.updateAllDetails(projectDTO);
			if (isUpdated) {
				boolean unAssigned = true;
				m.addAttribute("unAssigned", unAssigned);
			} else {
				boolean notUnAssigned = true;
				m.addAttribute("notUnAssigned", notUnAssigned);
			}
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
		return "ViewProjectDetails";
	}

	/**
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	/*
	 * protected void doGet(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { int action =
	 * Integer.parseInt(request.getParameter("servletId")); try { switch (action) {
	 * case 2: getEmployeeAssigned(request, response); break; case 4:
	 * deleteProject(request, response); break; case 7:
	 * getEmployeeUnAssigned(request, response); break; case 9:
	 * updateProject(request, response); break; case 10: viewAllProject(request,
	 * response); break; case 11: viewProjectById(request, response); break;
	 * 
	 * } } catch (Exception e) { EmployeeManagementLogger.logger.error(e); } }
	 *
	 * /**
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 * response)
	 */
	/*
	 * protected void doPost(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { int action =
	 * Integer.parseInt(request.getParameter("servletId")); try { switch (action) {
	 * case 1: assignEmployee(request, response); break; case 3:
	 * createProject(request, response); break; // case 4: // deleteProject(request,
	 * response); // break; case 5: insertProject(request, response); break; case 6:
	 * unAssignEmployee(request, response); break; case 8:
	 * getProjectUpdated(request, response); break; } } catch (Exception e) {
	 * EmployeeManagementLogger.logger.error(e); } }
	 * 
	 * private void assignEmployee(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { try { ProjectService
	 * projectService = new ProjectServiceImpl(); ProjectDTO projectDTO =
	 * projectService.viewProjectById(Integer.parseInt(request.getParameter("id")));
	 * Set<EmployeeDTO> employeeSet = projectDTO.getEmployees(); String[]
	 * selectedIds = request.getParameterValues("selected"); // if(null !=
	 * selectedIds) { for (String employeeId : selectedIds) { EmployeeDTO
	 * employeeDTO = projectService.viewEmployeeById(Integer.parseInt(employeeId));
	 * employeeSet.add(employeeDTO); } projectDTO.setEmployees(employeeSet); boolean
	 * isUpdated = false; isUpdated = projectService.updateAllDetails(projectDTO);
	 * if (isUpdated) { boolean isAssigned = true;
	 * request.setAttribute("isAssigned", isAssigned);
	 * request.getRequestDispatcher("ViewProjectDetails.jsp").forward(request,
	 * response); } else { boolean notAssigned = true;
	 * request.setAttribute("notAssigned", notAssigned);
	 * request.getRequestDispatcher("ViewProjectDetails.jsp").forward(request,
	 * response); } /* } else { boolean emptySelect = true;
	 * request.setAttribute("emptySelect", emptySelect);
	 * request.setAttribute("employeeDTOSet", employeeDTOSet);
	 * request.setAttribute("employeeDTOList", employeeDTOList);
	 * request.setAttribute("project", project);
	 * request.getRequestDispatcher("AssignEmployee.jsp").forward(request,
	 * response); }
	 */
	/*
	 * } catch (CustomException e) { EmployeeManagementLogger.logger.error(e); } }
	 * 
	 * private void getEmployeeAssigned(HttpServletRequest request,
	 * HttpServletResponse response) throws ServletException, IOException {
	 * ProjectDTO projectDTO = new ProjectDTO(); ProjectService projectService = new
	 * ProjectServiceImpl(); try { projectDTO =
	 * projectService.viewProjectById(Integer.parseInt(request.getParameter("id")));
	 * Set<EmployeeDTO> employeeDTOSet = projectDTO.getEmployees();
	 * List<EmployeeDTO> employeeDTOList = new
	 * ArrayList<>(projectService.viewAllEmployee()); List<EmployeeDTO>
	 * availableEmployees = new ArrayList<>(employeeDTOSet);
	 * employeeDTOList.removeAll(availableEmployees); RequestDispatcher dispatcher =
	 * request.getRequestDispatcher("AssignEmployee.jsp");
	 * request.setAttribute("employeeDTOList", employeeDTOList);
	 * request.setAttribute("employeeDTOSet", employeeDTOSet);
	 * request.setAttribute("project", projectDTO);
	 * request.setAttribute("projectId", projectDTO.getId());
	 * dispatcher.forward(request, response); } catch (CustomException e) {
	 * EmployeeManagementLogger.logger.error(e); } }
	 * 
	 * private void createProject(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException {
	 * request.getRequestDispatcher("createProject.jsp").forward(request, response);
	 * }
	 * 
	 * private void deleteProject(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { boolean isDeleted = false;
	 * ProjectService projectService = new ProjectServiceImpl(); try { isDeleted =
	 * projectService.deleteProjectById(Integer.parseInt(request.getParameter("id"))
	 * ); if (isDeleted) { boolean isRemoved = true;
	 * request.setAttribute("isRemoved", isRemoved);
	 * request.getRequestDispatcher("ViewProjectDetails.jsp").forward(request,
	 * response); } else { boolean notRemoved = true;
	 * request.setAttribute("notRemoved", notRemoved);
	 * request.getRequestDispatcher("ViewProjectDetails.jsp").forward(request,
	 * response); } } catch (CustomException e) {
	 * EmployeeManagementLogger.logger.error(e); } }
	 * 
	 * private void insertProject(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { ProjectDTO projectDTO = new
	 * ProjectDTO(); projectDTO.setName(request.getParameter("name"));
	 * projectDTO.setStartDate(LocalDate.parse(request.getParameter("startDate")));
	 * projectDTO.setEndDate(LocalDate.parse(request.getParameter("endDate")));
	 * projectDTO.setDomain(request.getParameter("domain"));
	 * projectDTO.setStatus(request.getParameter("status")); ProjectService
	 * projectService = new ProjectServiceImpl(); boolean isCreated = false; try {
	 * isCreated = projectService.createProject(projectDTO); if (isCreated) {
	 * request.setAttribute("isCreated", isCreated);
	 * request.getRequestDispatcher("projectView.jsp").forward(request, response); }
	 * else { ProjectDTO project = null; boolean notCreated = true;
	 * request.setAttribute("projectDTO", project);
	 * request.setAttribute("notCreated", notCreated);
	 * request.getRequestDispatcher("createProject.jsp").forward(request, response);
	 * } } catch (CustomException e) { EmployeeManagementLogger.logger.error(e); } }
	 * 
	 * private void unAssignEmployee(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { try { ProjectService
	 * projectService = new ProjectServiceImpl(); ProjectDTO projectDTO =
	 * projectService.viewProjectById(Integer.parseInt(request.getParameter("id")));
	 * List<EmployeeDTO> availableEmployees = new
	 * ArrayList<EmployeeDTO>(projectDTO.getEmployees()); List<EmployeeDTO>
	 * employeeList = new ArrayList<EmployeeDTO>(); String[] selectedIds =
	 * request.getParameterValues("selected"); // if (null != selectedIds) { for
	 * (String employeeId : selectedIds) { EmployeeDTO employeeDTO =
	 * projectService.viewEmployeeById(Integer.parseInt(employeeId));
	 * employeeList.add(employeeDTO); } availableEmployees.removeAll(employeeList);
	 * Set<EmployeeDTO> employeeDTOSet = new
	 * HashSet<EmployeeDTO>(availableEmployees);
	 * projectDTO.setEmployees(employeeDTOSet); boolean isUpdated = false; isUpdated
	 * = projectService.updateAllDetails(projectDTO); if (isUpdated) { boolean
	 * unAssigned = true; request.setAttribute("unAssigned", unAssigned);
	 * request.getRequestDispatcher("ViewProjectDetails.jsp").forward(request,
	 * response); } else { boolean notUnAssigned = true;
	 * request.setAttribute("notUnAssigned", notUnAssigned);
	 * request.getRequestDispatcher("ViewProjectDetails.jsp").forward(request,
	 * response); } /* } else { boolean emptySelect = true;
	 * request.setAttribute("emptySelect", emptySelect);
	 * request.getRequestDispatcher("AssignEmployee.jsp").forward(request,
	 * response); }
	 */
	/*
	 * } catch (CustomException e) { EmployeeManagementLogger.logger.error(e); } }
	 * 
	 * private void getEmployeeUnAssigned(HttpServletRequest request,
	 * HttpServletResponse response) throws ServletException, IOException {
	 * ProjectDTO projectDTO = new ProjectDTO(); ProjectService projectService = new
	 * ProjectServiceImpl(); try { projectDTO =
	 * projectService.viewProjectById(Integer.parseInt(request.getParameter("id")));
	 * Set<EmployeeDTO> employeeDTOSet = projectDTO.getEmployees();
	 * RequestDispatcher dispatcher =
	 * request.getRequestDispatcher("UnAssignEmployee.jsp");
	 * request.setAttribute("employeeDTOSet", employeeDTOSet);
	 * request.setAttribute("projectId", projectDTO.getId());
	 * dispatcher.forward(request, response); } catch (CustomException e) {
	 * EmployeeManagementLogger.logger.error(e); } }
	 * 
	 * private void getProjectUpdated(HttpServletRequest request,
	 * HttpServletResponse response) throws ServletException, IOException {
	 * ProjectDTO projectDTO = new ProjectDTO();
	 * projectDTO.setId(Integer.parseInt(request.getParameter("id")));
	 * projectDTO.setName(request.getParameter("name"));
	 * projectDTO.setStartDate(LocalDate.parse(request.getParameter("startDate")));
	 * projectDTO.setEndDate(LocalDate.parse(request.getParameter("endDate")));
	 * projectDTO.setDomain(request.getParameter("domain"));
	 * projectDTO.setStatus(request.getParameter("status")); ProjectService
	 * projectService = new ProjectServiceImpl(); boolean isUpdated = false; try {
	 * isUpdated = projectService.updateAllDetails(projectDTO); if (isUpdated) {
	 * boolean isMerged = true; request.setAttribute("isMerged", isMerged);
	 * request.getRequestDispatcher("ViewProjectDetails.jsp").forward(request,
	 * response); } else { boolean notMerged = true;
	 * request.setAttribute("notMerged", notMerged);
	 * request.getRequestDispatcher("ViewProjectDetails.jsp").forward(request,
	 * response); } } catch (CustomException e) {
	 * EmployeeManagementLogger.logger.error(e); } }
	 * 
	 * private void updateProject(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { ProjectDTO projectDTO = new
	 * ProjectDTO(); ProjectService projectService = new ProjectServiceImpl(); try {
	 * projectDTO =
	 * projectService.viewProjectById(Integer.parseInt(request.getParameter("id")));
	 * RequestDispatcher dispatcher =
	 * request.getRequestDispatcher("createProject.jsp");
	 * request.setAttribute("projectDTO", projectDTO); dispatcher.forward(request,
	 * response); } catch (CustomException e) {
	 * EmployeeManagementLogger.logger.error(e); } }
	 * 
	 * private void viewAllProject(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { ProjectService
	 * projectService = new ProjectServiceImpl(); try { List<ProjectDTO> projectList
	 * = projectService.viewProject(); request.setAttribute("projectList",
	 * projectList);
	 * request.getRequestDispatcher("ViewProject.jsp").forward(request, response); }
	 * catch (CustomException e) { EmployeeManagementLogger.logger.error(e); } }
	 * 
	 * private void viewProjectById(HttpServletRequest request, HttpServletResponse
	 * response) throws ServletException, IOException { ProjectDTO projectDTO = new
	 * ProjectDTO(); ProjectService projectService = new ProjectServiceImpl(); try {
	 * int projectId = Integer.parseInt(request.getParameter("id")); if
	 * (projectService.containsProject(projectId)) { projectDTO =
	 * projectService.viewProjectById(Integer.parseInt(request.getParameter("id")));
	 * request.setAttribute("project", projectDTO);
	 * request.getRequestDispatcher("ViewProjectById.jsp").forward(request,
	 * response); } else { boolean notAvailable = true;
	 * request.setAttribute("notAvailable", notAvailable);
	 * request.getRequestDispatcher("ViewSpecificProject.jsp").forward(request,
	 * response); } } catch (CustomException e) {
	 * EmployeeManagementLogger.logger.error(e); } }
	 */
}
