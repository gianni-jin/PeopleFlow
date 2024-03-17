package com.giannijin.TalentForce.service;

import com.giannijin.TalentForce.model.Department;
import com.giannijin.TalentForce.model.Employee;

import java.util.List;

public interface DepartmentService {
    List<Department> getDepartments();
    Department getSingleDepartment(Long id);
    void deleteDepartment(Long id);
    Department saveDepartment(Department department);
    Department updateDepartment(Department department);
    Department addEmployeeToDepartment(Department department, Employee employee);

    List<Department> findByNameIgnoreCase(String name);
}
