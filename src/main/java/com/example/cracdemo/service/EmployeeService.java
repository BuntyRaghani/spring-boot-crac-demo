package com.example.cracdemo.service;

import com.example.cracdemo.dto.Employee;
import com.example.cracdemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public void saveUser(Employee Employee) {
        repository.save(Employee);
    }

    public Employee getEmployee(String id) {
        return repository.findById(id).orElse(null);
    }

    public List<Employee> getAllUsers() {
        return repository.findAll();
    }

    public void deleteUser(String id) {
        repository.deleteById(id);
    }
}
