package com.giannijin.PeopleFlow.service;

import com.giannijin.PeopleFlow.model.Department;
import java.util.List;

public interface DepartmentService {
    List<Department> getDepartments();
    Department getSingleDepartment(Long id);
    void deleteDepartment(Long id);
    Department saveDepartment(Department department);
    Department updateDepartment(Department department);
}