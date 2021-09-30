package com.example.employee.controller;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee.model.Employee;
import com.example.employee.repository.entity.EmployeeEntity;
import com.example.employee.service.EmployeeService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

@RestController
@RequiredArgsConstructor
@RequestMapping("/1.0")
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping(value="/employee/{employeeNumber}")
    public ResponseEntity<Employee> getEmployee(@PathVariable("employeeNumber") String employeeNumber) {
        Employee employee = employeeService.getEmployee(employeeNumber);
        if (employee != null) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping(value="/allEmployees", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Employee>> getAllEmployees( 
    		
			  @And({			  
			  @Spec(path = "firstname", params = "firstname", spec = Like.class),
			  @Spec(path = "lastname", params = "lastname", spec = Like.class) })
			 
    		Specification<EmployeeEntity> employeeSpec,Pageable pageable) {
        List<Employee> employee = employeeService.getAllEmployees(employeeSpec,pageable);
        if (employee != null && employee.size() > 0) {
            return new ResponseEntity<>(employee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    
    @PatchMapping(value="/updateEmployee", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(employee);
        if (updatedEmployee != null ) {
            return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
