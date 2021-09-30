package com.example.employee.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.employee.error.EmployeeNotFoundException;
import com.example.employee.model.Employee;
import com.example.employee.repository.EmployeeRepository;
import com.example.employee.repository.entity.EmployeeEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee getEmployee(String employeeNumber) {
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findByEmployeeNumber(employeeNumber);
        if (employeeEntity.isPresent()) {
            return mapEmployee(employeeEntity.get());
        }
        throw new EmployeeNotFoundException("Employee not found for employeeNumber:" + employeeNumber);
    }
    
    @Override
	public List<Employee> getAllEmployees(Specification<EmployeeEntity> employeeSpec,Pageable pageable) {
		 List<Employee> employee = new ArrayList<>();
		 employeeRepository.findAll(employeeSpec,pageable).forEach(e -> employee.add(mapEmployee(e)));
		 return employee;
	}

    private Employee mapEmployee(EmployeeEntity employeeEntity) {
        return new Employee(employeeEntity.getEmployeeNumber(), employeeEntity.getFirstname(), employeeEntity.getLastname());
    }

	@Override
	public Employee updateEmployee(Employee employee) {
		String employeeNumber = employee.getUserId();
		EmployeeEntity employeeEntity = null;
		Optional<EmployeeEntity> employeeEntityOptional = employeeRepository.findByEmployeeNumber(employeeNumber);
		
        if (employeeEntityOptional.isPresent()) {
        	employeeEntity = employeeEntityOptional.get();
        	
        	employeeEntity = new EmployeeEntity(employeeEntity.getId(), employeeEntity.getEmployeeNumber(),
        			StringUtils.hasText(employee.getFirstName())?employee.getFirstName():employeeEntity.getFirstname(), 
        					StringUtils.hasText(employee.getLastName())?employee.getLastName():employeeEntity.getLastname());
        	
        	return mapEmployee(employeeRepository.save(employeeEntity));
        }
        throw new EmployeeNotFoundException("Employee not found for employeeNumber:" + employeeNumber);
	}
	
	
}
