package com.example.spring.service;

import com.example.spring.domain.Employee;
import com.example.spring.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findByFoo(String foo) {
        return employeeRepository.findByFoo(foo);
    }
}
