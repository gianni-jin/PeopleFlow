package com.giannijin.TalentForce.service;

import com.giannijin.TalentForce.model.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployees();

    Employee saveEmployee (Employee employee);

    Employee getSingleEmployee (Long id);

    void deleteEmployee (Long id);

    Employee updateEmployee (Employee employee);

    List<Employee> getEmployeeByLastNameAndLocation (String lastName, String location);

    List<Employee> getEmployeeByFirstNameAndLastName (String firstName, String lastName);


    List<Employee> findEmployeesNotInDepartment();
}

