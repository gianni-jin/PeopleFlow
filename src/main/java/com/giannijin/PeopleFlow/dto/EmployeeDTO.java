package com.giannijin.PeopleFlow.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private Long age;
    private String sex;
    private String location;
    private String email;
    private DepartmentDTO department;

}