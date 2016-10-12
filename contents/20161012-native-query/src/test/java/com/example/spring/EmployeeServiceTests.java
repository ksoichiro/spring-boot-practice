package com.example.spring;

import com.example.spring.entity.ExtendedEmployee;
import com.example.spring.entity.ExtendedEmployee2;
import com.example.spring.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
@Sql("/data-test.sql")
@Slf4j
public class EmployeeServiceTests {
    @Autowired
    private EmployeeService employeeService;

    @Test
    public void findByDepartmentName() {
        List<ExtendedEmployee> expected = Arrays.asList(
            new ExtendedEmployee(1, "e1", "foo"),
            new ExtendedEmployee(3, "e3", "foo"));
        assertThat(employeeService.findByDepartmentName("foo"), is(expected));

        expected = Arrays.asList(
            new ExtendedEmployee(2, "e2", "bar"),
            new ExtendedEmployee(4, "e4", "bar"));
        assertThat(employeeService.findByDepartmentName("bar"), is(expected));
    }

    @Test
    public void findByDepartmentName2() {
        List<ExtendedEmployee2> expected = Arrays.asList(
            new ExtendedEmployee2(1, "e1", "foo"),
            new ExtendedEmployee2(3, "e3", "foo"));
        assertThat(employeeService.findByDepartmentName2("foo"), is(expected));

        expected = Arrays.asList(
            new ExtendedEmployee2(2, "e2", "bar"),
            new ExtendedEmployee2(4, "e4", "bar"));
        assertThat(employeeService.findByDepartmentName2("bar"), is(expected));
    }
}
