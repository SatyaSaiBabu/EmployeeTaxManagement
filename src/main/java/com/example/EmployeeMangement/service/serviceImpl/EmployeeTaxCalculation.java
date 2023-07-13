package com.example.EmployeeMangement.service.serviceImpl;

import com.example.EmployeeMangement.entity.Employee;
import com.example.EmployeeMangement.model.EmployeeResponse;
import com.example.EmployeeMangement.model.EmployeeTaxResponse;
import com.example.EmployeeMangement.repository.EmployeeRepo;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Optional;
@Service
public class EmployeeTaxCalculation implements com.example.EmployeeMangement.service.EmployeeTaxCalculation {
    @Autowired
    EmployeeRepo employeeRepo;
    @Override
    public EmployeeTaxResponse TaxCalculation(Long id) {

        EmployeeTaxResponse employeeTaxResponse = new EmployeeTaxResponse();
        Optional<Employee> employee = Optional.of(new Employee());
        employee =employeeRepo.findById(id);
        Double salary = employee.get().getSalary();
        LocalDateTime now = LocalDateTime.now();
        Period diff = Period.between(employee.get().getDateOfJoining(),
                LocalDate.now());
        Integer noOfMonths = diff.getMonths()+(diff.getYears()*12);
        Double yearlySalary =salary*noOfMonths;

        if(yearlySalary<=250000){
                        employeeTaxResponse.setId(employee.get().getId());
            employeeTaxResponse.setFirstName(employee.get().getFirstName());
            employeeTaxResponse.setLastName(employee.get().getLastName());
            employeeTaxResponse.setTaxAmount(0.0);
            employeeTaxResponse.setCessAmount(0.0);
            employeeTaxResponse.setYearly_Salary(yearlySalary);
            return employeeTaxResponse;
        } else if (yearlySalary>=250000&&yearlySalary<=500000) {

            employeeTaxResponse.setId(employee.get().getId());
            employeeTaxResponse.setFirstName(employee.get().getFirstName());
            employeeTaxResponse.setLastName(employee.get().getLastName());
            Double taxAmount =calculateTaxAmount(salary,0.05,0.0);
            employeeTaxResponse.setTaxAmount(taxAmount);
            employeeTaxResponse.setCessAmount(0.0);
            employeeTaxResponse.setYearly_Salary(yearlySalary);
            return employeeTaxResponse;

        } else if (yearlySalary>500000&&yearlySalary<=1000000) {
            employeeTaxResponse.setId(employee.get().getId());
            employeeTaxResponse.setFirstName(employee.get().getFirstName());
            employeeTaxResponse.setLastName(employee.get().getLastName());
            Double taxAmount =calculateTaxAmount(salary,0.1,500000.0);
            employeeTaxResponse.setTaxAmount(taxAmount+12500);
            employeeTaxResponse.setCessAmount(0.0);
            employeeTaxResponse.setYearly_Salary(yearlySalary);
            return employeeTaxResponse;
        }
        else if (yearlySalary>1000000) {
            employeeTaxResponse.setId(employee.get().getId());
            employeeTaxResponse.setFirstName(employee.get().getFirstName());
            employeeTaxResponse.setLastName(employee.get().getLastName());
            Double taxAmount =calculateTaxAmount(salary,0.2,750000.0);
            employeeTaxResponse.setTaxAmount(taxAmount+62500);
            Double cess =0.0;
            if(yearlySalary>2500000){
                cess = (yearlySalary-2500000)*0.02;
            }
            else {
                cess =0.0;
            }
            employeeTaxResponse.setCessAmount(cess);
            employeeTaxResponse.setYearly_Salary(yearlySalary);
            return employeeTaxResponse;
        }

        return employeeTaxResponse;
    }

    public static Double calculateTaxAmount(Double salary,Double percentage,Double nonTaxableamount){
        Double taxableAmount = (salary*12) - 250000;
        Double taxamount = (taxableAmount-nonTaxableamount)*percentage;
        return taxamount;
    }
}
