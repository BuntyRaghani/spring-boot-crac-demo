package com.example.cracdemo.controller;

import com.example.cracdemo.dto.Employee;
import com.example.cracdemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class CracDemoController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/hello")
    public String greeting() {
        Employee employee = new Employee();
        employee.setId("1");
        employee.setAge(20);
        employee.setName("Bunty Raghani" + LocalDateTime.now());

        employeeService.saveUser(employee);

        List<Employee> allUsers = employeeService.getAllUsers();
        allUsers.forEach(System.out::println);
        return "Hello World";
    }
}
