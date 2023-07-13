//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.EmployeeMangement.service.serviceImpl;

import com.example.EmployeeMangement.entity.Employee;
import com.example.EmployeeMangement.model.EmployeeTaxResponse;
import com.example.EmployeeMangement.repository.EmployeeRepo;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Calendar;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeTaxCalculation implements com.example.EmployeeMangement.service.EmployeeTaxCalculation {
    @Autowired
    EmployeeRepo employeeRepo;

    public EmployeeTaxResponse TaxCalculation(Long id) {
        EmployeeTaxResponse employeeTaxResponse = new EmployeeTaxResponse();
        Optional<Employee> employee = Optional.of(new Employee());
        employee = this.employeeRepo.findById(id);
        Double salary = (employee.get()).getSalary();
        LocalDateTime now = LocalDateTime.now();
        Period diff = Period.between((employee.get()).getDateOfJoining(), LocalDate.now());
        int CurrentYear = Calendar.getInstance().get(1);
        String financiyalYearFrom = "";
        financiyalYearFrom = "" + CurrentYear + "-04-01";
       if ((employee.get()).getDateOfJoining().isAfter(LocalDate.parse(financiyalYearFrom))) {
            diff = Period.between(LocalDate.parse(financiyalYearFrom),employee.get().getDateOfJoining());
        }
       else {
           diff = Period.ofYears(0);
       }
        Integer noOfMonths = 12-diff.getMonths();
        Double yearlySalary = salary *noOfMonths;
        if (yearlySalary <= 250000.0) {
            employeeTaxResponse.setId((employee.get()).getId());
            employeeTaxResponse.setFirstName((employee.get()).getFirstName());
            employeeTaxResponse.setLastName((employee.get()).getLastName());
            employeeTaxResponse.setTaxAmount(0.0);
            employeeTaxResponse.setCessAmount(0.0);
            employeeTaxResponse.setYearly_Salary(yearlySalary);
            return employeeTaxResponse;
        } else {
            Double taxAmount;
            if (yearlySalary >= 250000.0 && yearlySalary <= 500000.0) {
                employeeTaxResponse.setId((employee.get()).getId());
                employeeTaxResponse.setFirstName((employee.get()).getFirstName());
                employeeTaxResponse.setLastName((employee.get()).getLastName());
                taxAmount = calculateTaxAmount(salary, 0.05, 0.0, noOfMonths);
                employeeTaxResponse.setTaxAmount(taxAmount);
                employeeTaxResponse.setCessAmount(0.0);
                employeeTaxResponse.setYearly_Salary(yearlySalary);
                return employeeTaxResponse;
            } else if (yearlySalary > 500000.0 && yearlySalary <= 1000000.0) {
                employeeTaxResponse.setId((employee.get()).getId());
                employeeTaxResponse.setFirstName((employee.get()).getFirstName());
                employeeTaxResponse.setLastName((employee.get()).getLastName());
                taxAmount = calculateTaxAmount(salary, 0.1, 500000.0, noOfMonths);
                employeeTaxResponse.setTaxAmount(taxAmount + 12500.0);
                employeeTaxResponse.setCessAmount(0.0);
                employeeTaxResponse.setYearly_Salary(yearlySalary);
                return employeeTaxResponse;
            } else if (yearlySalary > 1000000.0) {
                employeeTaxResponse.setId((employee.get()).getId());
                employeeTaxResponse.setFirstName((employee.get()).getFirstName());
                employeeTaxResponse.setLastName((employee.get()).getLastName());
                taxAmount = calculateTaxAmount(salary, 0.2, 750000.0, noOfMonths);
                employeeTaxResponse.setTaxAmount(taxAmount + 62500.0);
                Double cess = 0.0;
                if (yearlySalary > 2500000.0) {
                    cess = (yearlySalary - 2500000.0) * 0.02;
                } else {
                    cess = 0.0;
                }

                employeeTaxResponse.setCessAmount(cess);
                employeeTaxResponse.setYearly_Salary(yearlySalary);
                return employeeTaxResponse;
            } else {
                return employeeTaxResponse;
            }
        }
    }

    public static Double calculateTaxAmount(Double salary, Double percentage, Double nonTaxableamount, int noOfMonths) {
        Double taxableAmount = (salary * 12) - 250000.0;
        Double taxamount = (taxableAmount - nonTaxableamount) * percentage;
        return taxamount;
    }
}
