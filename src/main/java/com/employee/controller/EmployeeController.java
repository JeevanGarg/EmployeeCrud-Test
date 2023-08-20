package com.employee.controller;

import com.employee.dtos.EmployeeDto;
import com.employee.exception.ResourceNotFoundException;
import com.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController
{
    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeDto employeeDto) throws ResourceNotFoundException {
        EmployeeDto employee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(employee, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDto>> getAllEmoyees()
    {
        return new ResponseEntity<>(employeeService.getAllEmoyees(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(employeeService.getEmployeeById(id),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long id,@Valid @RequestBody EmployeeDto employeeDto) throws ResourceNotFoundException {
        EmployeeDto employeeDto1 = employeeService.updateEmployee(id, employeeDto);
        return new ResponseEntity<>(employeeDto1,HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>("Successfully Deleted",HttpStatus.OK);
    }
}
