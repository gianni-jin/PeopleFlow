# Project Overview

## Introduction
TalentForce is a straightforward employee management application. The application allows users to effortlessly add new employees to the database, modify existing employee details, delete employees, and assign them to specific departments. Additionally, users can perform search operations using certain parameters to sift through the employee database.

Apart from employee management, the application also provides functionality for department management. Users can create new departments, modify existing ones, or delete them. These departments can then be associated with employees.

Upon launching the application, users can navigate to "http://localhost:8080/" to interact with the front-end built with Thymeleaf and HTML/CSS, and perform the aforementioned operations.

## Technologies Used
The back-end is built with Spring Boot and Hibernate/JPA, while the front-end utilizes a template engine called Thymeleaf, along with HTML/CSS. The database of choice for this project is MySQL.



## Future Plans

The project is currently in its initial stages. I plan to incorporate additional functionalities such as authentication and authorization, logging, among others. I also intend to add more related entities to increase the complexity of the program. Furthermore, I am considering rebuilding the front-end with a more widely used framework or technology, such as Angular or React.


## API Documentation

Upon launching the program, we can go to http://localhost:8080/swagger-ui/index.html#/ to access to the list of rest endpoints of the program:
![image](https://github.com/gianni-jin/TalentForce/assets/129873947/26c3d41d-a918-4a7f-85a4-dc1c2820e533)
![image](https://github.com/gianni-jin/TalentForce/assets/129873947/d7c43fda-48ff-4fec-84e1-77a4a72c1a3c)


# Code Structure
The program follows the MVC model structure, emphasizing the separation of concerns and clear delineation of responsibilities. 

Here's a brief overview of the project structure:

## Controller Package
This package contains the controller classes that manage incoming HTTP requests. These controllers utilize the methods defined in the "service" package to execute operations and return the appropriate HTTP responses. 

Usually, in "Controller" package there should be only API controllers, annotated with @RestController and are typically used to create RESTful APIs. These controllers return data (usually in JSON format) and are intended to be consumed by other software, such as front-end frameworks and mobile apps.

However, since here I've decided to use Thymeleaf, I have also created view controllers, which return a model and a view. Thymeleaf can take the model data provided by the controller, and use it to populate an HTML template. The resulting HTML page is then sent to the client.

## Model Package

This package contains the two entity classes of the program, "Department" and "Employee". I've I've used JPA annotations such as @Id and @Column to  map  the two entities to the database tables. For example, "Employee.java" maps to the "tbl_employee" table and "Department.java" maps to the "tbl_departments" table.

Besides, I've made use of Hibernate mapping annotations to set up the relationship between this entity with the other one. 

Finally, I've made use of various lombak annotations such as "@getter "@Getter", "@Setter", "@ToString", "@NoArgsConstructor", "@AllArgsConstructor". They automatically generate getter, setter, toString, and constructor methods.

### @JsonIdentityInfo and Circular Reference Problem 

At the beginning, when I didn't use this annotation, whenever I used Postman to test the endpoins, I always had circular references. Therefore, I've added this annotation  to handle them. It specifies that the "id" field should be used as the identifier for instances of "Employee" during serialization and deserialization.

### Employee Class 

This class is annotated with "@Entity". This way, Instances of this class will be automatically mapped to records in the "tbl_employee" table in the database.

The fiels are just typical fields that you would expect an instance of "Employee" to have. 
#### Method 
What's worth noting is that the "Employee" class has a single method: "setDepartment(Department department)".

This method is used to set the department of the employee. When the "setDepartment" method is called, it updates the "department" field of the "Employee" instance and also updates the list of employees in the "Department" instance. I've added this method to ensure the consistency of the relationship between "Employee" and "Department". 


#### Relationship with Other Entities
Clearly, "Employee" should have "ManytoOne" relationship with "Department". Also, the cascade type should not be "ALL".

The reason why "CascadeType.ALL/REMOVE"  is not suitable in this case is that it would mean that if an "Employee" entity is deleted, the associated "Department" entity would also be deleted. This is not desirable in most cases because a department should not be deleted just because one of its employees is deleted.

### Department Class 

This class is annotated with "@Entity". This way, instances of this class will be automatically mapped to records in the "tbl_departments" table in the database.


#### Methods
The "Department" class has three methods: "addEmployee(Employee employee)", "removeEmployee(Employee employee)", and "getEmployees()".

The "addEmployee" and "removeEmployee" methods are used to add and remove employees from the department, respectively. When an employee is added or removed, the "department" field of the "Employee" instance is also updated. I've added these methods to ensure the consistency of the relationship between "Employee" and "Department".

The "getEmployees" method is used to get the list of employees that belong to the department.

#### Relationship with Other Entities
The "Department" class has a "@OneToMany" relationship with the "Employee" class. This means that one "Department" instance can be associated with many "Employee" instances. 

The "cascade" attribute in the "@OneToMany" annotation is set to "CascadeType.ALL", which means that all operations (persist, merge, remove, refresh, detach) that happen on "Department" instances will also be cascaded to the related "Employee" instances. 

However, I've set the "orphanRemoval" attribute to "true". This means that when an "Employee" instance is removed from the "employees" list of a "Department" instance, it will also be removed from the database. I thought this would be useful and necessary because an "Employee" instance should not exist without a "Department".

The "fetch" attribute in the "@OneToMany" annotation is set to "FetchType.LAZY". This means that the list of "Employee" instances will not be fetched from the database when a "Department" instance is fetched. Instead, it will be fetched only when it is accessed for the first time. I've chosen this setting to improve the performance of the application.


### API Controllers

The "EmployeeApiController" and "DepartmentApiController" classes in the "controller" package provide RESTful API endpoints for managing "Employee" entities in the application. The base URL for all endpoints in these controller are "/api/v1/employees" and "/api/v1/departments", respectively.

The endpoints provide a way to perform CRUD (Create, Read, Update, Delete) operations on "Employee" and "Department" entities in the application, as well as to retrieve employees based on specific filters.


### View Controllers
The "DepartmentViewController" class in the "com.giannijin.TalentForce.controller" package provides web endpoints for managing "Department" entities in the application. The base URL for all endpoints in this controller is "/departments".


Typically, they use the same repository methods as API controllers, and they also call the same service methods as API controllers. However, they usually redirect to a HTML page
to the model and the "employee" view is returned.


## Repository Package

The "repository" package  provides the interface for interacting with the database. In this case, the "DepartmentRepository" interface extends "JpaRepository", which is a part of Spring Data JPA. "JpaRepository" provides methods for all the CRUD operations (Create, Read, Update, Delete) on the entity.

### DepartmentRepository

Besides the typical CRUD methods provided by the "JpaRepository", the "DepartmentRepository" interface has a custom method "findByNameContainingIgnoreCase". This method is used to find "Department" entities by their name, ignoring case.




### EmployeeRepository


The "EmployeeRepository" interface in the "repository" package is an interface that extends "JpaRepository". This interface provides methods for all the CRUD operations (Create, Read, Update, Delete) on the "Employee" entity. 

The "EmployeeRepository" interface has several custom methods such "findByFirstNameAndLastName" and "findByLastNameAndLocation". I've used @Query and wrote JPQL queries for these, as I find the JPA way of writing custom queries a bit cumbersome, especially when I have to write "containing" and "ignoring", they can become very hard to read. 

## Service Package
"service" package contain the business logic of the application and act as a bridge between the controller and repository layers. 

For example, in the "EmployeeServiceImp" class, the "getEmployees()" method calls "employeeRepository.findAll()" to retrieve all Employee entities from the database. Similarly, the "saveEmployee(Employee employee)" method calls "employeeRepository.save(employee)" to save an Employee entity to the database. This is how the Service layer communicates with the Repository layer

Then, these service methods will be called by the respective rest methods in the "controller" package and each will be bind to a certain rest endpoint. 

In other words, Controller layer would call these Service layer methods to handle client requests. For instance, a controller might call "employeeService.getEmployees()" in response to a GET request at an endpoint like "/employees". This is how the Service layer communicates with the Controller layer.



## DTO Package
 This package contains the Data Transfer Object (DTO) classes which are used to transfer data between the client and the server. In other words, DTOs are  used to transfer data about a department between different parts of the application or over the network.


Here, EmployeeDTO is used to transfer data about an employee between different parts of the application, and so is . This could be from your database to your controller, or from your controller to your view. 


In both classes, I've used Lombok annotations such as "@Getter", "@Setter", "@ToString", "@NoArgsConstructor", "@AllArgsConstructor" to automatically generate getter, setter, toString, and constructor methods. This helps to reduce boilerplate code and makes the code cleaner and easier to read.

## Exception Package


The "exception" package in the TalentForce application contains classes for handling exceptions. These will be called by methods in service implementation classes when necessary.


"ErrorObject" class represents an error that occurs in the application. It contains three fields: "statusCode", "message" and "timestamp"

"GlobalExceptionHandler" class extends "ResponseEntityExceptionHandler". It contains two methods: "handleResourceNotFoundException" and "handleResourceAlreadyExistsException"


The way it works is the following: 

When a ResourceNotFoundException is thrown, "handleResourceNotFoundException" creates an "ErrorObject" with the exception's message, a status code of 404 (representing a "Not Found" error), and the current timestamp. It then returns a "ResponseEntity" containing the "ErrorObject" and a HTTP status of "HttpStatus.NOT_FOUND".

"handleResourceAlreadyExistsException" works very similarly to "handleResourceNotFoundException", just the code and HTTP status will be different. 
