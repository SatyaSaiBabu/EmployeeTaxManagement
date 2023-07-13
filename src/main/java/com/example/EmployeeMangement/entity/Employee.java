package com.example.EmployeeMangement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "EmployeeData")
public class Employee {

    @Id
    @Column(name ="Id")
    private Long id;
    @Column(name="FirstName")
    private String firstName;
    @Column(name="LastName")
    private String lastName;
    @Column(name = "Email")
    private String email;
    @Column(name = "ContactNumber")
    private String contactNumber;
    @Column(name = "Date_Of_Joining")
    private LocalDate dateOfJoining;
    @Column(name ="Salary")
    private Double salary;

}
