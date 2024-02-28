package com.giannijin.PeopleFlow.controller;
import com.giannijin.PeopleFlow.model.Employee;
import com.giannijin.PeopleFlow.dto.DepartmentDTO;
import com.giannijin.PeopleFlow.model.Department;
import com.giannijin.PeopleFlow.service.DepartmentService;
import com.giannijin.PeopleFlow.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;


    private DepartmentDTO mapToDTO(Department department) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setEmployeeIds(department.getEmployees().stream().map(Employee::getId).collect(Collectors.toList()));
        return dto;
    }

    @GetMapping("/departments")
    public List<DepartmentDTO> getDepartments(){
        List<Department> departments = departmentService.getDepartments();
        return departments.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/departments/{id}")
    public DepartmentDTO getDepartment (@PathVariable Long id) {
        Department department = departmentService.getSingleDepartment(id);
        return mapToDTO(department);
    }

    /*
    @GetMapping("/departments")
    public List<Department> getDepartments(){
    return departmentService.getDepartments();
    }

    @GetMapping("/departments/{id}")
    public Department getDepartment (@PathVariable Long id) {
    return departmentService.getSingleDepartment(id);
    }
    */

    @DeleteMapping("/departments")
    public void deleteDepartment (@RequestParam Long id){
        departmentService.deleteDepartment(id);
    }
    @PostMapping("/departments")
    public ResponseEntity<Department> saveDepartment (@RequestBody DepartmentDTO departmentDTO){
        Department department = new Department();
        department.setName(departmentDTO.getName());

        List<Employee> employees = departmentDTO.getEmployeeIds().stream()
                .map(employeeService::getSingleEmployee)
                .collect(Collectors.toList());

        employees.forEach(department::addEmployee);

        department = departmentService.saveDepartment(department);

        return new ResponseEntity<Department>(department, HttpStatus.CREATED);
    }

        /*
    @PostMapping("/departments")
    public ResponseEntity<Department> saveDepartment (@RequestBody Department department){
        department = departmentService.saveDepartment(department);
        return new ResponseEntity<Department>(department, HttpStatus.CREATED);
    }
     */

    @PutMapping("/departments/{id}")
    public DepartmentDTO updateDepartment (@PathVariable Long id, @RequestBody DepartmentDTO departmentDTO){
        Department existingDepartment = departmentService.getSingleDepartment(id);

        existingDepartment.setName(departmentDTO.getName());

        List<Employee> employees = departmentDTO.getEmployeeIds().stream()
                .map(employeeService::getSingleEmployee)
                .collect(Collectors.toList());

        employees.forEach(existingDepartment::addEmployee);

        Department updatedDepartment = departmentService.updateDepartment(existingDepartment);

        return mapToDTO(updatedDepartment);
    }
    /*
    @PutMapping("/departments/{id}")
    public Department updateDepartment (@PathVariable Long id, @RequestBody Department department){
        department.setId(id);
        return departmentService.updateDepartment(department);
    }
     */
}