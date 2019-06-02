package com.example.employeemanagement.service;

import com.example.employeemanagement.model.Employee;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<Employee> findAll(Sort sort);
    Optional<Employee> findById(Long id);
    Employee saveEmployee(Employee employee);
}
