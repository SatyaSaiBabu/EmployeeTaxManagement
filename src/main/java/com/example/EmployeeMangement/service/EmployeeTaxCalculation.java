package com.example.EmployeeMangement.service;

import com.example.EmployeeMangement.model.EmployeeResponse;
import com.example.EmployeeMangement.model.EmployeeTaxResponse;

public interface EmployeeTaxCalculation {
    public EmployeeTaxResponse TaxCalculation(Long id);
}
