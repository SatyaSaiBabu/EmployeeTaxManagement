package com.example.EmployeeMangement.controller;

import com.example.EmployeeMangement.model.EmployeeRequest;
import com.example.EmployeeMangement.model.EmployeeResponse;
import com.example.EmployeeMangement.model.EmployeeTaxResponse;
import com.example.EmployeeMangement.service.EmployeeService;
import com.example.EmployeeMangement.service.EmployeeTaxCalculation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@ResponseBody
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeTaxCalculation employeeTaxCalculation;

    @PostMapping("/saveEmployeeDetails")
    public EmployeeResponse saveRecords(@RequestBody EmployeeRequest employeeRequest){
        EmployeeResponse employeeResponse =employeeService.storingEmployeeData(Long.valueOf(employeeRequest.getId()),employeeRequest.getFirstName(),employeeRequest.getLastName(),employeeRequest.getEmail(),employeeRequest.getContactNumber(),employeeRequest.getDateOfJoining(),employeeRequest.getSalary());
        return employeeResponse;
    }
    @GetMapping("/getTaxDetails/{id}")
    public EmployeeTaxResponse taxCalculating(@PathVariable Long id){
        return employeeTaxCalculation.TaxCalculation(id);
    }
}
