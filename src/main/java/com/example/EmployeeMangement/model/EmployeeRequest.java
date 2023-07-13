package com.example.EmployeeMangement.model;

import com.example.EmployeeMangement.entity.Employee;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Data;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.Date;
@Data
public class EmployeeRequest {

    @JsonProperty("Id")
    public Long id;
    @JsonProperty("firstName")
    public String firstName;
    @JsonProperty("LastName")
    private String lastName;
    @JsonProperty("Email")
    private String email;
    @JsonProperty("ContactNumber")
    private String contactNumber;
    @JsonProperty("Date_Of_Joining")
    private LocalDate dateOfJoining;
    @JsonProperty("Salary")
    private Double salary;
}
