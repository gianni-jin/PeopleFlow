package com.giannijin.PeopleFlow.repository;

import com.giannijin.PeopleFlow.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DepartmentRepository extends JpaRepository <Department, Long> {
}
