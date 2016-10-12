package com.example.spring.service;

import com.example.spring.entity.ExtendedEmployee;
import com.example.spring.entity.ExtendedEmployee2;
import com.example.spring.repository.EmployeeRepository;
import com.example.spring.repository.ExtendedEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ExtendedEmployeeRepository extendedEmployeeRepository;

    public List<ExtendedEmployee> findByDepartmentName(String departmentName) {
        return employeeRepository.findByDepartmentName(departmentName);
    }

    public List<ExtendedEmployee2> findByDepartmentName2(String departmentName) {
        return extendedEmployeeRepository.findByDepartmentName(departmentName);
    }
}
