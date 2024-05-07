package com.giannijin.TalentForce.controller;

import com.giannijin.TalentForce.dto.DepartmentDTO;
import com.giannijin.TalentForce.dto.EmployeeDTO;
import com.giannijin.TalentForce.exception.ResourceNotFoundException;
import com.giannijin.TalentForce.model.Department;
import com.giannijin.TalentForce.model.Employee;
import com.giannijin.TalentForce.repository.DepartmentRepository;
import com.giannijin.TalentForce.repository.EmployeeRepository;
import com.giannijin.TalentForce.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeApiController {

    @Autowired
    private EmployeeService EmpService;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private EmployeeRepository employeeRepository;

    private DepartmentDTO mapToDTO(Department department) {
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(department.getId());
        dto.setName(department.getName());
        dto.setEmployeeIds(department.getEmployees().stream().map(Employee::getId).collect(Collectors.toList()));
        return dto;
    }

    private EmployeeDTO mapEmployeeToDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setFirstName(employee.getFirstName());
        dto.setLastName(employee.getLastName());
        dto.setAge(employee.getAge());
        dto.setGender(employee.getGender());
        dto.setLocation(employee.getLocation());
        dto.setEmail(employee.getEmail());
        dto.setDepartment(mapToDTO(employee.getDepartment()));
        return dto;
    }

    @GetMapping
    public List<EmployeeDTO> getEmployees(){
        List<Employee> employees = EmpService.getEmployees();
        return employees.stream()
                .map(this::mapEmployeeToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployee (@PathVariable Long id) {
        Employee employee = EmpService.getSingleEmployee(id);
        return mapEmployeeToDTO(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee (@PathVariable Long id){
        EmpService.deleteEmployee(id);
    }

    @PostMapping
    public ResponseEntity<Employee> saveEmployee (@Valid @RequestBody Employee employee){
        Long departmentId = employee.getDepartment().getId();
        String departmentName = employee.getDepartment().getName();

        Department department = departmentRepository.findById(departmentId)
                .orElseGet(() -> {
                    Department newDepartment = new Department();
                    newDepartment.setName(departmentName);
                    return departmentRepository.save(newDepartment);
                });

        employee.setDepartment(department);
        employee = employeeRepository.save(employee);

        return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

        Department department = departmentRepository.findById(employeeDetails.getDepartment().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not exist with id :" + employeeDetails.getDepartment().getId()));

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setAge(employeeDetails.getAge());
        employee.setGender(employeeDetails.getGender());
        employee.setLocation(employeeDetails.getLocation());
        employee.setEmail(employeeDetails.getEmail());
        employee.setDepartment(department);

        Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @GetMapping("/filterbyLastNameAndLocation")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByLastNameAndLocation (@RequestParam String lastName, @RequestParam String location) {
        List<Employee> employees = EmpService.getEmployeeByLastNameAndLocation(lastName, location);
        List<EmployeeDTO> employeeDTOs = employees.stream()
                .map(this::mapEmployeeToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(employeeDTOs, HttpStatus.OK);
    }

    @GetMapping("/filterByFirstNameAndLastName")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeByLastNameAndFirstName (@RequestParam String firstName, @RequestParam String lastName) {
        List<Employee> employees = EmpService.getEmployeeByFirstNameAndLastName(firstName, lastName);
        List<EmployeeDTO> employeeDTOs = employees.stream()
                .map(this::mapEmployeeToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(employeeDTOs, HttpStatus.OK);
    }
}