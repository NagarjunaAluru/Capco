package com.example.employee.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.example.employee.repository.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>,JpaSpecificationExecutor<EmployeeEntity> {
    Optional<EmployeeEntity> findByEmployeeNumber(String employeeNumber);

}
