package com.giannijin.TalentForce.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@Table(name = "tbl_employee")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;


    @NotBlank(message = "first name should not be null")
    @Column (name = "first_name")
    private String firstName;


    @NotBlank(message = "last name should not be null")
    @Column (name = "last_name")
    private String lastName;

    @Column (name = "age")
    private Long age = 0L;

    @Column (name = "gender")
    @Pattern(regexp="^(M|F)$",message="invalid gender, insert 'M' for male, and 'F' for female")
    private String gender;

    @Column (name = "location")
    private String location;


    @Column (name = "email")
    private String email;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "department_id")
    private Department department;
    public void setDepartment(Department department) {
        if (department == null) {
            if (this.department != null) {
                this.department.getEmployees().remove(this);
            }
        } else {
            department.getEmployees().add(this);
        }
        this.department = department;
    }

}

