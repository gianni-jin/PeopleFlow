package com.giannijin.TalentForce.service;

import com.giannijin.TalentForce.exception.ResourceAlreadyExistsException;
import com.giannijin.TalentForce.exception.ResourceNotFoundException;
import com.giannijin.TalentForce.model.Employee;
import com.giannijin.TalentForce.repository.EmployeeRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional; 

@Service
public class EmployeeServiceImp implements EmployeeService{


    @Autowired
    private EmployeeRepository employeeRepository;



    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        if (employee.getId() != null) {
            if (employeeRepository.existsById(employee.getId())) {
                throw new EntityExistsException("Employee already exists with this id :: " + employee.getId());
            }
        }
        return employeeRepository.save(employee);
    }



    @Override
    public Employee getSingleEmployee(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + id));
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        if (employee.getId() == null || !employeeRepository.existsById(employee.getId())) {
            throw new IllegalArgumentException("Employee id must not be null and should exist");
        }
        return employeeRepository.save(employee);
    }


    @Override
    public List<Employee> getEmployeeByLastNameAndLocation(String lastName, String location) {
        return employeeRepository.findByLastNameAndLocation(lastName, location);
    }

    @Override
    public List<Employee> getEmployeeByFirstNameAndLastName(String firstName, String lastName) {
        return  employeeRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<Employee> findEmployeesNotInDepartment() {
        return employeeRepository.findByDepartmentIsNull();
    }
}
