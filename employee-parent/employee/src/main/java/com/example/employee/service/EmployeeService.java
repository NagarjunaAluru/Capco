package com.example.employee.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.example.employee.model.Employee;
import com.example.employee.repository.entity.EmployeeEntity;

public interface EmployeeService {
    Employee getEmployee(String employeeNumber);
    List<Employee> getAllEmployees(Specification<EmployeeEntity> employeeSpec,Pageable pageable);
    Employee updateEmployee(Employee employee);
}
