package com.example.employee.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.employee.model.Employee;
import com.example.employee.service.EmployeeService;
import com.google.gson.GsonBuilder;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService service;

    @Test
    public void shouldGetEmployeeForTheGivenEmployeeId() {
        String employeeNumber = "001003";
        Employee employee = new Employee(employeeNumber, "firstname", "lastname");
        when(service.getEmployee(employeeNumber)).thenReturn(employee);
        ResponseEntity<Employee> employeeResponseEntity = this.employeeController.getEmployee(employeeNumber);
        assertEquals(HttpStatus.OK, employeeResponseEntity.getStatusCode());
        assertEquals(employee, employeeResponseEntity.getBody());
    }

    @Test
    public void shouldGetEmployeeNotFoundException() {
        String employeeNumber = "001001";
        when(service.getEmployee(employeeNumber)).thenReturn(null);
        ResponseEntity<Employee> employeeResponseEntity = this.employeeController.getEmployee(employeeNumber);
        assertEquals(HttpStatus.NOT_FOUND, employeeResponseEntity.getStatusCode());
    }
    
    @Test
    public void shouldGetAllEmployees() throws Exception {
    	String content = readFileAsString("src/test/resources/AllEmployees.json");
    	List<Employee> list = Arrays.asList(new GsonBuilder().create().fromJson(content, Employee[].class));
    	
        when(service.getAllEmployees(Mockito.any(),Mockito.any())).thenReturn(list);
        ResponseEntity<List<Employee>> employeeResponseEntity = this.employeeController.getAllEmployees(Mockito.any(),Mockito.any());
        assertEquals(HttpStatus.OK, employeeResponseEntity.getStatusCode());
        assertNotNull(employeeResponseEntity.getBody());
    }
    
    @Test
    public void shouldGetNoContentFound() throws Exception {
    	 when(service.getAllEmployees(Mockito.any(),Mockito.any())).thenReturn(null);
         ResponseEntity<List<Employee>> employeeResponseEntity = this.employeeController.getAllEmployees(Mockito.any(),Mockito.any());
        assertEquals(HttpStatus.NO_CONTENT, employeeResponseEntity.getStatusCode());
    }
    
    @Test
    public void shouldUpdateEmployeeForTheGivenEmployeeData() {
        Employee employee = new Employee("001000", "David firstname", "Beckham lastname");
        when(service.updateEmployee(employee)).thenReturn(employee);
        ResponseEntity<Employee> employeeResponseEntity = this.employeeController.updateEmployee(employee);
        assertEquals(HttpStatus.OK, employeeResponseEntity.getStatusCode());
        assertEquals(employee, employeeResponseEntity.getBody());
    }

    @Test
    public void shouldGetUpdateEmployeeNotFoundException() {
    	Employee employee = new Employee("0010-Invalid", "David firstname", "Beckham lastname");
        when(service.updateEmployee(employee)).thenReturn(null);
        ResponseEntity<Employee> employeeResponseEntity = this.employeeController.updateEmployee(employee);
        assertEquals(HttpStatus.NOT_FOUND, employeeResponseEntity.getStatusCode());
    }
    
    public static String readFileAsString(String file)throws Exception {
        return new String(Files.readAllBytes(Paths.get(file)));
    }
}
