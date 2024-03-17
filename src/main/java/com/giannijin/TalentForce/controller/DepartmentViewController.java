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

import java.util.List;

@Controller
public class DepartmentViewController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/departments")
    public String getAll(Model model, @RequestParam(value = "keyword", required = false) String keyword) {
        List<Department> departments = keyword == null ? departmentService.getDepartments() : departmentService.findByNameIgnoreCase(keyword);
        model.addAttribute("departments", departments);
        model.addAttribute("department", new Department());
        return "departments";
    }

    @GetMapping("/departments/new")
    public String addDepartment(Model model) {
        model.addAttribute("department", new Department());
        model.addAttribute("isNew", true);
        model.addAttribute("unassignedEmployees", employeeService.findEmployeesNotInDepartment());
        return "department_form";
    }

    @GetMapping("/departments/update/{id}")
    public String updateDepartment(@PathVariable("id") Long id, Model model) {
        Department department = departmentService.getSingleDepartment(id);
        if (department == null) {
            return "redirect:/departments";
        }
        model.addAttribute("department", department);
        model.addAttribute("isNew", false);
        model.addAttribute("unassignedEmployees", employeeService.findEmployeesNotInDepartment());
        return "department_form";
    }

    @PostMapping("/departments/save")
    public String saveOrUpdateDepartment(@ModelAttribute Department department, RedirectAttributes redirectAttributes) {
        if (department.getId() == null) {
            departmentService.saveDepartment(department);
        } else {
            departmentService.updateDepartment(department);
        }
        redirectAttributes.addFlashAttribute("message", "Saved department successfully!");
        return "redirect:/departments";
    }

    @GetMapping("/departments/delete/{id}")
    public String deleteDepartment(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        departmentService.deleteDepartment(id);
        redirectAttributes.addFlashAttribute("message", "The Department with id=" + id + " has been deleted successfully!");
        return "redirect:/departments";
    }

    @GetMapping("/departments/{departmentId}/addEmployee/{employeeId}")
    public String addEmployeeToDepartment(@PathVariable("departmentId") Long departmentId, @PathVariable("employeeId") Long employeeId, RedirectAttributes redirectAttributes) {
        Department department = departmentService.getSingleDepartment(departmentId);
        Employee employee = employeeService.getSingleEmployee(employeeId);
        if (department != null && employee != null) {
            departmentService.addEmployeeToDepartment(department, employee);
            redirectAttributes.addFlashAttribute("message", "Added employee to department successfully!");
        }
        return "redirect:/departments/update/" + departmentId;
    }


    @PostMapping("/departments/{departmentId}/deleteEmployee/{employeeId}")
    public String deleteEmployeeFromDepartment(@PathVariable("departmentId") Long departmentId, @PathVariable("employeeId") Long employeeId, RedirectAttributes redirectAttributes) {
        Department department = departmentService.getSingleDepartment(departmentId);
        Employee employee = employeeService.getSingleEmployee(employeeId);
        if (department != null && employee != null) {
            department.removeEmployee(employee);
            departmentService.updateDepartment(department);
            redirectAttributes.addFlashAttribute("message", "Removed employee from department successfully!");
        }
        return "redirect:/departments/update/" + departmentId;
    }


}
