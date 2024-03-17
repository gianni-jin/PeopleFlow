package com.giannijin.TalentForce.service;

import com.giannijin.TalentForce.exception.ResourceAlreadyExistsException;
import com.giannijin.TalentForce.exception.ResourceNotFoundException;
import com.giannijin.TalentForce.model.Department;
import com.giannijin.TalentForce.repository.DepartmentRepository;
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

    @Override
    public Department saveDepartment(Department department){
        if(departmentRepository.existsById(department.getId())) {
            throw new ResourceAlreadyExistsException("Department already exists with this id :: " + department.getId());
        }
        return departmentRepository.save(department);
    }


    @Override
    public Department updateDepartment(Department department){
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> findByNameIgnoreCase(String name) {
        return departmentRepository.findByNameContainingIgnoreCase(name);
    }
}