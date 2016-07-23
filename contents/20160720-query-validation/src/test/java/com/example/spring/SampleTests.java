package com.example.spring;

import com.example.spring.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
@Sql("/data-sample.sql")
@Slf4j
public class SampleTests {
    @Autowired
    private EmployeeService employeeService;

    @Test
    public void test() {
        log.info("This is test");
    }

    @Test
    public void testFindByFoo() {
        assertTrue(employeeService.findByFoo("John Doe") != null);
        assertTrue(employeeService.findByFoo("baz") == null);
    }
}
