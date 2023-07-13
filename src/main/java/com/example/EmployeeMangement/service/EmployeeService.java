package com.example.EmployeeMangement.service;

import com.example.EmployeeMangement.model.EmployeeResponse;

import java.time.LocalDate;
import java.util.Date;

public interface EmployeeService {
public EmployeeResponse storingEmployeeData(Long id, String firstName, String lastName, String email, String contact, LocalDate dateOfJoining, Double salary);
}
