package com.giannijin.PeopleFlow.repository;

import com.giannijin.PeopleFlow.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


@Repository
public interface EmployeeRepository extends JpaRepository <Employee, Long> {


    @Query("SELECT e FROM Employee e WHERE LOWER(e.firstName) LIKE LOWER(CONCAT('%', :firstName, '%')) AND LOWER(e.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))")
    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);

    // List<Employee> findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase (String firstName, String lastName);


    @Query("SELECT e FROM Employee e WHERE e.lastName LIKE %:name% AND e.location LIKE %:location%")
    List<Employee> findByLastNameAndLocation(@Param("name") String name, @Param("location") String location);

    // List<Employee> findByLastNameContainingAndLocationContaining (String name, String location);


}
