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
import com.ideas2it.project.model.dto.AddressDTO;
import com.ideas2it.project.model.dto.EmployeeDTO;
import com.ideas2it.project.model.dto.ProjectDTO;
import com.ideas2it.project.service.EmployeeService;

@Controller
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	EmployeeService employeeService;

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@PostMapping("/saveEmp")
	public String save(@ModelAttribute("employee") EmployeeDTO employeeDTO,
			@ModelAttribute("address") AddressDTO addressDTO, BindingResult result, Model model) {
		List<AddressDTO> addressList = new ArrayList<AddressDTO>();
		addressList.add(addressDTO);
		employeeDTO.setAddress(addressList);
		boolean isCreated = false;
		String response = null;
		String duplicateString = null;
		try {
			duplicateString = employeeService.employeeUniqueCreate(employeeDTO);
			if (null != duplicateString) {
				boolean duplicateAvailable = true;
				boolean isDuplicate = true;
				model.addAttribute("isDuplicate", isDuplicate);
				model.addAttribute(duplicateString, duplicateAvailable);
				model.addAttribute("employee", employeeDTO);
				model.addAttribute("address", addressDTO);
				response = "createEmployee";
				/*
				 * if (isDuplicateEmail || isDuplicateContact || notValidDob) { boolean
				 * isDuplicate = true; model.addAttribute("isDuplicate", isDuplicate);
				 * model.addAttribute("isDuplicateEmail", isDuplicateEmail);
				 * model.addAttribute("isDuplicateContact", isDuplicateContact);
				 * model.addAttribute("notValidDob", notValidDob);
				 * model.addAttribute("employee", employeeDTO); model.addAttribute("address",
				 * addressDTO); response = "createEmployee";
				 */
			} else {
				isCreated = employeeService.createEmployee(employeeDTO);
				model.addAttribute("isCreated", isCreated);
				response = "employeeView";
			}
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
		return response;
	}

	@PostMapping("/updateEmployee")
	private String updateEmployee(@ModelAttribute("employee") EmployeeDTO employee, @RequestParam int id,
			BindingResult result, Model model) {
		boolean isUpdated = false;
		boolean isMerged = false;
		boolean notMerged = false;
		String response = null;

		try {
			// boolean isDuplicateEmail =
			// employeeService.getEmployeeEmailValidated(employee.getEmail());
			// boolean isDuplicateContact =
			// employeeService.getEmployeeContactValidated(employee.getContact());
			// boolean notValidDob = employeeService.getValidatedDOB(employee.getDob());
			EmployeeDTO employeeDTO = employeeService.viewEmployeeById(id);
			employee.setAddress(employeeDTO.getAddress());
			String duplicateString = employeeService.employeeUniqueUpdate(employee, id, employeeDTO);
			if (null != duplicateString) {
				boolean isDuplicate = true;
				model.addAttribute(duplicateString, isDuplicate);
				model.addAttribute("employee", employee);
				response = "createEmployee";
				/*
				 * if (notValidDob) { model.addAttribute("notValidDob", notValidDob);
				 * model.addAttribute("employee", employee); response = "createEmployee"; } else
				 * if (isDuplicateEmail &&
				 * !(employeeDTO.getEmail().equals(employee.getEmail()))) {
				 * model.addAttribute("isDuplicateEmail", isDuplicateEmail);
				 * model.addAttribute("employee", employee); response = "createEmployee"; } else
				 * if (isDuplicateContact && !(employeeDTO.getContact() ==
				 * employee.getContact())) { model.addAttribute("isDuplicateContact",
				 * isDuplicateContact); model.addAttribute("employee", employee); response =
				 * "createEmployee";
				 */
			} else {
				isUpdated = employeeService.updateAllDetails(employee);
				if (isUpdated) {
					isMerged = true;
					model.addAttribute("isMerged", isMerged);
				} else {
					notMerged = true;
					model.addAttribute("notMerged", notMerged);
				}
				response = "ViewEmployeeDetails";
			}

		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
		return response;
	}

	@GetMapping("/createEmp")
	public String showform(Model m) {
		m.addAttribute("employee", new EmployeeDTO());
		m.addAttribute("address", new AddressDTO());
		return "createEmployee";
	}

	@GetMapping("/viewAllEmp")
	private String viewAllEmployee(Model m) {
		try {
			List<EmployeeDTO> employeeList = employeeService.viewEmployee();
			m.addAttribute("employeeList", employeeList);
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
		return "ViewEmployee";
	}

	@GetMapping("/viewEmpById")
	private String viewEmployeeById(Model m, @RequestParam int id) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		boolean notAvailable = false;
		String response = null;
		try {
			if (employeeService.containsEmployee(id)) {
				employeeDTO = employeeService.viewEmployeeById(id);
				m.addAttribute("employee", employeeDTO);
				response = "ViewEmployeeById";
			} else {
				notAvailable = true;
				m.addAttribute("notAvailable", notAvailable);
				response = "ViewSpecificEmployee";
			}
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
		return response;
	}

	@GetMapping("/viewEmpDetails")
	private String viewEmployeeDetails(Model m, @RequestParam int id) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		try {
			employeeDTO = employeeService.viewEmployeeById(id);
			List<AddressDTO> addressList = employeeDTO.getAddress();
			Set<ProjectDTO> projectDTOSet = employeeDTO.getProjects();
			List<ProjectDTO> projectDTOList = new ArrayList<>(employeeService.viewAllProject());
			List<ProjectDTO> availableProjects = new ArrayList<>(projectDTOSet);
			projectDTOList.removeAll(availableProjects);
			m.addAttribute("employeeId", employeeDTO.getId());
			m.addAttribute("addressList", addressList);
			m.addAttribute("projectDTOSet", projectDTOSet);
			m.addAttribute("projectDTOList", projectDTOList);
			m.addAttribute("employee", employeeDTO);
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
		return "UpdateAddress";
	}

	@PostMapping("/deleteEmployee")
	private String deleteEmployee(Model m, @RequestParam int id) {
		boolean isDeleted = false;
		boolean notDeleted = false;
		try {
			isDeleted = employeeService.deleteEmployeeById(id);
			if (isDeleted) {
				m.addAttribute("isDeleted", isDeleted);
			} else {
				notDeleted = true;
				m.addAttribute("notDeleted", notDeleted);
			}
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
		return "ViewEmployeeDetails";
	}

	@GetMapping("/getEmpUpdated")
	private String getEmployeeUpdated(Model m, @RequestParam int id) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		try {
			employeeDTO = employeeService.viewEmployeeById(id);
			m.addAttribute("employee", employeeDTO);
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
		return "createEmployee";
	}

	@GetMapping("/getAddress")
	private String getAddress(Model m, @RequestParam int id) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		AddressDTO address = new AddressDTO();
		try {
			employeeDTO = employeeService.viewEmployeeById(id);
			boolean isAdded = true;
			m.addAttribute("isAdded", isAdded);
			m.addAttribute("employeeId", employeeDTO.getId());
			m.addAttribute("address", address);
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
		return "createEmployee";
	}

	@PostMapping("/addAddress")
	private String addAddress(@ModelAttribute("address") AddressDTO address, Model m, @RequestParam int id,
			BindingResult result) {
		EmployeeDTO employeeDTO = new EmployeeDTO();
		try {
			employeeDTO = employeeService.viewEmployeeById(id);
			List<AddressDTO> addressList = employeeService.addAddress(employeeDTO, address);
			employeeDTO.setAddress(addressList);
			boolean isUpdated = false;
			isUpdated = employeeService.updateAllDetails(employeeDTO);
			if (isUpdated) {
				boolean isAdded = true;
				m.addAttribute("isAdded", isAdded);
			} else {
				boolean notAdded = true;
				m.addAttribute("notAdded", notAdded);
			}
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
		return "ViewEmployeeDetails";
	}

	@PostMapping("/deleteAddress")
	private String deleteAddress(@ModelAttribute("selected") String[] selected,
			@ModelAttribute("address") AddressDTO address, Model m, @RequestParam int id, BindingResult result) {
		try {
			EmployeeDTO employeeDTO = employeeService.viewEmployeeById(id);
			List<AddressDTO> addressList = employeeDTO.getAddress();
			List<AddressDTO> deleteAddresses = new ArrayList<>();
			for (AddressDTO addressDTO : addressList) {
				for (int index = 0; index < selected.length; index++) {
					if (addressDTO.getAddressId() == (Integer.parseInt(selected[index]))) {
						deleteAddresses.add(addressDTO);
					}
				}
			}
			addressList.removeAll(deleteAddresses);
			employeeDTO.setAddress(addressList);
			boolean isUpdated = employeeService.deleteAddress(employeeDTO);
			if (isUpdated) {
				boolean isDeleted = true;
				m.addAttribute("isDeleted", isDeleted);
			} else {
				boolean notDeleted = true;
				m.addAttribute("notDeleted", notDeleted);
			}
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
		return "ViewEmployeeDetails";
	}

	@PostMapping("/unAssignProject")
	private String unAssignProject(@ModelAttribute("selected") String[] selected, Model m, @RequestParam int id,
			BindingResult result) {
		try {
			EmployeeDTO employeeDTO = employeeService.viewEmployeeById(id);
			Set<ProjectDTO> projectDTOSet = employeeDTO.getProjects();
			List<ProjectDTO> projectDTOList = new ArrayList<>(projectDTOSet);
			List<ProjectDTO> projectList = new ArrayList<>();

			for (String projectId : selected) {
				ProjectDTO projectDTO = employeeService.viewProjectById(Integer.parseInt(projectId));
				projectList.add(projectDTO);
			}
			projectDTOList.removeAll(projectList);
			projectDTOSet = new HashSet<>(projectDTOList);
			employeeDTO.setProjects(projectDTOSet);
			boolean isUpdated = employeeService.updateAllDetails(employeeDTO);
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
		return "ViewEmployeeDetails";
	}

	@PostMapping("/assignProject")
	private String assignProject(@RequestParam int id, @ModelAttribute("selected") String[] selected, Model m,
			BindingResult result) {
		String response = null;
		try {
			if (selected != null) {
				EmployeeDTO employeeDTO = employeeService.viewEmployeeById(id);
				Set<ProjectDTO> projectSet = employeeDTO.getProjects();
				for (String projectId : selected) {
					ProjectDTO projectDTO = employeeService.viewProjectById(Integer.parseInt(projectId));
					projectSet.add(projectDTO);
				}
				employeeDTO.setProjects(projectSet);
				boolean isUpdated = false;
				isUpdated = employeeService.updateAllDetails(employeeDTO);
				if (isUpdated) {
					boolean isAssigned = true;
					m.addAttribute("isAssigned", isAssigned);
				} else {
					boolean notAssigned = true;
					m.addAttribute("notAssigned", notAssigned);
				}
				response = "ViewEmployeeDetails";
			} else {
				boolean notAssigned = true;
				m.addAttribute("notAssigned", notAssigned);
				response = "ViewEmployeeDetails";
			}
		} catch (CustomException e) {
			EmployeeManagementLogger.logger.error(e);
		}
		return response;
	}
}