package com.example.EmployeeMangement.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EmployeeTaxResponse {

    @JsonProperty("Emloyee_Id")
    public Long id;
    @JsonProperty("FirstName")
    public String firstName;
    @JsonProperty("LastName")
    public String lastName;
    @JsonProperty("YearlySalary")
    public Double yearly_Salary;
    @JsonProperty("Tax_Amount")
    public Double taxAmount;
    @JsonProperty("Cess_Amount")
    public Double cessAmount;
}
