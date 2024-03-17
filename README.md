# Project Overview

## Introduction
TalentForce is a straightforward employee management application. The application allows users to effortlessly add new employees to the database, modify existing employee details, delete employees, and assign them to specific departments. Additionally, users can perform search operations using certain parameters to sift through the employee database.

Apart from employee management, the application also provides functionality for department management. Users can create new departments, modify existing ones, or delete them. These departments can then be associated with employees.

Upon launching the application, users can navigate to "http://localhost:8080/" to interact with the front-end built with Thymeleaf and HTML/CSS, and perform the aforementioned operations.

## Technologies Used
The back-end is built with Spring Boot and Hibernate/JPA, while the front-end utilizes a template engine called Thymeleaf, along with HTML/CSS. The database of choice for this project is MySQL.

## The Origin of the Idea

After gaining a basic understanding of Spring, I wanted to apply and futher my knowledge. The thought of creating a program that would fit the needs of a common setting was appealing. As I knew I would start internships in companies, I thought it would be a good idea to create an employee management program, perhaps to also show my passion for Java/Spring.

## Future Plans

The project is currently in its initial stages. I plan to incorporate additional functionalities such as authentication and authorization, logging, among others. I also intend to add more related entities to increase the complexity of the program. Furthermore, I am considering rebuilding the front-end with a more widely used framework or technology, such as Angular or React.


## API Documentation

Upon launching the program, we can go to http://localhost:8080/swagger-ui/index.html#/ to access to the list of rest endpoints of the program:
![image](https://github.com/gianni-jin/TalentForce/assets/129873947/26c3d41d-a918-4a7f-85a4-dc1c2820e533)
![image](https://github.com/gianni-jin/TalentForce/assets/129873947/d7c43fda-48ff-4fec-84e1-77a4a72c1a3c)


## Database Schema
![image](https://github.com/gianni-jin/TalentForce/assets/129873947/d1ef74f1-04c2-441d-9372-b347348ba0a4)

# Demo


Upon opening localhost:8080, we see the index.html page, and it has two options.
![image](https://github.com/gianni-jin/TalentForce/assets/129873947/9572d701-e93d-4183-9099-b6f89a6f9922)

Let's start with "Manage Employees"


## Adding an Employee without Having an Instance of Department

![image](https://github.com/gianni-jin/TalentForce/assets/129873947/8e3dce4f-3a76-405e-b4cf-e13cc133908a)
Currently, there's no employee. 


![image](https://github.com/gianni-jin/TalentForce/assets/129873947/a73182ea-2d2a-4b97-ae62-509ada0772a2)
We can add a new employee by inserting values into this form. Note that because I had used validation annotations with the fieds of the employee entity, the input must satisfy some requirements. For example, for "Email" field, there has to be "@".

![image](https://github.com/gianni-jin/TalentForce/assets/129873947/494697a0-9156-40fb-b9c8-80381101caca)
Some other fields cannot be left blank, such as "Last Name"



![image](https://github.com/gianni-jin/TalentForce/assets/129873947/224ee5ec-357c-42ad-86d1-0524dbda3ddc)
Ops! Actually returned an error! Why? Because naturally, an employee shouldn't be added into the database without a department being created first!


## Adding Departments

![image](https://github.com/gianni-jin/TalentForce/assets/129873947/dc887fc3-8810-4d9f-9092-a2431f6c1960)

Let's therefore go back to create some instances of department first
![image](https://github.com/gianni-jin/TalentForce/assets/129873947/ca0162c6-d080-4fe4-a9a0-dcf4cadfbb7d)

![image](https://github.com/gianni-jin/TalentForce/assets/129873947/d962b5e1-8b5b-4e89-8586-5d0d4ef2a545)


![image](https://github.com/gianni-jin/TalentForce/assets/129873947/c1e76f1b-2ea6-4d17-b0b9-582ccbbd5017)

## Adding Employees

![image](https://github.com/gianni-jin/TalentForce/assets/129873947/3fc01cfb-2d90-4c18-be7a-3fbfbf85c092)
Great! Now we can see from the drop down list that we've a set of departents to choose from!


![image](https://github.com/gianni-jin/TalentForce/assets/129873947/3975767c-b513-440d-b25a-c50c873dd34b)
Our first instance of employee is added to the database!



![image](https://github.com/gianni-jin/TalentForce/assets/129873947/05a86271-98fc-4586-8141-c72906e388ed)

## Filter Employee Records

As our database about employees grows over time, we need to use search functions to better find out the records of employees that we need to manage.
![image](https://github.com/gianni-jin/TalentForce/assets/129873947/bfbcfc5a-56d5-4a8c-ac18-199bfb0832e6)


We can use different ways to achieve this. For instance, by using the location:
![image](https://github.com/gianni-jin/TalentForce/assets/129873947/0cc2f5bd-cee8-4de4-8c32-2507299f3c5a)

![image](https://github.com/gianni-jin/TalentForce/assets/129873947/00c5167f-1fa4-4f5f-8322-ab6fc068d92f)

Or by using the id:
![image](https://github.com/gianni-jin/TalentForce/assets/129873947/0815623f-3671-4a7b-a90e-a58731e4aee9)

![image](https://github.com/gianni-jin/TalentForce/assets/129873947/1546bbb0-7b65-494c-817e-76003378ef2d)

Or by using the last name:
![image](https://github.com/gianni-jin/TalentForce/assets/129873947/bd66800c-dc81-418a-aad8-af53ec250b2d)

![image](https://github.com/gianni-jin/TalentForce/assets/129873947/632e0ade-895e-47b6-91e5-7795e8e5fbad)


## Update Employee

We can also update the fields of an employee.

Before: 
![image](https://github.com/gianni-jin/TalentForce/assets/129873947/2a7ce8ef-4eae-429a-a419-8caa9fcd0a44)

![image](https://github.com/gianni-jin/TalentForce/assets/129873947/31ad5c62-7257-41be-8c5d-35fb8c701c61)

After: 
![image](https://github.com/gianni-jin/TalentForce/assets/129873947/bfa0054a-a9ea-4f41-b2d1-0aa56166a24b)


## Delete Employee
We can also delete employees from our database:
![image](https://github.com/gianni-jin/TalentForce/assets/129873947/405d0c48-4230-478a-a1eb-78b915b1545d)


## Change Employee's Department

![image](https://github.com/gianni-jin/TalentForce/assets/129873947/b71a2c5e-5993-4f9d-b3a6-fd4c0ae30096)
We can also change the employee's department directly in the department management section, by clicking on the update and then removing the current employees from a certain department:
![image](https://github.com/gianni-jin/TalentForce/assets/129873947/5cecd24d-5892-42e4-8ffc-53b0aea98415)

![image](https://github.com/gianni-jin/TalentForce/assets/129873947/4c9c546d-f982-49f6-9abc-6bee8b2a322a)

![image](https://github.com/gianni-jin/TalentForce/assets/129873947/5b59071d-201e-4218-ad90-4aad95176133)


Now that two "Rossi" aren't associated with a deparment, we can update their department association:
![image](https://github.com/gianni-jin/TalentForce/assets/129873947/9a08d699-71f6-4691-a261-250a03d23c34)
![image](https://github.com/gianni-jin/TalentForce/assets/129873947/0fded551-9500-41dd-ad17-4f80266e81b8)
![image](https://github.com/gianni-jin/TalentForce/assets/129873947/9a350134-4b8c-4d24-a221-9d695f338e1d)

## Update Department
Of course we can update a department's name as we wish as well:
![image](https://github.com/gianni-jin/TalentForce/assets/129873947/8ab475bb-8ed1-42f4-9ef3-1072a082c037)


![image](https://github.com/gianni-jin/TalentForce/assets/129873947/98f4e796-2eae-4caf-a2c7-4f0d2a8068f3)


## Delete Departments
And finally, we can delete departments:

![image](https://github.com/gianni-jin/TalentForce/assets/129873947/b8b5547e-4a87-4977-8c25-c80a8f009617)
![image](https://github.com/gianni-jin/TalentForce/assets/129873947/239126aa-0b85-4a4b-9e43-2b34534dc1ab)


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


For example, EmployeeDTO is used to transfer data about an employee between different parts of the application, and so is . 


In both classes, I've used Lombok annotations such as "@Getter", "@Setter", "@ToString", "@NoArgsConstructor", "@AllArgsConstructor" to automatically generate getter, setter, toString, and constructor methods. This helps to reduce boilerplate code and makes the code cleaner and easier to read.

## Exception Package


The "exception" package in the TalentForce application contains classes for handling exceptions. These will be called by methods in service implementation classes when necessary.


"ErrorObject" class represents an error that occurs in the application. It contains three fields: "statusCode", "message" and "timestamp"

"GlobalExceptionHandler" class extends "ResponseEntityExceptionHandler". It contains two methods: "handleResourceNotFoundException" and "handleResourceAlreadyExistsException"


The way it works is the following: 

When a ResourceNotFoundException is thrown, "handleResourceNotFoundException" creates an "ErrorObject" with the exception's message, a status code of 404 (representing a "Not Found" error), and the current timestamp. It then returns a "ResponseEntity" containing the "ErrorObject" and a HTTP status of "HttpStatus.NOT_FOUND".

"handleResourceAlreadyExistsException" works very similarly to "handleResourceNotFoundException", just the code and HTTP status will be different. 
