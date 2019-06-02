package com.example.employeemanagement.dataservice;

import com.example.employeemanagement.model.Employee;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
    List<Employee> findAll();
    List<Employee> findAll(Sort s);

}
