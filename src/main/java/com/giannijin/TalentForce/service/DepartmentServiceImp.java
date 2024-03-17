package com.giannijin.TalentForce.service;

import com.giannijin.TalentForce.exception.ResourceAlreadyExistsException;
import com.giannijin.TalentForce.exception.ResourceNotFoundException;
import com.giannijin.TalentForce.model.Department;
import com.giannijin.TalentForce.model.Employee;
import com.giannijin.TalentForce.repository.DepartmentRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImp implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> getDepartments(){
        return departmentRepository.findAll();
    }

    @Override
    public Department getSingleDepartment(Long id) {
        return departmentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department not found for this id :: " + id));
    }


    @Override
    public void deleteDepartment(Long id){
        departmentRepository.deleteById(id);
    }

    public Department saveDepartment(Department department) {
        if (department.getId() != null && departmentRepository.existsById(department.getId())) {
            throw new EntityExistsException("Department with id " + department.getId() + " already exists");
        }
        return departmentRepository.save(department);
    }


    @Override
    public Department updateDepartment(Department department) {
        if (department.getId() == null || !departmentRepository.existsById(department.getId())) {
            throw new IllegalArgumentException("Department id must not be null and should exist");
        }
        return departmentRepository.save(department);
    }

    @Override
    public Department addEmployeeToDepartment(Department department, Employee employee) {
        employee.setDepartment(department);
        return departmentRepository.save(department);
    }




    @Override
    public List<Department> findByNameIgnoreCase(String name) {
        return departmentRepository.findByNameContainingIgnoreCase(name);
    }
}
