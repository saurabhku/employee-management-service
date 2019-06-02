package com.example.employeemanagement.controller;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class TestEmployeeController {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void testGetAllEmployees() throws Exception {
        Employee employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setName("first");
        employee.setSalary(5000);
        employee.setHireDate(LocalDate.of(2000, 12, 12));

        List<Employee> employeeList = Arrays.asList(employee);
        given(employeeService.findAll(null)).willReturn(employeeList);

        this.mockMvc.perform(get("/employee")).andExpect(status().isOk()).andExpect(content().json("[{\"employeeId\": 1,\"name\": \"first\";\"salary\": 5000,\"hireDate\": \"2000-12-12\"}]"));

    }

    @Test
    public void testGetAllEmployeesWithSortingAccordingToSalary() throws Exception {
        Employee employee1 = new Employee();
        employee1.setEmployeeId(1L);
        employee1.setName("first");
        employee1.setSalary(5000);
        employee1.setHireDate(LocalDate.of(2000, 12, 12));

        Employee employee2 = new Employee();
        employee2.setEmployeeId(1L);
        employee2.setName("first");
        employee2.setSalary(6000);
        employee2.setHireDate(LocalDate.of(2000, 12, 12));

        List<Employee> employeeList = Arrays.asList(employee1, employee2);
        given(employeeService.findAll(Sort.by("salary"))).willReturn(employeeList);

        this.mockMvc.perform(get("/employee?sortby=salary")).andExpect(status().isOk())
                .andExpect(content()
                        .json("[{\"employeeId\": 1,\"name\": \"first\";\"salary\": 5000,\"hireDate\": \"2000-12-12\"},{\"employeeId\": 1,\"name\": \"first\";\"salary\": 6000,\"hireDate\": \"2000-12-12\"}]"));

    }

    @Test
    public void testGetAllEmployeesWithSortingAccordingToSalaryDesc() throws Exception {
        Employee employee1 = new Employee();
        employee1.setEmployeeId(1L);
        employee1.setName("first");
        employee1.setSalary(5000);
        employee1.setHireDate(LocalDate.of(2000, 12, 12));

        Employee employee2 = new Employee();
        employee2.setEmployeeId(1L);
        employee2.setName("first");
        employee2.setSalary(6000);
        employee2.setHireDate(LocalDate.of(2000, 12, 12));

        List<Employee> employeeList = Arrays.asList(employee1, employee2);
        given(employeeService.findAll(Sort.by("salary").descending())).willReturn(employeeList);

        this.mockMvc.perform(get("/employee?sortby=salary&sortseq=desc")).andExpect(status().isOk())
                .andExpect(content()
                        .json("[{\"employeeId\": 1,\"name\": \"first\";\"salary\": 6000,\"hireDate\": \"2000-12-12\"},{\"employeeId\": 1,\"name\": \"first\";\"salary\": 5000,\"hireDate\": \"2000-12-12\"}]"));

    }

    @Test
    public void testGetEmployeeById() throws Exception {
        Employee employee1 = new Employee();
        employee1.setEmployeeId(1L);
        employee1.setName("first");
        employee1.setSalary(5000);
        employee1.setHireDate(LocalDate.of(2000, 12, 12));

        given(employeeService.findById(1L)).willReturn(Optional.of(employee1));

        this.mockMvc.perform(get("/employee/1")).andExpect(status().isOk())
                .andExpect(content()
                        .json("{\"employeeId\": 1, \"name\": \"first\",\"salary\": 5000,\"hireDate\": \"2000-12-12\"}"));
    }

    @Test
    public void testGetEmployeeByIdWhenNoDataPresent() throws Exception {
        Employee employee1 = new Employee();
        employee1.setEmployeeId(1L);
        employee1.setName("first");
        employee1.setSalary(5000);
        employee1.setHireDate(LocalDate.of(2000, 12, 12));

        given(employeeService.findById(1L)).willReturn(Optional.empty());

        this.mockMvc.perform(get("/employee/1")).andExpect(status().isNoContent());
    }


}
