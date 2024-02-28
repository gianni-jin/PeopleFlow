package com.giannijin.PeopleFlow.service;

import com.giannijin.PeopleFlow.model.Department;
import com.giannijin.PeopleFlow.repository.DepartmentRepository;
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
        return departmentRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteDepartment(Long id){
        departmentRepository.deleteById(id);
    }

    @Override
    public Department saveDepartment(Department department){
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(Department department){
        return departmentRepository.save(department);
    }
}