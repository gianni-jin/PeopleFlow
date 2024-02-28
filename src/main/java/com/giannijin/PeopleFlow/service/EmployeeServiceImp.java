package com.giannijin.PeopleFlow.service;

import com.giannijin.PeopleFlow.model.Employee;
import com.giannijin.PeopleFlow.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional; // Add this import statement

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
        return employeeRepository.save(employee);
    }

    @Override
    public Employee getSingleEmployee(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee.get();
        }
        throw new RuntimeException("Employee is not found for the id "+id);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
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


}
