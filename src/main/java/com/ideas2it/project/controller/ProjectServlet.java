package com.ideas2it.project.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServlet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ideas2it.project.exception.CustomException;
import com.ideas2it.project.logger.EmployeeManagementLogger;
import com.ideas2it.project.model.dto.EmployeeDTO;
import com.ideas2it.project.model.dto.ProjectDTO;
import com.ideas2it.project.service.ProjectService;

@Controller
public class ProjectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProjectService projectService;

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	@GetMapping("/createPro")
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
			EmployeeManagementLogger.logger.error(e);
		}
		m.addAttribute("isCreated", isCreated);
		return "projectView";
	}

	@GetMapping("/viewAllPro")
	private String viewAllProject(Model m) {
		try {
			List<ProjectDTO> projectList = projectService.viewProject();
			m.addAttribute("projectList", projectList);
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
		return "ViewProject";
	}

	@GetMapping("/viewProById")
	private String viewProjectById(Model m, @RequestParam int id) {
		ProjectDTO projectDTO = new ProjectDTO();
		String response = null;
		try {
			if (projectService.containsProject(id)) {
				projectDTO = projectService.viewProjectById(id);
				m.addAttribute("project", projectDTO);
				response = "ViewProjectById";
			} else {
				boolean notAvailable = true;
				m.addAttribute("notAvailable", notAvailable);
				response = "ViewSpecificProject";
			}
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
		return response;
	}

	@GetMapping("/viewProDetails")
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

	@PostMapping("/deleteProject")
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

	@GetMapping("/getProUpdated")
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

	@PostMapping("/assignEmployee")
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

	@PostMapping("/unAssignEmployee")
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
}
