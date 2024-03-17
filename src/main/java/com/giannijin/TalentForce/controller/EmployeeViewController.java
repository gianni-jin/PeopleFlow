package com.giannijin.TalentForce.controller;

import com.giannijin.TalentForce.model.Department;
import com.giannijin.TalentForce.model.Employee;
import com.giannijin.TalentForce.service.DepartmentService;
import com.giannijin.TalentForce.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class EmployeeViewController {
    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/employees")
    public String getAll(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        List<Employee> employees = keyword == null ? employeeService.getEmployees() : employeeService.getEmployeeByFirstNameAndLastName(keyword, keyword);
        model.addAttribute("employees", employees);
        model.addAttribute("employee", new Employee());
        model.addAttribute("filterByFirstNameAndLastName", new Employee());
        model.addAttribute("updateEmployee", new Employee());
        return "employee";
    }

    @PostMapping("/employees/search/lastname-location")
    public String getEmployeeByLastNameAndLocation(@RequestParam String lastName, @RequestParam String location, Model model) {
        List<Employee> employees = employeeService.getEmployeeByLastNameAndLocation(lastName, location);
        if (employees.isEmpty()) {
            model.addAttribute("message", "No employees found with given last name and location.");
        }
        model.addAttribute("employees", employees);
        model.addAttribute("filterByFirstNameAndLastName", new Employee());
        return "employee";
    }

    @PostMapping("/employees/id")
    public String getEmployeeById(@RequestParam("id") Long id, Model model) {
        try {
            Employee employee = employeeService.getSingleEmployee(id);
            model.addAttribute("employees", Arrays.asList(employee));
        } catch (RuntimeException e) {
            model.addAttribute("employees", Collections.emptyList());
            model.addAttribute("searchError", "No employee found with ID: " + id);
        }
        model.addAttribute("filterByFirstNameAndLastName", new Employee());
        return "employee";
    }

    @PostMapping("/employees/search/name")
    public String getEmployeeByFirstNameAndLastName(@RequestParam String firstName, @RequestParam String lastName, Model model) {
        List<Employee> employees = employeeService.getEmployeeByFirstNameAndLastName(firstName, lastName);
        if (employees.isEmpty()) {
            model.addAttribute("message", "No employees found with given name.");
        }
        model.addAttribute("employees", employees);
        model.addAttribute("filterByFirstNameAndLastName", new Employee());
        return "employee";
    }

    @GetMapping("/employees/{id}")
    public String getSingleEmployee(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getSingleEmployee(id);
        if (employee == null) {
            employee = new Employee();
        }
        model.addAttribute("employee", employee);
        model.addAttribute("filterByFirstNameAndLastName", new Employee());
        return "employee";
    }

    @GetMapping("/employees/new")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("isNew", true);
        model.addAttribute("departments", departmentService.getDepartments());
        return "employee_form";
    }

    @GetMapping("/employees/form")
    public String showForm(@RequestParam(required = false) Long id, Model model) {
        if (id != null) {
            Employee employee = employeeService.getSingleEmployee(id);
            model.addAttribute("employee", employee);
        } else {
            model.addAttribute("employee", new Employee());
        }
        model.addAttribute("departments", departmentService.getDepartments());
        return "employee_form";
    }
    @GetMapping("/employees/update/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Employee employee = employeeService.getSingleEmployee(id);
        if (employee == null) {
            return "redirect:/employees";
        }
        model.addAttribute("employee", employee);
        model.addAttribute("isNew", false);
        model.addAttribute("departments", departmentService.getDepartments());
        return "employee_form";
    }
    @PostMapping("/employees/save")
    public String saveOrUpdateEmployee(@ModelAttribute Employee employee, @RequestParam("department.id") Long departmentId, RedirectAttributes redirectAttributes) {
        Department department = departmentService.getSingleDepartment(departmentId);
        employee.setDepartment(department);
        if (employee.getId() == null) {
            employeeService.saveEmployee(employee);
        } else {
            employeeService.updateEmployee(employee);
        }
        redirectAttributes.addFlashAttribute("message", "Saved employee successfully!");
        return "redirect:/employees";
    }

    @GetMapping("/employees/delete/{id}")
    public String deleteEmployee(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        employeeService.deleteEmployee(id);
        redirectAttributes.addFlashAttribute("message", "The Employee with id=" + id + " has been deleted successfully!");
        return "redirect:/employees";
    }
    @GetMapping("/employees/add")
    public String showAddEmployeeForm(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        model.addAttribute("isNew", true);
        model.addAttribute("departments", departmentService.getDepartments());
        return "employee_form";
    }
    @PostMapping("/employees/update/{id}")
    public String updateEmployee(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Employee employee = employeeService.updateEmployee(employeeService.getSingleEmployee(id));
        redirectAttributes.addFlashAttribute("message", "The Employee id=" + id + " has been updated successfully");
        return "redirect:/employees";
    }

}
