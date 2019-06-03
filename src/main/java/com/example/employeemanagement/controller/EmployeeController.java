package com.example.employeemanagement.controller;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.service.EmployeeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
@Api(value = "Employee management system", description = "operations to list and add a employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    @CrossOrigin
    @ApiOperation(value = "list all employees", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message= "Successfully retrieved"),
            @ApiResponse(code = 400, message= "Invalid input parameter value"),
    })
    public List<Employee> getAllEmployees(@RequestParam(value = "sortby", required = false) String sortBy, @RequestParam(value = "sortseq", required = false) String sortSeq) {
        Optional<Sort> optionalSort = Optional.empty();
        if (!StringUtils.isEmpty(sortBy)) {
            try {
                Employee.class.getDeclaredField(sortBy);
            } catch (NoSuchFieldException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid input parameter value");
            }
            if (!StringUtils.isEmpty(sortSeq) && sortSeq.equals("desc"))
                optionalSort = Optional.of(Sort.by(sortBy).descending());
            else
                optionalSort = Optional.of(Sort.by(sortBy).ascending());
        }
        return employeeService.findAll(optionalSort.isPresent() ? optionalSort.get() : null);
    }

    @GetMapping("/{id}")
    @CrossOrigin
    @ApiOperation(value = "Get a employee based on id", response = Employee.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message= "Successfully retrieved"),
            @ApiResponse(code = 204, message= "No content found"),
    })
    public Employee getEmployee(@PathVariable String id) {
        Optional<Employee> result = employeeService.findById(Long.parseLong(id));
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No data found for this id");
        }
    }

    @PostMapping
    @CrossOrigin
    @ApiOperation(value = "save a employee", response = Employee.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message= "Successfully saved"),
    })
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }
}
