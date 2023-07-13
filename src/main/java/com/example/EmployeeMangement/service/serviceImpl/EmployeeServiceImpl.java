package com.example.EmployeeMangement.service.serviceImpl;

import com.example.EmployeeMangement.entity.Employee;
import com.example.EmployeeMangement.model.EmployeeRequest;
import com.example.EmployeeMangement.model.EmployeeResponse;
import com.example.EmployeeMangement.repository.EmployeeRepo;
import com.example.EmployeeMangement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.regex.Pattern;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;
    @Override
    public EmployeeResponse storingEmployeeData(Long id, String firstName, String lastName, String email, String contact, LocalDate dateOfJoining, Double salary) {

        Employee employeeRequest = new Employee();
        if(id!=null&&firstName!=null&&lastName!=null&&email!=null&&contact!=null&&dateOfJoining!=null&&salary!=null){
            employeeRequest.setId(id);
            Pattern pattern = Pattern.compile(".*[0-9].*");
            if(!pattern.matcher(firstName).matches()) {
                employeeRequest.setFirstName(firstName);
            }
            else{
                throw new IllegalArgumentException("First name Shouldn't contains numerics");
            }
            if(!pattern.matcher(lastName).matches()) {
                employeeRequest.setLastName(lastName);
            }
            else{
                throw new IllegalArgumentException("Last name Shouldn't contains numerics");
            }
            String  regexPattern = "^(.+)@(\\S+)$";

            if(Pattern.compile(regexPattern).matcher(email).matches()) {
                employeeRequest.setEmail(email);
            }
            else{
                throw new IllegalArgumentException("Email is not valid");
            }
            if(!Pattern.compile("/^([+]\\d{2})?\\d{10}$/").matcher(contact).matches()) {
                employeeRequest.setContactNumber(contact);
            }
            else {
                throw new RuntimeException("Invalid mobile Number");
            }
            employeeRequest.setDateOfJoining(dateOfJoining);
            employeeRequest.setSalary(salary);
            employeeRepo.save(employeeRequest);

        }
        else{
            throw new IllegalArgumentException("Invalid Data");
        }
        EmployeeResponse employeeResponse =new EmployeeResponse();
        employeeResponse.setFirstName(firstName);
        employeeResponse.setId(id);
        employeeResponse.setLastName(lastName);
        employeeResponse.setEmail(email);
        employeeResponse.setContactNumber(contact);
        employeeResponse.setDateOfJoining(dateOfJoining);
        employeeResponse.setSalary(employeeRequest.getSalary());
        return  employeeResponse;
    }
}
