package com.giannijin.PeopleFlow.controller;

import com.giannijin.PeopleFlow.dto.DepartmentDTO;
import com.giannijin.PeopleFlow.dto.EmployeeDTO;
import com.giannijin.PeopleFlow.exception.ResourceNotFoundException;
import com.giannijin.PeopleFlow.model.Department;
import com.giannijin.PeopleFlow.model.Employee;
import com.giannijin.PeopleFlow.repository.DepartmentRepository;
import com.giannijin.PeopleFlow.repository.EmployeeRepository;
import com.giannijin.PeopleFlow.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController



public class EmployeeController {


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
        dto.setSex(employee.getSex());
        dto.setLocation(employee.getLocation());
        dto.setEmail(employee.getEmail());
        dto.setDepartment(mapToDTO(employee.getDepartment()));
        return dto;
    }


@GetMapping("/employees")
public List<EmployeeDTO> getEmployees(){
    List<Employee> employees = EmpService.getEmployees();
    return employees.stream()
            .map(this::mapEmployeeToDTO)
            .collect(Collectors.toList());
}
    @GetMapping("/employees/{id}") //from path variable section
    public EmployeeDTO getEmployee (@PathVariable Long id) {
        Employee employee = EmpService.getSingleEmployee(id);
        return mapEmployeeToDTO(employee);
    }
    @DeleteMapping("/employees")
    public void deleteEmployee (@RequestParam Long id){
        EmpService.deleteEmployee(id);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> saveEmployee (@Valid @RequestBody Employee employee){
        Long departmentId = employee.getDepartment().getId();
        String departmentName = employee.getDepartment().getName();  // Assign department name to a variable here

        Department department = departmentRepository.findById(departmentId)
                .orElseGet(() -> {
                    Department newDepartment = new Department();
                    newDepartment.setName(departmentName);  // Use the variable here
                    return departmentRepository.save(newDepartment);
                });

        employee.setDepartment(department);
        employee = employeeRepository.save(employee);

        return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
    }




    @PutMapping("/employees/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id :" + id));

        Department department = departmentRepository.findById(employeeDetails.getDepartment().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Department not exist with id :" + employeeDetails.getDepartment().getId()));

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        // set other fields
        employee.setDepartment(department);

        Employee updatedEmployee = employeeRepository.save(employee);
        return ResponseEntity.ok(updatedEmployee);
    }


    @GetMapping("/employees/filterbyLastNameAndLocation")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByLastNameAndLocation (@RequestParam String lastName, @RequestParam String location) {
        List<Employee> employees = EmpService.getEmployeeByLastNameAndLocation(lastName, location);
        List<EmployeeDTO> employeeDTOs = employees.stream()
                .map(this::mapEmployeeToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(employeeDTOs, HttpStatus.OK);
    }


    @GetMapping("/employees/filterByFirstNameAndLastName")
    public ResponseEntity<List<EmployeeDTO>> getEmployeeByLastNameAndFirstName (@RequestParam String firstName, @RequestParam String lastName) {
        List<Employee> employees = EmpService.getEmployeeByFirstNameAndLastName(firstName, lastName);
        List<EmployeeDTO> employeeDTOs = employees.stream()
                .map(this::mapEmployeeToDTO)
                .collect(Collectors.toList());
        return new ResponseEntity<>(employeeDTOs, HttpStatus.OK);
    }
}
